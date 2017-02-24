package com.jx.weixin.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.config.shiro.ShiroSecurityHelper;
import com.jx.common.entity.ComAppUser;
import com.jx.common.service.ComAppUserService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.RandomUtil;
import com.jx.common.util.SmsUtil;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.weixin.util.WxUtil;


/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/main")
public class WxMainController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	
	
	/**
	 * 去登录页面
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin() throws Exception {
		//获取myOpenId
		String myOpenId = WxUtil.getWxMyOpenId(request, response);
		if(StringUtils.isEmpty(myOpenId)){
			return null;
		}
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String flag = pd.getString("flag");
		mv.addObject("flag", pd.getString("flag"));
		
		mv.setViewName("weixin/main/wxLogin");
		Subject subject = SecurityUtils.getSubject();
		
		try {
			if(!subject.isAuthenticated()){
				if(subject.isRemembered()){
					String userName = (String)subject.getPrincipal();
					//自动登录
					String[] arr = userName.split(Const.REG_COM_SPLIT);
					if(arr.length == 2){
						String userId= arr[0];
						String openId= arr[1];
						if(myOpenId.equals(openId)){
							ComAppUser comAppUser = comAppUserService.findById(userId);
							if(comAppUser != null && comAppUser.getOpenId().equals(openId)){
								//如果用户已登录，先踢出  
								ShiroSecurityHelper.kickOutUser(userName);  
								UsernamePasswordToken token = new UsernamePasswordToken(userName, 
										new SimpleHash("SHA-512", userId, openId, 2).toString(), true);  
								subject.login(token); // 登录  
								
								WxSessionUtil.setSessionWxComAppUser(comAppUser);
							}
					}
					}
				}
			}
			
			if(subject.isAuthenticated()){
				SavedRequest savedRequest= WebUtils.getSavedRequest(request);
				if(!Const.FLAG_DIRECTLOGIN.equals(flag)&&savedRequest!=null){
					String rollbackUrl = savedRequest.getRequestUrl();
					rollbackUrl = rollbackUrl.replace(request.getContextPath()+"/", "");
					mv.setViewName("redirect:/"+rollbackUrl);
				}else{
					mv.setViewName("redirect:toIndex");
				}
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}
		
		return mv;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/getCaptcha")
	@ResponseBody
	public Object getCaptcha() throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String phone = pd.getString("phone");
		try{
			String captcha = RandomUtil.getRandom(6);
			String backCode = SmsUtil.xxk_login(phone, captcha);
			if("25010 ".equals(backCode)){
				long expire = MapleDateUtil.getNextSeconds(new Date(), 10*60).getTime();
				WxSessionUtil.setSessionWxCaptcha(phone+Const.REG_COM_SPLIT+captcha+Const.REG_COM_SPLIT+expire);
				resultInfo.setResultCode("success");
			}else{
				resultInfo.setResultContent("获取验证码失败！");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setResultContent("获取验证码失败！");
		}
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ModelAndView login() throws Exception {
		//获取openId
		String openId = WxUtil.getWxMyOpenId(request, response);
		if(StringUtils.isEmpty(openId)){
			return null;
		}
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/main/wxLogin");
		
		//参数校验
		String phone = pd.getString("phone");
		String captcha = pd.getString("captcha");
		String flag = pd.getString("flag");
		
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(captcha)){
			resultInfo.setResultContent("未填写手机号码或验证码!");
			mv.addObject(resultInfo);
			return mv;
		}
		
		//验证码校验
		String sessionWxCaptcha = WxSessionUtil.getSessionWxCaptcha();
		String[] arr =  sessionWxCaptcha.split(Const.REG_COM_SPLIT);
		if(arr.length !=3 || !(arr[0]).equals(phone) || !(arr[1]).equals(phone) 
				|| Long.getLong(arr[2])<new Date().getTime()){
			resultInfo.setResultContent("验证码不正确,请重新获取!");
			mv.addObject(resultInfo);
			return mv;
		}
		WxSessionUtil.removeSessionWxCaptcha();
		
		//账号校验
		ComAppUser comAppUser = comAppUserService.findByPhone(phone);
		if(comAppUser == null){
			comAppUser = comAppUserService.wxRegister(openId, phone);
		}else{
			comAppUser.setOpenId(openId);
			comAppUser.setModifyUserId(openId);
			
			comAppUserService.edit(comAppUser);
		}
		 
		// shiro加入身份验证
		try {
			Subject subject = SecurityUtils.getSubject();
			String userName = comAppUser.getAppUserId()+Const.REG_COM_SPLIT+openId;
			//如果用户已登录，先踢出  
			ShiroSecurityHelper.kickOutUser(userName); 
			UsernamePasswordToken token = new UsernamePasswordToken(userName, 
					new SimpleHash("SHA-512", comAppUser.getAppUserId(), openId, 2).toString(), true);
			subject.login(token);
			WxSessionUtil.setSessionWxComAppUser(comAppUser);
			

			SavedRequest savedRequest= WebUtils.getSavedRequest(request);
			if(!Const.FLAG_DIRECTLOGIN.equals(flag)&&savedRequest!=null){
				String rollbackUrl = savedRequest.getRequestUrl();
				
				rollbackUrl = rollbackUrl.replace(request.getContextPath()+"/", "");
				mv.setViewName("redirect:/"+rollbackUrl);
			}else{
				mv.setViewName("redirect:toIndex");
			}
			resultInfo.setResultCode("success");
		} catch (AuthenticationException e) {
			resultInfo.setResultContent("身份验证失败！");
		}
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() throws Exception {
		ModelAndView mv = this.getModelAndView();
		Subject subject = SecurityUtils.getSubject();
	    subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存  
	    
	    mv.addObject("flag", Const.FLAG_DIRECTLOGIN);
	    mv.setViewName("redirect:toLogin");
	    
		return mv;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/register")
	public ModelAndView register() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try{
			String openId = WxUtil.getWxMyOpenId(request, response);
			if(StringUtils.isEmpty(openId)){
				return null;
			}
			WxSessionUtil.setSessionWxMyOpenId(openId);
			System.out.println("33333333333-----------------"+openId);
			mv.setViewName("weixin/main/wxIndex");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try{
			mv.setViewName("weixin/main/wxIndex");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 分类
	 * @return
	 */
	@RequestMapping(value = "/toCategory")
	public ModelAndView toCategory() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try{
			mv.setViewName("weixin/main/wxCategory");
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 购物车
	 * @return
	 */
	@RequestMapping(value = "/toShopCar")
	public ModelAndView toShopCar() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/main/wxShopCar");
		return mv;
	}
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping(value = "/toMine")
	public ModelAndView toMine() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try{
			mv.setViewName("weixin/main/wxMine");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	
	

}
