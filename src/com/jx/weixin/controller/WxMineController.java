package com.jx.weixin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComOrder;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComOrderService;
import com.jx.common.util.AppUtil;
import com.jx.weixin.util.WxSessionUtil;

/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/mine")
public class WxMineController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	@Resource(name = "comOrderService")
	private ComOrderService comOrderService;
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping(value = "/toMyCenter")
	public ModelAndView toMyCenter() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		int count1 = comOrderService.listByOrderIdsSED("01", userId, null).size();
		int count2 = comOrderService.listByOrderIdsSED("02", userId, null).size();
		int count3 = comOrderService.listByOrderIdsSED("03", userId, null).size();
		
		mv.addObject(comAppUser);
		mv.addObject("count1",count1);
		mv.addObject("count2",count2);
		mv.addObject("count3",count3);
		
		mv.setViewName("weixin/mine/wxMyCenter");
		return mv;
	}
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value = "/toMyInfo")
	public ModelAndView toMyInfo() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		mv.addObject(comAppUser);
		mv.setViewName("weixin/mine/wxMyInfo");
		return mv;
	}
	
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value = "/changeMyInfo")
	public ModelAndView changeMyInfo() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		mv.addObject(comAppUser);
		mv.setViewName("weixin/mine/wxMyInfo");
		return mv;
	}
	
	/**
	 * 伙伴
	 * @return
	 */
	@RequestMapping(value = "/toMyPartner")
	public ModelAndView toMyPartner() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		
		ComAppUser comAppUserParent = comAppUserService.findById(comAppUser.getParentId());
		List<ComAppUser> comAppUserSubList = comAppUserService.listByParentId(userId);
		
		mv.addObject("comAppUserParent",comAppUserParent);
		mv.addObject("comAppUserSubList",comAppUserSubList);
		mv.setViewName("weixin/mine/wxMyPartner");
		return mv;
	}
	
	/**
	 * 去修改
	 * @return
	 */
	@RequestMapping(value = "/toMyPwd")
	public ModelAndView toMyPwd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/mine/wxMyPwd");
		return mv;
	}
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "/changePwd")
	@ResponseBody
	public Object changePwd() throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		
		String pwd = comAppUser.getPassword();
		String pwdnew = pd.getString("pwd");
		String pwdold = pd.getString("pwdold");
		
		if(StringUtils.isEmpty(pwdnew)){
			resultInfo.setResultContent("请输入新密码!");
			return AppUtil.returnResult(pd, resultInfo);
		}else{
			if(StringUtils.isNotEmpty(pwd)){
				if(pwd.equals(pwdold)){
					resultInfo.setResultContent("请输入原密码不正确!");
					return AppUtil.returnResult(pd, resultInfo);
				}
			}
			
			//comAppUserService.toChangePwd(userId, pwdnew, pwd);
		}
		
		return AppUtil.returnResult(pd, resultInfo);
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
	
	
	
	

}
