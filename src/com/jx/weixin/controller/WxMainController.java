package com.jx.weixin.controller;

import java.util.Date;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.config.shiro.ShiroSecurityHelper;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComInvite;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComInviteService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.weixin.util.WxSessionUtil;


/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/main")
public class WxMainController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	
	@Resource(name = "comInviteService")
	private ComInviteService comInviteService;
	
	
	
	/**
	 * 去登录页面
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin() throws Exception {
		
		String myOpenId = WxSessionUtil.getMyOpenId();
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		//直接跳转标识
		String flag = pd.getString("flag");
		mv.addObject("flag", pd.getString("flag"));
		
		mv.setViewName("weixin/main/wxLogin");
		
		try {
			Subject subject = SecurityUtils.getSubject();
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
							if(comAppUser != null && comAppUserExtService.toGetOpenId(userId).equals(openId)){
								//如果用户已登录，先踢出  
								ShiroSecurityHelper.kickOutUser(userName);  
								UsernamePasswordToken token = new UsernamePasswordToken(userName, 
										new SimpleHash("SHA-512", userId, openId, 2).toString(), true);  
								subject.login(token); // 登录  
								WxSessionUtil.setUserId(userId);
								WxSessionUtil.setUser(comAppUser);
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
	 * 获取验证码
	 * @return
	 */
	@RequestMapping(value = "/getCaptcha")
	@ResponseBody
	public Object getCaptcha(@RequestParam String phone) throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		if(!Pattern.matches(Const.REG_COM_PHONE_STR, phone)){
			resultInfo.setResultContent("请输入正确手机号码");
			return AppUtil.returnResult(pd, resultInfo);
		}
		try{
//			String captcha = RandomUtil.getRandom(6);
//			String backCode = SmsUtil.xxk_login(phone, captcha);
			String captcha = "123456";
			String backCode = "25010";
			if("25010".equals(backCode)){
				long expire = MapleDateUtil.getNextSeconds(new Date(), 10*60).getTime();
				WxSessionUtil.setCaptcha(phone+Const.REG_COM_SPLIT+captcha+Const.REG_COM_SPLIT+expire);
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
	@ResponseBody
	public Object login() throws Exception {
		
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		String myOpenId = WxSessionUtil.getMyOpenId();
		
		//参数校验
		String phone = pd.getString("phone");
		String captcha = pd.getString("captcha");
		String flag = pd.getString("flag");
		
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(captcha)){
			resultInfo.setResultContent("参数缺失!");
			return AppUtil.returnResult(pd, resultInfo);
		}
		
		//验证码校验
		String sessionCaptcha = WxSessionUtil.getCaptcha();
		String[] arr =  sessionCaptcha.split(Const.REG_COM_SPLIT);
		
		if(arr.length !=3 || !(arr[0]).equals(phone) || !(arr[1]).equals(captcha) 
				|| Long.parseLong(arr[2])<new Date().getTime()){
			resultInfo.setResultContent("验证码不正确,请重试!");
			return AppUtil.returnResult(pd, resultInfo);
		}
		WxSessionUtil.removeCaptcha();
		
		//账号校验
		ComAppUser comAppUser = comAppUserService.findByPhone(phone);
		if(comAppUser == null){
			ComInvite comInvite = comInviteService.findByState00(myOpenId);
			if(comInvite == null){
				resultInfo.setResultContent("未被邀请，无法注册!");
				return AppUtil.returnResult(pd, resultInfo);
			}else{
				comAppUser = comAppUserService.toWxRegister(phone, comInvite);
			}
		}
		
		// shiro加入身份验证
		try {
			Subject subject = SecurityUtils.getSubject();
			String userId = comAppUser.getAppUserId();
			String userName = userId+Const.REG_COM_SPLIT+myOpenId;
			//如果用户已登录，先踢出  
			ShiroSecurityHelper.kickOutUser(userName); 
			UsernamePasswordToken token = new UsernamePasswordToken(userName, 
					new SimpleHash("SHA-512", userId, myOpenId, 2).toString(), true);
			subject.login(token);
			WxSessionUtil.setUser(comAppUser);
			WxSessionUtil.setUserId(userId);
			comAppUserExtService.changeOpenId(userId, myOpenId);

			SavedRequest savedRequest= WebUtils.getSavedRequest(request);
			if(!Const.FLAG_DIRECTLOGIN.equals(flag)&&savedRequest!=null){
				String rollbackUrl = savedRequest.getRequestUrl();
				
				rollbackUrl = rollbackUrl.replace(request.getContextPath()+"/", "");
				resultInfo.setResultContent(rollbackUrl);
			}else{
				resultInfo.setResultContent("weixin/main/toIndex");
			}
			resultInfo.setResultCode("success");
		} catch (AuthenticationException e) {
			resultInfo.setResultContent("身份验证失败！");
		}
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 登出
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
	 * 去注册
	 * @return
	 */
	@RequestMapping(value = "/toRegister")
	public ModelAndView toRegister() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		String pId = pd.getString("pId");
		if(StringUtils.isEmpty(pId)){
			
		}
		
		mv.setViewName("redirect:toIndex");
		return mv;
	}
	
	/**
	 * 注册
	 * @return
	 */
	@RequestMapping(value = "/register")
	@ResponseBody
	public Object register() throws Exception {
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String myOpenId = WxSessionUtil.getMyOpenId();
		
		//参数校验
		String phone = pd.getString("phone");
		String captcha = pd.getString("captcha");
		String pId = pd.getString("pId");
		
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(captcha)||StringUtils.isEmpty(pId)){
			resultInfo.setResultContent("参数缺失!");
			return AppUtil.returnResult(pd, resultInfo);
		}
		
		//验证码校验
		String sessionCaptcha = WxSessionUtil.getCaptcha();
		String[] arr =  sessionCaptcha.split(Const.REG_COM_SPLIT);
		if(arr.length !=3 || !(arr[0]).equals(phone) || !(arr[1]).equals(captcha) 
				|| Long.getLong(arr[2])<new Date().getTime()){
			resultInfo.setResultContent("验证码不正确,请重试!");
			return AppUtil.returnResult(pd, resultInfo);
		}
		WxSessionUtil.removeCaptcha();
		
		//账号校验
		ComAppUser comAppUser = comAppUserService.findByPhone(phone);
		if(comAppUser != null){
			resultInfo.setResultContent("手机号码已被注册!");
			return AppUtil.returnResult(pd, resultInfo);
		}else{
			ComInvite comInvite = comInviteService.findByState00(myOpenId);
			if(comInvite == null){
				resultInfo.setResultContent("未被邀请，无法注册!");
				return AppUtil.returnResult(pd, resultInfo);
			}else{
				comAppUser = comAppUserService.toWxRegister(phone, comInvite);
			}
			
		}
		
		
		// shiro加入身份验证
		try {
			Subject subject = SecurityUtils.getSubject();
			String userId = comAppUser.getAppUserId();
			String userName = userId+Const.REG_COM_SPLIT+myOpenId;
			//如果用户已登录，先踢出  
			ShiroSecurityHelper.kickOutUser(userName); 
			UsernamePasswordToken token = new UsernamePasswordToken(userName, 
					new SimpleHash("SHA-512", userId, myOpenId, 2).toString(), true);
			subject.login(token);
			WxSessionUtil.setUser(comAppUser);
			WxSessionUtil.setUserId(userId);

			resultInfo.setResultContent("weixin/main/toIndex");
			resultInfo.setResultCode("success");
		} catch (AuthenticationException e) {
			resultInfo.setResultContent("身份验证失败！");
		}
		return AppUtil.returnResult(pd, resultInfo);
		
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
