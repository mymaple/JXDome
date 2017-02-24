package com.jx.weixin.controller;

import java.security.Principal;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import com.jx.common.entity.UserInfo;
import com.jx.common.service.ComAppUserService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.HttpManager;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.WxConnUtil;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.weixin.util.WxUtil;

import net.sf.json.JSONObject;

/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/mine")
public class WxMineController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value = "/toMyInfo")
	public ModelAndView toMyInfo() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		return mv;
	}
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value = "/toMyWallet")
	public ModelAndView toMyWallet() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/mine/wxMyWallet");
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
	

}
