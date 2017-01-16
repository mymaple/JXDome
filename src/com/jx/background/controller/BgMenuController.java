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
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationAdd;
import com.jx.common.config.BaseEntity.ValidationEdit;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgMenu.ValidationIcon;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.background.service.BgMenuService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgMenuController
 * 创建人：maple
 * 创建时间：2016-12-20
 */
@Controller
@RequestMapping(value="/background/menu")
public class BgMenuController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background/menu";
	
	@Resource(name="bgMenuService")
	private BgMenuService bgMenuService;
	
	
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
		JSONArray arr = JSONArray.fromObject(bgMenuService.listInRank("0"));
		String json = arr.toString();
		json = json.replaceAll("menuId", "id").replaceAll("parentId", "pId")
				.replaceAll("menuName", "name").replaceAll("subBgMenuList", "nodes")
				.replaceAll("hasMenu", "checked").replaceAll("subBgMenuPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
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
		List<PageData>	bgMenuList = bgMenuService.listPage(bgPage);	//列出bgMenu列表
			
		mv.addObject("bgMenuList", bgMenuList);
		mv.addObject("parentBgMenu", bgMenuService.findById(pd.getString("pId")));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/menu/bgMenuList");

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
		BgMenu parentBgMenu = bgMenuService.findById(parentId);
		if(!"0".equals(parentId) && parentBgMenu==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		BgMenu bgMenu = new BgMenu();
		bgMenu.setParentId(parentId);
		bgMenu.setMenuCode("");
		bgMenu.setMenuName("");
		bgMenu.setMenuType("01");
		bgMenu.setMenuTag(0);
		bgMenu.setMenuUrl("");
		bgMenu.setMenuIcon("");
		bgMenu.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(bgMenu);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/menu/bgMenuEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) BgMenu bgMenu, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMenu");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = bgMenu.getParentId();
		BgMenu parentBgMenu = bgMenuService.findById(parentId);
		if(!"0".equals(parentId) && parentBgMenu==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<BgMenu> bgMenuList = bgMenuService.otherHaveCode("", bgMenu.getMenuCode());
		if(MapleUtil.notEmptyList(bgMenuList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		bgMenuService.add(bgMenu);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String menuId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgMenu bgMenu = bgMenuService.findById(menuId);	//根据ID读取
		if(bgMenu == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(bgMenu);
		resultInfo.setResultCode("success");
		mv.setViewName("background/menu/bgMenuEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) BgMenu bgMenu, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMenu");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<BgMenu> bgMenuList = bgMenuService.otherHaveCode(bgMenu.getMenuId(), bgMenu.getMenuCode());	
		if(MapleUtil.notEmptyList(bgMenuList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		bgMenuService.edit(bgMenu);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String menuId, @RequestParam String menuCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<BgMenu> bgMenuList = bgMenuService.otherHaveCode(menuId, menuCode);	
		if(MapleUtil.emptyList(bgMenuList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String menuId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		bgMenuService.deleteInRank(menuId);	//根据ID删除
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
		bgMenuService.batchDeleteInRank(ids.split(","));	//根据ID删除
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
		titles.add("后台菜单 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("后台菜单代号");	//2
		titles.add("后台菜单名称");	//3
		titles.add("后台菜单类型");	//4
		titles.add("后台菜单状态");	//5
		titles.add("菜单数字标记");	//6
		titles.add("菜单链接");	//7
		titles.add("菜单图标");	//8
		titles.add("排序编号");	//9
		titles.add("有效标志");	//10
		titles.add("创建人员id");	//11
		titles.add("创建时间");	//12
		titles.add("修改人员id");	//13
		titles.add("修改时间");	//14
		dataMap.put("titles", titles);
		List<BgMenu> varOList = bgMenuService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getMenuId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getMenuCode());	//2
			vpd.put("var3", varOList.get(i).getMenuName());	//3
			vpd.put("var4", varOList.get(i).getMenuType());	//4
			vpd.put("var5", varOList.get(i).getMenuStatus());	//5
			vpd.put("var6", varOList.get(i).getMenuTag());	//6
			vpd.put("var7", varOList.get(i).getMenuUrl());	//7
			vpd.put("var8", varOList.get(i).getMenuIcon());	//8
			vpd.put("var9", varOList.get(i).getOrderNum());		//9
			vpd.put("var10", varOList.get(i).getEffective());	//10
			vpd.put("var11", varOList.get(i).getCreateUserId());	//11
			vpd.put("var12", varOList.get(i).getCreateTime());	//12
			vpd.put("var13", varOList.get(i).getModifyUserId());//13
			vpd.put("var14", varOList.get(i).getModifyTime());	//14
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
			
		BgMenu parentBgMenu = bgMenuService.findById(pId);
		if(!"0".equals(pId) && parentBgMenu==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", RIGHTS_BG_MENUCODE_STR);
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
		titles.add("后台菜单代号");	//0
		titles.add("后台菜单名称");	//1
		titles.add("后台菜单类型");	//2
		titles.add("菜单数字标记");	//3
		titles.add("菜单链接");	//4
		titles.add("菜单图标");	//5
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
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		if (null != file && !file.isEmpty()) {
			mv.addObject(resultInfo);					
			return mv;
		}
		String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "menuexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		BgMenu bgMenu = new BgMenu();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		bgMenu.setParentId(pId);
				
		/**
		 * var0 :后台菜单代号;	//0
		 * var1 :后台菜单名称;	//1
		 * var2 :后台菜单类型;	//2
		 * var3 :菜单数字标记;	//3
		 * var4 :菜单链接;	//4
		 * var5 :菜单图标;	//5
		 */
		for(int i=0;i<listPd.size();i++){	
			bgMenu.setMenuId(this.get32UUID());
			bgMenu.setMenuCode(listPd.get(i).getString("var0"));
			bgMenu.setMenuName(listPd.get(i).getString("var1"));
			bgMenu.setMenuType(listPd.get(i).getString("var2"));
			bgMenu.setMenuTag(Integer.valueOf(listPd.get(i).getString("var3")));
			bgMenu.setMenuUrl(listPd.get(i).getString("var4"));
			bgMenu.setMenuIcon(listPd.get(i).getString("var5"));
			bgMenuService.add(bgMenu);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
		/**打开上传EXCEL页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/toChangeMenuIcon")
	public ModelAndView toChangeMenuIcon(@RequestParam String menuId)throws Exception{
		ModelAndView mv = this.getModelAndView();
		if(!JudgeRightsUtil.getRights(RIGHTS_BG_MENUCODE_STR).isEdit()){
			mv.setViewName("background/bgWithoutRights");
			return mv;
		}
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		BgMenu bgMenu = bgMenuService.findById(menuId);	//根据ID读取
		if(bgMenu == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject(bgMenu);
		resultInfo.setResultCode("success");
		mv.setViewName("background/menu/bgMenuIcon");
		return mv;
	}
	
	/**
	 * 保存菜单图标 
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/changeMenuIcon")
	public ModelAndView changeMenuIcon(@Validated(ValidationIcon.class) BgMenu bgMenu, BindingResult result)throws Exception{
		ModelAndView mv = this.getModelAndView();
		if(!JudgeRightsUtil.getRights(RIGHTS_BG_MENUCODE_STR).isEdit()){
			mv.setViewName("background/bgWithoutRights");
			return mv;
		}
		
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("bgMenu");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		bgMenuService.changeMenuIcon(bgMenu);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}

}
