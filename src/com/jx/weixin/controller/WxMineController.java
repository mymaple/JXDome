package com.jx.weixin.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComAppUserRole;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserRoleService;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComOrderService;
import com.jx.common.util.MapleDecimalUtil;
import com.jx.weixin.util.WxSessionUtil;

/**
 * 总入口
 */
@Controller
@RequestMapping(value = "/weixin/mine")
public class WxMineController extends BaseController {
	
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	@Resource(name = "comAppUserRoleService")
	private ComAppUserRoleService comAppUserRoleService;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	@Resource(name = "comOrderService")
	private ComOrderService comOrderService;
	
	/**
	 * 个人中心
	 * @return
	 */
	@RequestMapping(value = "/toMyCenter")
	public ModelAndView toMyCenter() throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		int count1 = comOrderService.listByIdsSED("01", userId, null).size();
		int count2 = comOrderService.listByIdsSED("02", userId, null).size();
		int count3 = comOrderService.listByIdsSED("03", userId, null).size();
		
		String integralCount = comAppUserExtService.toGetIntegralCount(userId);
		
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject(comAppUser);
		mv.addObject("count1",count1);
		mv.addObject("count2",count2);
		mv.addObject("count3",count3);
		mv.addObject("integralCount",integralCount);
		
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
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject(comAppUser);
		mv.setViewName("weixin/mine/wxMyInfo");
		return mv;
	}
	
	/**
	 * 修改个人资料
	 * @return
	 */
	@RequestMapping(value = "/changeMyInfo")
	public ModelAndView changeMyInfo() throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		
		String sex = pd.getString("sex");
		String brithdayStr = pd.getString("brithdayStr");
		String remarks = pd.getString("remarks");
		
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setAppUserId(userId);
		comAppUser.setSex(sex);
		comAppUser.setBrithdayStr(brithdayStr);
		comAppUser.setRemarks(remarks);
		
		try {
			comAppUserService.changeMyInfoByUE(comAppUser);
			resultInfo.setResultCode("success");
			resultInfo.setResultContent("个人资料修改成功！");
			resultInfo.setResultUrl("weixin/mine/toMyCenter.do");
		} catch (Exception e) {
			resultInfo.setResultContent("个人资料修改失败！");
			resultInfo.setResultUrl("weixin/mine/toMyInfo.do");
		}
		
		mv.addObject(resultInfo);
		mv.setViewName("weixin/wxResult");
		
		return mv;
	}
	
	/**
	 * 伙伴
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/toMyPartner")
	public ModelAndView toMyPartner() throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(userId);
		String roleIdStr = comAppUser.getRoleId();
		String[] roleIds = roleIdStr.split(",");
		
		Map map = new HashMap<>();
		for (int i = 0; i < roleIds.length; i++) {
			List list = new ArrayList();
			
			List<ComAppUserRole> comAppUserRoleList = new ArrayList<ComAppUserRole>();
			ComAppUserRole comAppUserRole = comAppUserRoleService.findById(roleIds[i]);
			comAppUserRoleService.listInParentRankByE(comAppUserRoleList, comAppUserRole.getParentId());
			
			List<ComAppUser> comAppUserList = new ArrayList<ComAppUser>();
			comAppUserList.add(comAppUser);
			comAppUserRole.setComAppUserList(comAppUserList);
			comAppUserRoleList.add(comAppUserRole);
			
			List<ComAppUserRole> subComAppUserRoleList = comAppUserRoleService.listByParentId(roleIds[i]);
			for (int j = 0; j < subComAppUserRoleList.size(); j++) {
				subComAppUserRoleList.get(j).setComAppUserList(
						comAppUserService.listInRoleIdE(subComAppUserRoleList.get(j).getAppUserRoleId()));
			}
			list.add(comAppUserRoleList);
			list.add(subComAppUserRoleList);
			map.put(roleIds[i], list);
		}
		
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject("userId", userId);
		mv.addObject("map",map);
		mv.setViewName("weixin/mine/wxMyPartner");
		return mv;
	}
	
	/**
	 * 去下属页面
	 * @return
	 */
	@RequestMapping(value = "/toBranch")
	public ModelAndView toBranch(@RequestParam String toAppUserId, @RequestParam String appUserRoleId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		String userId = WxSessionUtil.getUserId();
		ComAppUser comAppUser = comAppUserService.findById(toAppUserId);
		if(comAppUser == null){
			resultInfo.setResultContent("未找到此用户");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		
		if(!Arrays.asList(comAppUser.getRoleId().split(",")).contains(appUserRoleId)){
			resultInfo.setResultContent("此用户异常");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		List<ComAppUserRole> comAppUserRoleList = comAppUserRoleService.listByParentId(appUserRoleId);
		for (int i = 0; i < comAppUserRoleList.size(); i++) {
			comAppUserRoleList.get(i).setComAppUserList(comAppUserService.listInRoleIdE(comAppUserRoleList.get(i).getAppUserRoleId()));
		}
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject("comAppUserRoleList",comAppUserRoleList);
		mv.addObject("userId", userId);
		mv.setViewName("weixin/mine/wxBranch");
		return mv;
	}
	
	/**
	 * 去转账页面
	 * @return
	 */
	@RequestMapping(value = "/toTransfer")
	public ModelAndView toTransfer(@RequestParam String toAppUserId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		
		ComAppUser comAppUser = comAppUserService.findById(toAppUserId);
		if(comAppUser == null){
			resultInfo.setResultContent("未找到接受用户");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject(comAppUser);
		mv.setViewName("weixin/mine/wxTransfer");
		return mv;
	}
	
	/**
	 * 转赠
	 * @return
	 */
	@RequestMapping(value = "/transfer")
	public ModelAndView transfer(@RequestParam String toAppUserId, @RequestParam double integralCount) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		resultInfo.setResultUrl("weixin/mine/toMyCenter.do");
		
		String userId = WxSessionUtil.getUserId();
		
		ComAppUser toAppUser = comAppUserService.findById(toAppUserId);
		if(toAppUser == null){
			resultInfo.setResultContent("未找到接受用户");
			mv.addObject(resultInfo);
			return mv;
		}
		
		if(!Pattern.matches(Const.REG_COM_FFXS_STR, ""+integralCount)){
			resultInfo.setResultContent("转赠积分数量格式不规范");
			mv.addObject(resultInfo);
			return mv;
		}
		
		try{
			String integralCountU = comAppUserExtService.toGetIntegralCount(userId);
			if(MapleDecimalUtil.subtractDefealt(integralCountU, integralCount)<0){
				resultInfo.setResultContent("积分不足，转赠失败");
			}else{
				comAppUserService.toTransferByUE(userId, toAppUserId, integralCount);
				resultInfo.setResultCode("success");
				resultInfo.setResultContent("转赠成功");
			}
		}catch(Exception e){
			e.printStackTrace();
			resultInfo.setResultContent("转赠失败");
		}
		mv.addObject(resultInfo);
		return mv;
	}
	
	
	
	
/*	*//**
	 * 去修改
	 * @return
	 *//*
	@RequestMapping(value = "/toMyPwd")
	public ModelAndView toMyPwd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/mine/wxMyPwd");
		return mv;
	}
	
	*//**
	 * 修改密码
	 * @return
	 *//*
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
	
	
	*//**
	 * 个人资料
	 * @return
	 *//*
	@RequestMapping(value = "/toMyWallet")
	public ModelAndView toMyWallet() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/mine/wxMyWallet");
		return mv;
	}*/

}
