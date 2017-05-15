package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationAdd;
import com.jx.common.config.BaseEntity.ValidationEdit;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.entity.ComAppUserRole;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserRoleService;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComDictService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgAppUserController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/background/appUser")
public class BgAppUserController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_appUser";
	
	@Resource(name="comAppUserService")
	private ComAppUserService comAppUserService;
	@Resource(name="comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	@Resource(name="comAppUserRoleService")
	private ComAppUserRoleService comAppUserRoleService;
	@Resource(name="comDictService")
	private ComDictService comDictService;
	
	/**
	 * 显示列表ztree
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		JSONArray arr = JSONArray.fromObject(comAppUserService.listInRankCheck("0", ""));
		String json = arr.toString();
		json = json.replaceAll("appUserId", "id").replaceAll("parentId", "pId")
				.replaceAll("appUserName", "name").replaceAll("subComAppUserList", "nodes")
				.replaceAll("hasAppUser", "checked").replaceAll("subComAppUserPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/appUser");
		mv.addObject("pd", pd);
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgMainTree");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String pId = pd.getString("pId");
		String prId = pd.getString("prId");
		if(MapleStringUtil.isEmpty(pId) || "0".equals(pId)){
			pd.put("pId", "0");
			pd.put("prId", "0");
		}else{
			ComAppUser parentComAppUser = comAppUserService.findById(pd.getString("pId"));
			if(MapleStringUtil.isEmpty(prId) || "0".equals(prId)){
				pd.put("prId", "0");
			}else if(!Arrays.asList(parentComAppUser.getRoleId().split(",")).contains(prId)){
				mv.addObject(resultInfo);					
				return mv;
			}
		}
		
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comAppUserList = comAppUserService.listPage(bgPage);	//列出comAppUser列表
		
		List<ComAppUser> parentList = new ArrayList<ComAppUser>();
		comAppUserService.getParentList(parentList, pId);			//导航栏链接
		
		mv.addObject("comAppUserList", comAppUserList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String parentId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		String prId = pd.getString("prId");
		ComAppUser parentComAppUser = comAppUserService.findById(parentId);
		
		if("0".equals(parentId)){
			prId = "0";
		}else if(parentComAppUser!=null){
			if("0".equals(prId)||Arrays.asList(parentComAppUser.getRoleId().split(",")).contains(prId)){
				
			}else{
				mv.addObject(resultInfo);					
				return mv;
			}
		}else{
			mv.addObject(resultInfo);					
			return mv;
		}
		
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setParentId(parentId);
		comAppUser.setRoleId("");
		comAppUser.setAppUserCode("");
		comAppUser.setAppUserName("");
		comAppUser.setAppUserType("01");
		comAppUser.setAppUserNum("");
		comAppUser.setPhone("");
		comAppUser.setPassword("123456");
		comAppUser.setSex("01");
		comAppUser.setBrithday(new Date());
		comAppUser.setRemarks("");
		comAppUser.setOrderNum(""+new Date().getTime());
		
		mv.addObject(comAppUser);
		mv.addObject("methodPath", "add");
		mv.addObject("prId", prId);
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComAppUser comAppUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comAppUser.getParentId();
		ComAppUser parentComAppUser = comAppUserService.findById(parentId);
		if(!"0".equals(parentId) && parentComAppUser==null){
			mv.addObject(resultInfo);
			return mv;
		}
		
		List<ComAppUser> comAppUserList = comAppUserService.otherHavePhone("", comAppUser.getPhone());
		if(MapleUtil.notEmptyList(comAppUserList)){
			mv.addObject(resultInfo);
			return mv;
		}
		
		//后台添加
		comAppUser.setAppUserType("01");
		comAppUserService.add(comAppUser);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String appUserId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		String prId = pd.getString("prId");
		
		ComAppUser comAppUser = comAppUserService.findById(appUserId);	//根据ID读取
		if(comAppUser == null){
			mv.addObject(resultInfo);
			return mv;
		}else{
			String parentId = comAppUser.getParentId();
			ComAppUser parentComAppUser = comAppUserService.findById(parentId);
			
			if("0".equals(parentId)){
				prId = "0";
			}else if(parentComAppUser!=null){
				if("0".equals(prId)||Arrays.asList(parentComAppUser.getRoleId().split(",")).contains(prId)){
						
				}else{
					mv.addObject(resultInfo);					
					return mv;
				}
			}else{
				mv.addObject(resultInfo);					
				return mv;
			}
		}
		
		mv.addObject("prId", prId);
		mv.addObject("methodPath", "edit");
		mv.addObject(comAppUser);
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUser/bgAppUserEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComAppUser comAppUser, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUser");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComAppUser> comAppUserList = comAppUserService.otherHavePhone(comAppUser.getAppUserId(), comAppUser.getPhone());	
		if(MapleUtil.notEmptyList(comAppUserList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comAppUserService.edit(comAppUser);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotPhone")
	@ResponseBody
	public Object otherNotPhone(@RequestParam String appUserId, @RequestParam String phone) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComAppUser> comAppUserList = comAppUserService.otherHavePhone(appUserId, phone);	
		if(MapleUtil.emptyList(comAppUserList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String appUserId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comAppUserService.deleteInRank(appUserId);	//根据ID删除
		resultInfo.setResultCode("success");

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/toBatchDelete")
	@ResponseBody
	public Object toBatchDelete() throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		String ids = pd.getString("ids");
		if(MapleStringUtil.isEmpty(ids)){
			return AppUtil.returnResult(pd, resultInfo);
		}
		comAppUserService.batchDeleteInRank(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/toExportExcel")
	public ModelAndView toExportExcel() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("上级");			//1
		titles.add("角色");			//2
		titles.add("平台用户代号");	//3
		titles.add("平台用户名称");	//4
		titles.add("平台用户类型");	//5
		titles.add("平台用户状态");	//6
		titles.add("平台用户编号");	//7
		titles.add("电话号码");	//8
		titles.add("密码");		//9
		titles.add("性别");		//10
		titles.add("用户头像");	//11
		titles.add("生日");		//12
		titles.add("备注信息");	//13
		titles.add("级别");		//14
		titles.add("排序编号");	//15
		titles.add("有效标志");	//16
		titles.add("创建人员");	//17
		titles.add("创建时间");	//18
		titles.add("修改人员");	//19
		titles.add("修改时间");	//20
		dataMap.put("titles", titles);
		List<ComAppUser> varOList = comAppUserService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0", comDictService.getDisplayName("com_appUserEffective", varOList.get(i).getParentId()));			//0
			varOList.get(i).getRoleId();
			vpd.put("var1", comDictService.getDisplayName("com_appUserRoleEffectiveP", varOList.get(i).getRoleId()));						//1
			vpd.put("var2", varOList.get(i).getAppUserCode());	//2
			vpd.put("var3", varOList.get(i).getAppUserName());	//3
			vpd.put("var4", comDictService.getDisplayName("com_appUserType", varOList.get(i).getAppUserType()));	//4
			vpd.put("var5", comDictService.getDisplayName("com_appUserStatus", varOList.get(i).getAppUserStatus()));	//5
			vpd.put("var6", varOList.get(i).getAppUserNum());	//6
			vpd.put("var7", varOList.get(i).getPhone());	//7
			vpd.put("var8", varOList.get(i).getPassword());	//8
			vpd.put("var9", varOList.get(i).getSex());	//9
			vpd.put("var10", varOList.get(i).getHeadImgSrc());	//10
			vpd.put("var11", varOList.get(i).getBrithday());	//11
			vpd.put("var12", varOList.get(i).getRemarks());	//12
			vpd.put("var13", varOList.get(i).getLevel());	//13
			vpd.put("var14", varOList.get(i).getOrderNum());	//14
			vpd.put("var15", comDictService.getDisplayName("com_effective", varOList.get(i).getEffective()));		//15
			String var16 = comDictService.getDisplayName("bg_userEffective", varOList.get(i).getCreateUserId()) == varOList.get(i).getCreateUserId() ?
					comDictService.getDisplayName("com_appUserEffective", varOList.get(i).getCreateUserId()) : 
						comDictService.getDisplayName("bg_userEffective", varOList.get(i).getCreateUserId());
			vpd.put("var16", var16);	//16
			vpd.put("var17", varOList.get(i).getCreateTime());	//17
			String var18 = comDictService.getDisplayName("bg_userEffective", varOList.get(i).getModifyUserId()) == varOList.get(i).getModifyUserId() ?
					comDictService.getDisplayName("com_appUserEffective", varOList.get(i).getModifyUserId()) : 
						comDictService.getDisplayName("bg_userEffective", varOList.get(i).getModifyUserId());
			vpd.put("var18", var18);	//18
			vpd.put("var19", varOList.get(i).getModifyTime());//19
			varList.add(vpd);
		}
		dataMap.put("varList", varList);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toUploadExcel")
	public ModelAndView toUploadExcel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
			
		ComAppUser parentComAppUser = comAppUserService.findById(pId);
		if(!"0".equals(pId) && parentComAppUser==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background/appUser");
		mv.setViewName("background/bgUploadExcel");

		mv.addObject(resultInfo);					
		return mv;
	}
	
	/**下载模版
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/downExcelModel")
	public ModelAndView downExcelModel()throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("平台用户名称");	//2
		titles.add("电话号码");	//5
		titles.add("密码");	//6
		titles.add("性别01:男，02:女");	//7
		titles.add("生日");	//8
		titles.add("备注信息");	//9
		dataMap.put("titles", titles);
		ObjectExcelView erv = new ObjectExcelView();
		mv = new ModelAndView(erv,dataMap);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
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
		mv.setViewName("background/bgResult");
		ResultInfo resultInfo = this.getResultInfo();

		if (null == file || file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "appUserexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		

		String pId = this.request.getParameter("pId");
		pId = MapleStringUtil.isEmpty(pId)?"0":pId;//上级id
				
		/**
		 * var2 :平台用户名称;	//2
		 * var5 :电话号码;	//5
		 * var6 :密码;	//6
		 * var7 :性别;	//7
		 * var8 :生日;	//8
		 * var9 :备注信息;	//9
		 */
		for(int i=0;i<listPd.size();i++){	
			
			ComAppUser comAppUser = new ComAppUser();
			comAppUser.setParentId(pId);
			
			comAppUser.setAppUserName(listPd.get(i).getString("var0"));
			comAppUser.setPhone(listPd.get(i).getString("var1"));
			comAppUser.setPassword(listPd.get(i).getString("var2"));
			comAppUser.setSex(listPd.get(i).getString("var3"));
			comAppUser.setBrithdayStr(listPd.get(i).getString("var4"));
			comAppUser.setRemarks(listPd.get(i).getString("var5"));
			
			comAppUser.setRoleId("");
			comAppUser.setOrderNum(""+new Date().getTime());
			//后台添加
			comAppUser.setAppUserType("01");
			
			List<ComAppUser> comAppUserList = comAppUserService.otherHavePhone("", comAppUser.getPhone());
			if(MapleUtil.notEmptyList(comAppUserList)){
				mv.addObject(resultInfo);
				return mv;
			}
			comAppUserService.add(comAppUser);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 显示菜单列表ztree(菜单授权菜单)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toChangeRole")
	public ModelAndView toChangeRole(@RequestParam String appUserId,Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			ComAppUser comAppUser = comAppUserService.findById(appUserId);	//根据ID读取
			List<ComAppUserRole> comAppUserRoleList = comAppUserRoleService.listInRankCheck("0", comAppUser.getRoleId());
			
			JSONArray arr = JSONArray.fromObject(comAppUserRoleList);
			String json = arr.toString();
			json = json.replaceAll("appUserRoleId", "id").replaceAll("parentId", "pId")
					.replaceAll("appUserRoleName", "name").replaceAll("subComAppUserRoleList", "nodes")
					.replaceAll("hasAppUserRole", "checked");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("appUserId",appUserId);
			mv.setViewName("background/appUser/bgAppUserCR");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**保存角色菜单权限
	 * @param roleId 角色ID
	 * @param menuIds 菜单ID集合
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/changeRole")
	@ResponseBody
	public Object changeRole(@RequestParam String appUserId, @RequestParam String tags)throws Exception{
		logBefore(logger, "修改菜单权限");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		
		try{
			
			comAppUserService.changeRoleByU(appUserId, tags);				//更新当前角色菜单权限
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toChangeParent")
	public ModelAndView toChangeParent(@RequestParam String appUserId,Model model)throws Exception{
		ModelAndView mv = this.getModelAndView();
		try{
			ComAppUser comAppUser = comAppUserService.findById(appUserId);					//根据ID读取
			List<ComAppUser> comAppUserList = comAppUserService.listInRankCheck("0", comAppUser.getParentId());
			
			
			JSONArray arr = JSONArray.fromObject(comAppUserList);
			String json = arr.toString();
			json = json.replaceAll("appUserId", "id").replaceAll("parentId", "pId")
					.replaceAll("appUserName", "name").replaceAll("subComAppUserList", "nodes")
					.replaceAll("hasAppUser", "checked");
			model.addAttribute("zTreeNodes", json);
			mv.addObject("appUserId",appUserId);
			mv.setViewName("background/appUser/bgAppUserCP");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**保存角色菜单权限
	 * @param appUserId 角色ID
	 * @param tags 
	 * @param out
	 * @throws Exception
	 */
	@RequestMapping(value="/changeParent")
	@ResponseBody
	public Object changeParent(@RequestParam String appUserId, @RequestParam String tags)throws Exception{
		logBefore(logger, "修改菜单权限");
		
		Map<String,String> map = new HashMap<String,String>();
		String errInfo = "";
		
		try{
			comAppUserService.changeParentByU(appUserId, tags);				//更新当前角色菜单权限
			errInfo = "success";
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/toShowExt")
	public ModelAndView toShowExt(@RequestParam String appUserId)throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		List<ComAppUserExt> comAppUserExtList = comAppUserExtService.listByU(appUserId);
		mv.addObject("comAppUserExtList", comAppUserExtList);
		mv.addObject("appUserId", appUserId);
		mv.setViewName("background/appUser/bgShowExt");
		return mv;
	}

}
