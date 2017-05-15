package com.jx.background.controller;

import java.util.ArrayList;
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
import com.jx.common.entity.ComAppUserRole;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComAppUserRoleService;
import com.jx.common.service.ComDictService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgAppUserRoleController
 * 创建人：maple
 * 创建时间：2017-04-21
 */
@Controller
@RequestMapping(value="/background/appUserRole")
public class BgAppUserRoleController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_appUserRole";
	
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
		JSONArray arr = JSONArray.fromObject(comAppUserRoleService.listInRankCheck("0", ""));
		String json = arr.toString();
		json = json.replaceAll("appUserRoleId", "id").replaceAll("parentId", "pId")
				.replaceAll("appUserRoleName", "name").replaceAll("subComAppUserRoleList", "nodes")
				.replaceAll("hasAppUserRole", "checked").replaceAll("subComAppUserRolePath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/appUserRole");
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
		
		if(MapleStringUtil.isEmpty(pd.getString("pId"))){
			pd.put("pId", "0");
		}
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comAppUserRoleList = comAppUserRoleService.listPage(bgPage);	//列出comAppUserRole列表
		
		List<ComAppUserRole> parentList = new ArrayList<ComAppUserRole>();
		comAppUserRoleService.getParentList(parentList, pd.getString("pId"));			//导航栏链接
		
		mv.addObject("comAppUserRoleList", comAppUserRoleList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUserRole/bgAppUserRoleList");

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
		ComAppUserRole parentComAppUserRole = comAppUserRoleService.findById(parentId);
		if(!"0".equals(parentId) && parentComAppUserRole==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComAppUserRole comAppUserRole = new ComAppUserRole();
		comAppUserRole.setParentId(parentId);
		comAppUserRole.setAppUserRoleName("");
		
		if("0".equals(parentId)){
			comAppUserRole.setAppUserRoleType("01");
		}else if(parentComAppUserRole!=null){
			comAppUserRole.setAppUserRoleType("0"+(Integer.parseInt(parentComAppUserRole.getAppUserRoleType())+1));
		}
		
		comAppUserRole.setAppUserRoleStatus("01");
		comAppUserRole.setRemarks("");
		comAppUserRole.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comAppUserRole);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUserRole/bgAppUserRoleEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComAppUserRole comAppUserRole, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUserRole");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comAppUserRole.getParentId();
		ComAppUserRole parentComAppUserRole = comAppUserRoleService.findById(parentId);
		if(!"0".equals(parentId) && parentComAppUserRole==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		
		List<ComAppUserRole> comAppUserRoleList = comAppUserRoleService.otherHaveCode("", comAppUserRole.getAppUserRoleCode());
		if(MapleUtil.notEmptyList(comAppUserRoleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
			
		comAppUserRoleService.add(comAppUserRole);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String appUserRoleId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComAppUserRole comAppUserRole = comAppUserRoleService.findById(appUserRoleId);	//根据ID读取
		if(comAppUserRole == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comAppUserRole);
		resultInfo.setResultCode("success");
		mv.setViewName("background/appUserRole/bgAppUserRoleEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComAppUserRole comAppUserRole, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comAppUserRole");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComAppUserRole> comAppUserRoleList = comAppUserRoleService.otherHaveCode(comAppUserRole.getAppUserRoleId(), comAppUserRole.getAppUserRoleCode());	
		if(MapleUtil.notEmptyList(comAppUserRoleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comAppUserRoleService.edit(comAppUserRole);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String appUserRoleId, @RequestParam String appUserRoleCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComAppUserRole> comAppUserRoleList = comAppUserRoleService.otherHaveCode(appUserRoleId, appUserRoleCode);	
		if(MapleUtil.emptyList(comAppUserRoleList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String appUserRoleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comAppUserRoleService.deleteInRank(appUserRoleId);	//根据ID删除
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
		comAppUserRoleService.batchDeleteInRank(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改状态
	 */
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public Object changeStatus(@RequestParam String flag, @RequestParam String appUserRoleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comAppUserRoleService.changeStatus(flag, appUserRoleId);	
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String appUserRoleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comAppUserRoleService.changeEffective(flag, appUserRoleId);	
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
		ResultInfo resultInfo = this.getResultInfo();

		Map<String,Object> dataMap = new HashMap<String,Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("上级 ");			//0
		titles.add("平台用户角色代号");	//1
		titles.add("平台用户角色名称");	//2
		titles.add("平台用户角色类型");	//3
		titles.add("平台用户角色状态");	//4
		titles.add("备注");			//5
		titles.add("排序编号");		//6
		titles.add("有效标志");		//7
		titles.add("创建人员");		//8
		titles.add("创建时间");		//9
		titles.add("修改人员");		//10
		titles.add("修改时间");		//11
		dataMap.put("titles", titles);
		List<ComAppUserRole> varOList = comAppUserRoleService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();
			vpd.put("var0", comDictService.getDisplayName("com_appUserRoleEffectiveP",varOList.get(i).getParentId()));			//0
			vpd.put("var1", varOList.get(i).getAppUserRoleCode());	//1
			vpd.put("var2", varOList.get(i).getAppUserRoleName());	//2
			vpd.put("var3", comDictService.getDisplayName("com_appUserRoleType", varOList.get(i).getAppUserRoleType()));	//3
			vpd.put("var4", comDictService.getDisplayName("com_appUserRoleStatus", varOList.get(i).getAppUserRoleStatus()));	//4
			vpd.put("var5", varOList.get(i).getRemarks());			//5
			vpd.put("var6", varOList.get(i).getOrderNum());			//6
			vpd.put("var7", comDictService.getDisplayName("com_isEffective", varOList.get(i).getEffective()));		//7
			String var8 = comDictService.getDisplayName("bg_userEffective",varOList.get(i).getCreateUserId()) == varOList.get(i).getCreateUserId() ?
					comDictService.getDisplayName("com_appUserEffective",varOList.get(i).getCreateUserId()) : 
						comDictService.getDisplayName("bg_userEffective",varOList.get(i).getCreateUserId());
			vpd.put("var8", var8);									//8
			vpd.put("var9", varOList.get(i).getCreateTime());		//9
			String var10 = comDictService.getDisplayName("bg_userEffective",varOList.get(i).getModifyUserId()) == varOList.get(i).getModifyUserId() ?
					comDictService.getDisplayName("com_appUserEffective",varOList.get(i).getModifyUserId()) : 
						comDictService.getDisplayName("bg_userEffective",varOList.get(i).getModifyUserId());
			vpd.put("var10", var10);								//10
			vpd.put("var11", varOList.get(i).getModifyTime());		//11
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
			
		ComAppUserRole parentComAppUserRole = comAppUserRoleService.findById(pId);
		if(!"0".equals(pId) && parentComAppUserRole==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background/appUserRole");
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
		titles.add("平台用户角色名称");	//0
		titles.add("平台用户角色类型('00','01','02','03','04')");	//1
		titles.add("平台用户角色状态('01':在职)");	//2
		titles.add("备注");	//3
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
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if (null == file || file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "appUserRoleexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		String pId = this.request.getParameter("pId");
		pId = MapleStringUtil.isEmpty(pId)?"0":pId;//上级id
				
		/**
		 * var0 :平台用户角色名称;	//0
		 * var1 :平台用户角色类型;	//1
		 * var2 :平台用户角色状态;	//2
		 * var3 :备注;	//3
		 */
		for(int i=0;i<listPd.size();i++){	
			ComAppUserRole comAppUserRole = new ComAppUserRole();
			comAppUserRole.setParentId(pId);
			
			comAppUserRole.setAppUserRoleName(listPd.get(i).getString("var0"));
			comAppUserRole.setAppUserRoleType(listPd.get(i).getString("var1"));
			comAppUserRole.setAppUserRoleStatus(listPd.get(i).getString("var2"));
			comAppUserRole.setRemarks(listPd.get(i).getString("var3"));
			comAppUserRole.setOrderNum(""+new Date().getTime());
			comAppUserRoleService.add(comAppUserRole);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
