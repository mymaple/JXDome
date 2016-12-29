package com.jx.background.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.shiro.crypto.hash.SimpleHash;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.entity.BgUser.Add;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.background.util.BgSessionUtil;

/** 
 * 类名称：BgUserController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/user")
public class BgUserController extends BaseController {
	
	/**
	 * 后台 菜单标记名称(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/user";
	
	@Resource(name="bgUserService")
	private BgUserService bgUserService;
	@Resource(name="bgRoleService")
	private BgRoleService bgRoleService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgUser");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String keywords = pd.getString("keywords");				//关键词检索条件
			if(MapleStringUtil.notEmpty(keywords)){
				pd.put("keywords", keywords.trim());
			}
			String lastLoginStart = pd.getString("lastLoginStart");	//开始时间
			String lastLoginEnd = pd.getString("lastLoginEnd");		//结束时间
			if(MapleStringUtil.notEmpty(lastLoginStart)){
				pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
			}
			if(MapleStringUtil.notEmpty(lastLoginEnd)){
				pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
			}
			bgPage.setPd(pd);
			List<PageData>	bgUserList = bgUserService.listPage(bgPage);	//列出bgUser列表
			List<BgRole> bgRoleList = bgRoleService.listSubBgRoleByParentId(1);//列出所有系统用户角色
			
			mv.addObject("bgUserList", bgUserList);
			mv.addObject("bgRoleList", bgRoleList);
			mv.addObject("pd", pd);
			mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());	//按钮权限
			
			mv.setViewName("background/user/bgUserList");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(){
		logBefore(logger, "去新增bgUser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<BgRole> bgRoleList = bgRoleService.listSubBgRoleByParentId(1);//列出所有系统用户角色
			mv.addObject("bgRoleList", bgRoleList);
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/user/bgUserEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated({Add.class}) BgUser bgUser, BindingResult result) throws Exception{
		logBefore(logger, "新增bgUser");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Date nowTime = new Date();
		
		if(result.hasErrors()) {
			mv.setViewName("background/user/bgUserEdit");
            return mv;  
        }
		
		try {
			if(bgUserService.findByLoginName(pd.getString("userCode")) == null ||
					bgUserService.findByLoginName(pd.getString("userNumber")) == null ||
					bgUserService.findByLoginName(pd.getString("email")) == null ||
					bgUserService.findByLoginName(pd.getString("phone")) == null){
				
				bgUser.setUserCode(pd.getString("userCode"));
				bgUser.setUserName(pd.getString("userName"));
				bgUser.setRoleId(Integer.parseInt(pd.getString("roleId")));
				bgUser.setUserNumber(pd.getString("userNumber")); // loginIp
				bgUser.setPhone(pd.getString("phone")); // loginIp
				bgUser.setEmail(pd.getString("email")); // loginIp
				bgUser.setRemarks(pd.getString("remarks")); // loginIp
				
				bgUser.setPassword(new SimpleHash("SHA-512", pd.getString("userCode"), pd.getString("password"),2).toString());
				
				bgUser.setUserRights("0");			// 权限
				bgUser.setLastLoginTime(nowTime);	// 最后登录时间
				bgUser.setLastLoginIp("127.0.0.1"); // loginIp
				bgUser.setUserIconSrc("static/ace/avatars/user.jpg"); // userIconSrc
				bgUser.setStatus("1");				// 状态
				bgUser.setModifyTime(nowTime); 		// 修改时间时间
				
				
				bgUserService.add(bgUser);
				
				mv.addObject("msg","success");
			}else{
				mv.addObject("msg","false");
			}
		
			mv.setViewName("background/bgSaveResult");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改bgUser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if("1".equals(pd.getString("userId"))){return null;}		//不能修改admin用户
			pd = bgUserService.findPdByPd(pd);	//根据ID读取
			List<BgRole> bgRoleList = bgRoleService.listSubBgRoleByParentId(1);//列出所有系统用户角色
			mv.addObject("bgRoleList", bgRoleList);
			mv.addObject("fx", "user");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/user/bgUserEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改bgUser");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Date nowTime = new Date();
		try {
			if(MapleStringUtil.isEmpty(pd.getString("userId")) || 
					bgUserService.findByLoginName(pd.getString("userCode")) == null ||
					bgUserService.findByLoginName(pd.getString("userNumber")) == null ||
					bgUserService.findByLoginName(pd.getString("email")) == null ||
					bgUserService.findByLoginName(pd.getString("phone")) == null){
				
				BgUser bgUser = new BgUser();
				bgUser = bgUserService.findById(Integer.parseInt(pd.getString("userId")));
				bgUser.setUserCode(pd.getString("userCode"));
				bgUser.setUserName(pd.getString("userName"));
				bgUser.setRoleId(Integer.parseInt(pd.getString("roleId")));
				bgUser.setUserNumber(pd.getString("userNumber")); 			// loginIp
				bgUser.setPhone(pd.getString("phone")); 					// loginIp
				bgUser.setEmail(pd.getString("email")); 					// loginIp
				bgUser.setRemarks(pd.getString("remarks")); 				// loginIp
				
				bgUser.setPassword(new SimpleHash("SHA-512", pd.getString("userCode"), pd.getString("password"),2).toString());
				bgUser.setModifyTime(nowTime);
				
				bgUserService.edit(bgUser);
				mv.addObject("msg","success");
			}else{
				mv.addObject("msg","false");
			}
			mv.setViewName("background/bgSaveResult");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String userId)throws Exception{
		logBefore(logger, "删除bgUser");
		PageData pd = new PageData();
		pd = this.getPageData();
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		try{
			bgUserService.deleteByPd(pd);
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
			errInfo = "false";
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() {
		logBefore(logger, "批量删除bgUser");
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String userIds = pd.getString("userIds");
			if(MapleStringUtil.notEmpty(userIds)){
				bgUserService.batchDeleteByIds(userIds.split(","));
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/toExportExcel")
	public ModelAndView toExportExcel(){
		logBefore(logger, "导出bgUser到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			String keywords = pd.getString("keywords");				//关键词检索条件
			if(MapleStringUtil.notEmpty(keywords)){
				pd.put("keywords", keywords.trim());
			}
			String lastLoginStart = pd.getString("lastLoginStart");	//开始时间
			String lastLoginEnd = pd.getString("lastLoginEnd");		//结束时间
			if(MapleStringUtil.notEmpty(lastLoginStart)){
				pd.put("lastLoginStart", lastLoginStart+" 00:00:00");
			}
			if(MapleStringUtil.notEmpty(lastLoginEnd)){
				pd.put("lastLoginEnd", lastLoginEnd+" 00:00:00");
			} 
			
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("后台用户表 主键id");   	//1
			titles.add("用户名");				//0+2
			titles.add("密码");				//1+2
			titles.add("真实姓名");			//2+2
			titles.add("用户权限");			//3+2
			titles.add("角色id");			//4+2
			titles.add("最后登录时间");		//5+2
			titles.add("最后登录IP");			//6+2
			titles.add("用户头像路径");		//7+2
			titles.add("用户编号");			//8+2
			titles.add("电子邮箱");			//9+2
			titles.add("手机号码");			//10+2
			titles.add("状态");				//11+2
			titles.add("备注信息");			//12+2
			titles.add("修改时间");			//13+2
			dataMap.put("titles", titles);
			List<BgUser> varOList = bgUserService.listByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var0", String.valueOf(varOList.get(i).getUserId()));
				vpd.put("var1", varOList.get(i).getUserCode());			//2
				vpd.put("var2", varOList.get(i).getPassword());			//3
				vpd.put("var3", varOList.get(i).getUserName());			//4
				vpd.put("var4", varOList.get(i).getUserRights());		//5
				vpd.put("var5", String.valueOf(varOList.get(i).getRoleId()));			//6
				vpd.put("var6", varOList.get(i).getLastLoginTimeStr());	//7
				vpd.put("var7", varOList.get(i).getLastLoginIp());		//8
				vpd.put("var8", varOList.get(i).getUserIconSrc());		//9
				vpd.put("var9", varOList.get(i).getUserNumber());		//10
				vpd.put("var10", varOList.get(i).getEmail());			//11
				vpd.put("var11", varOList.get(i).getPhone());			//12
				vpd.put("var12", varOList.get(i).getStatus());			//13
				vpd.put("var13", varOList.get(i).getRemarks());			//14
				vpd.put("var14", varOList.get(i).getModifyTimeStr());		//15
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView goUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.addObject("objStr","user");
		mv.setViewName("background/bgUploadExcel");
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcelModel")
	public ModelAndView downExcelModel()throws Exception{
		
		logBefore(logger, "导出bgUser到excel");
		ModelAndView mv = new ModelAndView();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户名");				//0+2
			titles.add("真实姓名");			//2+2
			titles.add("用户编号");			//8+2
			titles.add("电子邮箱");			//9+2
			titles.add("手机号码");			//10+2
			titles.add("备注信息");			//12+2
			dataMap.put("titles", titles);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
		
	
	/**从EXCEL导入到数据库
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/uploadExcel")
	public ModelAndView uploadExcel(
			@RequestParam(value="excel",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		Date nowTime = new Date();
		try {
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
				String fileName =  MapleFileUtil.fileUp(file, filePath, "userexcel");							//执行上传
				List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
				/*存入数据库操作======================================*/
				
				BgUser bgUser = new BgUser();
				bgUser.setUserRights("0");			// 权限
				bgUser.setLastLoginTime(nowTime);	// 最后登录时间
				bgUser.setLastLoginIp("127.0.0.1"); // loginIp
				bgUser.setUserIconSrc("static/ace/avatars/user.jpg"); // userIconSrc
				bgUser.setStatus("1");				// 状态
				bgUser.setModifyTime(nowTime); 		// 修改时间时间
				bgUser.setRoleId(5);
				
				/**
				 * var0 :用户名
				 * var1 :姓名
				 * var2 :用户编号
				 * var3 :电子邮箱
				 * var4 :手机号码
				 * var5 :备注
				 */
				for(int i=0;i<listPd.size();i++){	
					
					bgUser.setUserCode(listPd.get(i).getString("var0"));
					bgUser.setUserName(listPd.get(i).getString("var1"));
					bgUser.setUserNumber(listPd.get(i).getString("var2")); // loginIp
					bgUser.setEmail(listPd.get(i).getString("var3")); // loginIp
					bgUser.setPhone(listPd.get(i).getString("var4")); // loginIp
					bgUser.setRemarks(listPd.get(i).getString("var5")); // loginIp
					
					bgUser.setPassword(new SimpleHash("SHA-512", bgUser.getUserName(), bgUser.getUserName(),2).toString());
					bgUserService.add(bgUser);
				}
				/*存入数据库操作======================================*/
				mv.addObject("msg","success");
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}	
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value = "/hasLoginName")
	@ResponseBody
	public Object hasLoginName() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String loginName = pd.getString("loginName");
			if (bgUserService.findByLoginName(loginName) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
}
