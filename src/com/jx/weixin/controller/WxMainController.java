package com.jx.weixin.controller;

import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.service.BgConfigService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.config.shiro.ShiroHelper;
import com.jx.common.config.shiro.ShiroSecurityHelper;
import com.jx.common.entity.ComAppUser;
import com.jx.common.service.ComAppUserService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.HttpManager;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.WxConnUtil;
import com.jx.wechat.entity.UserInfo;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.weixin.util.WxUtil;

import net.sf.json.JSONObject;

/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/main")
public class WxMainController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String flag = pd.getString("flag");
		mv.addObject("flag", pd.getString("flag"));
		
		mv.setViewName("weixin/main/wxLogin");
		Subject subject = SecurityUtils.getSubject();
		if(subject.isRemembered()){  
			String phone = (String)subject.getPrincipal();
			String captcha = "123456";
			if (!subject.isAuthenticated()) {  
	        	//如果用户已登录，先踢出  
	        	ShiroSecurityHelper.kickOutUser(phone);  
	        	UsernamePasswordToken token = new UsernamePasswordToken(phone, captcha, true);  
	        	subject.login(token); // 登录  
	        }
			SavedRequest savedRequest= WebUtils.getSavedRequest(request);
			if(!Const.FLAG_DIRECTLOGIN.equals(flag)&&savedRequest!=null){
				String rollbackUrl = savedRequest.getRequestUrl();
				rollbackUrl = rollbackUrl.replace(request.getContextPath()+"/", "");
				mv.setViewName("redirect:/"+rollbackUrl);
			}else{
				mv.setViewName("redirect:toIndex");
			}
		}
		
		
		return mv;
	}
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/login1")
	public ModelAndView login1() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		//参数校验
		String phone = pd.getString("phone");
		String captcha = pd.getString("captcha");
		String flag = pd.getString("flag");
		
		Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {  
        	//如果用户已登录，先踢出  
        	ShiroSecurityHelper.kickOutUser(phone);  
        	UsernamePasswordToken token = new UsernamePasswordToken(phone, captcha, true);  
        	subject.login(token); // 登录  
        }
        SavedRequest savedRequest= WebUtils.getSavedRequest(request);
        if(!Const.FLAG_DIRECTLOGIN.equals(flag)&&savedRequest!=null){
			String rollbackUrl = savedRequest.getRequestUrl();
			rollbackUrl = rollbackUrl.replace(request.getContextPath()+"/", "");
			mv.setViewName("redirect:/"+rollbackUrl);
		}else{
			mv.setViewName("redirect:toIndex");
		}
	              
		return mv;
	}
	
	
	/**
	 * 登录
	 * @return
	 */
	@RequestMapping(value = "/toLogin1")
	public ModelAndView toLogin1() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		Subject subject = SecurityUtils.getSubject();
		if(!subject.isAuthenticated()){
			String userName = (String)subject.getPrincipal();
			String url = WebUtils.getSavedRequest(request).getRequestUrl();
			logger.info(url);
			if(subject.isRemembered()&&!Const.PATH_WX_TOLOGIN_STR.equals(url)
					&&StringUtils.isNotEmpty(userName)){
				//自动登录
				String[] arr = userName.split(",,,,");
				String userId= arr[0];
				String openId= arr[1];
				//账号校验
				ComAppUser comAppUser = comAppUserService.findById(userId);
				if(comAppUser != null && comAppUser.getOpenId().equals(openId)){
					//如果用户已登录，先踢出  
					ShiroSecurityHelper.kickOutUser(userName);
					UsernamePasswordToken token = new UsernamePasswordToken(userName, comAppUser.getPassword(), true);  
					subject.login(token); // 登录 
					
					mv.setViewName("redirect:"+url);
				}else{
					mv.setViewName("weixin/main/wxLogin");
				}
			}else{
				mv.setViewName("weixin/main/wxLogin");
			}
		}else{
			mv.setViewName("redirect:toMine");
		}
		String userName = (String)subject.getPrincipal();
		if(StringUtils.isEmpty(userName)){
			
		}
		if(subject.isAuthenticated()){
			mv.setViewName("redirect:toMine");
		}else{
			if(subject.isRemembered()){
				
			}else{
				mv.setViewName("weixin/main/wxLogin");
			}
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
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		WxSessionUtil.setSessionWxCaptcha("123456");
		
		resultInfo.setResultCode("success");
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
		if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(captcha)){
			resultInfo.setResultContent("未填写手机号码或验证码!");
			mv.addObject(resultInfo);
			return mv;
		}
		
		//验证码校验
		String sessionWxCaptcha = WxSessionUtil.getSessionWxCaptcha();
		if(!captcha.equals(sessionWxCaptcha)){
			resultInfo.setResultContent("验证码不正确!");
			mv.addObject(resultInfo);
			return mv;
		}
		WxSessionUtil.removeSessionWxCaptcha();
		
		//账号校验
		ComAppUser comAppUser = comAppUserService.findByPhone(phone);
		if(comAppUser == null){
			comAppUser = new ComAppUser();
			
			Date nowtime = new Date();
			//获取个人用户信息
			String json = WxConnUtil.getUserInfo(openId);
			UserInfo userInfo = new UserInfo();
			userInfo = (UserInfo)MapleUtil.convertJson(userInfo.getClass(), JSONObject.fromObject(json));
			String fileSrc = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/"+ openId+"_headimg.jpg";
			//下载头像图片
			if(StringUtils.isNotEmpty(userInfo.getHeadimgurl())){
				HttpManager.download(userInfo.getHeadimgurl(), null, fileSrc);
			}else{
				String headimgDefault = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/default_headimg.jpg";
				MapleFileUtil.copyFile(headimgDefault , fileSrc);
			}
			
			//生成个人编号
			String appUserNum = "";
			
			comAppUser.setOpenId(openId);
			comAppUser.setPhone(phone);
			
			comAppUser.setAppUserCode(StringUtils.isEmpty(userInfo.getNickname())?appUserNum:userInfo.getNickname());
			comAppUser.setSex("1".equals(userInfo.getSex())?"01":"02");
			comAppUser.setBrithday(nowtime);
			comAppUser.setWxQRcodeExpiry(nowtime);
			comAppUser.setMediaExpiry(nowtime);
//			comAppUser.setParentId(jsonCode);
			comAppUser.setHeadImgUrl(Const.PATH_MYHEADIMG+"/"+openId+"_headimg.jpg");
			comAppUser.setOrderNum(""+nowtime.getTime());
			comAppUser.setAppUserNum(appUserNum);
			
			//微信关注
			comAppUser.setAppUserStatus("01");
			//自主微信关注用户
			comAppUser.setAppUserType("01");
			comAppUser.setCreateUserId(openId);
			comAppUser.setModifyUserId(openId);
			
			comAppUserService.add(comAppUser);
			
		}else{
			comAppUser.setOpenId(openId);
			comAppUser.setModifyUserId(openId);
			
			comAppUserService.edit(comAppUser);
		}
		 
		// shiro加入身份验证
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(comAppUser.getAppUserId()+",,,,"+openId, comAppUser.getPhone(), true);
			subject.login(token);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			resultInfo.setResultContent("身份验证失败！");
			mv.addObject(resultInfo);
			return mv;
		}
		
		mv.setViewName("redirect:toMine");
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
