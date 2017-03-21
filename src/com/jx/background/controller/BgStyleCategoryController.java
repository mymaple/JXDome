package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.jx.common.entity.ComStyleCategory;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComStyleCategoryService;

import net.sf.json.JSONArray;

/** 
 * 类名称：BgStyleCategoryController
 * 创建人：maple
 * 创建时间：2017-03-10
 */
@Controller
@RequestMapping(value="/background/styleCategory")
public class BgStyleCategoryController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_styleCategory";
	
	@Resource(name="comStyleCategoryService")
	private ComStyleCategoryService comStyleCategoryService;
	
	
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
		String productId = pd.getString("productId");
		
		JSONArray arr = JSONArray.fromObject(comStyleCategoryService.listInRank(productId, "0"));
		String json = arr.toString();
		json = json.replaceAll("styleCategoryId", "id").replaceAll("parentId", "pId")
				.replaceAll("styleCategoryName", "name").replaceAll("subComStyleCategoryList", "nodes")
				.replaceAll("hasStyleCategory", "checked").replaceAll("subComStyleCategoryPath", "url");
		model.addAttribute("zTreeNodes", json);
		mv.addObject("controllerPath", "background/styleCategory");
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
		List<PageData>	comStyleCategoryList = comStyleCategoryService.listPage(bgPage);	//列出comStyleCategory列表
		
		List<ComStyleCategory> parentList = new ArrayList<ComStyleCategory>();
		comStyleCategoryService.getParentList(parentList, pd.getString("pId"));			//导航栏链接
		
		mv.addObject("comStyleCategoryList", comStyleCategoryList);
		mv.addObject("parentList", parentList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategory/bgStyleCategoryList");

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
		String productId = StringUtils.isEmpty(pd.getString("productId"))?"":pd.getString("productId");
		
		
		ComStyleCategory parentComStyleCategory = comStyleCategoryService.findById(parentId);
		if(!"0".equals(parentId) && parentComStyleCategory==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		if(parentComStyleCategory!=null){
			productId = parentComStyleCategory.getProductId();
		}
		
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setParentId(parentId);
		comStyleCategory.setProductId(productId);
		comStyleCategory.setStyleCategoryName("");
		comStyleCategory.setIsFinal("00");
		comStyleCategory.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comStyleCategory);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategory/bgStyleCategoryEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComStyleCategory comStyleCategory, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comStyleCategory");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String parentId = comStyleCategory.getParentId();
		ComStyleCategory parentComStyleCategory = comStyleCategoryService.findById(parentId);
		if(!"0".equals(parentId) && parentComStyleCategory==null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveName(comStyleCategory.getProductId(), "", comStyleCategory.getStyleCategoryName());
		if(MapleUtil.notEmptyList(comStyleCategoryList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comStyleCategoryService.add(comStyleCategory);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String styleCategoryId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComStyleCategory comStyleCategory = comStyleCategoryService.findById(styleCategoryId);	//根据ID读取
		if(comStyleCategory == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comStyleCategory);
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategory/bgStyleCategoryEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComStyleCategory comStyleCategory, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comStyleCategory");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveName(comStyleCategory.getProductId(), comStyleCategory.getStyleCategoryId(), comStyleCategory.getStyleCategoryName());	
		if(MapleUtil.notEmptyList(comStyleCategoryList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comStyleCategoryService.edit(comStyleCategory);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在styleCategoryName
	 */
	@RequestMapping(value="/otherNotName")
	@ResponseBody
	public Object otherNotName(@RequestParam String productId, @RequestParam String styleCategoryId, @RequestParam String styleCategoryName) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveName(productId, styleCategoryId, styleCategoryName);	
		if(MapleUtil.emptyList(comStyleCategoryList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String styleCategoryId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comStyleCategoryService.deleteInRank(styleCategoryId);	//根据ID删除
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
		comStyleCategoryService.batchDeleteInRank(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String styleCategoryId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comStyleCategoryService.changeEffective(flag, styleCategoryId);	
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
		titles.add("规格分类 主键id");		//0
		titles.add("上级 id");					//1
		titles.add("产品编号");	//2
		titles.add("规格分类名称");	//3
		titles.add("是否最终分类");	//4
		titles.add("排序编号");	//5
		titles.add("有效标志");	//6
		titles.add("创建人员id");	//7
		titles.add("创建时间");	//8
		titles.add("修改人员id");	//9
		titles.add("修改时间");	//10
		dataMap.put("titles", titles);
		List<ComStyleCategory> varOList = comStyleCategoryService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getStyleCategoryId());			//0
			vpd.put("var1",varOList.get(i).getParentId());						//1
			vpd.put("var2", varOList.get(i).getProductId());	//2
			vpd.put("var3", varOList.get(i).getStyleCategoryName());	//3
			vpd.put("var4", varOList.get(i).getIsFinal());	//4
			vpd.put("var5", varOList.get(i).getOrderNum());		//5
			vpd.put("var6", varOList.get(i).getEffective());	//6
			vpd.put("var7", varOList.get(i).getCreateUserId());	//7
			vpd.put("var8", varOList.get(i).getCreateTime());	//8
			vpd.put("var9", varOList.get(i).getModifyUserId());//9
			vpd.put("var10", varOList.get(i).getModifyTime());	//10
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
			
		ComStyleCategory parentComStyleCategory = comStyleCategoryService.findById(pId);
		if(!"0".equals(pId) && parentComStyleCategory==null){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		mv.addObject("pId",pId);

		mv.addObject("controllerPath", "background_styleCategory");
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
		titles.add("产品编号");	//0
		titles.add("规格分类名称");	//1
		titles.add("是否最终分类");	//2
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
		String filePath = PathUtil.getProjectPath() + Const.FILEPATHFILE;								//文件上传路径
		String fileName =  MapleFileUtil.fileUp(file, filePath, "styleCategoryexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		String pId = MapleStringUtil.isEmpty(pd.getString("pId"))?"0":pd.getString("pId");//上级id
		comStyleCategory.setParentId(pId);
				
		/**
		 * var0 :产品编号;	//0
		 * var1 :规格分类名称;	//1
		 * var2 :是否最终分类;	//2
		 */
		for(int i=0;i<listPd.size();i++){	
			comStyleCategory.setStyleCategoryId(this.get32UUID());
			comStyleCategory.setProductId(listPd.get(i).getString("var0"));
			comStyleCategory.setStyleCategoryName(listPd.get(i).getString("var1"));
			comStyleCategory.setIsFinal(listPd.get(i).getString("var2"));
			comStyleCategoryService.add(comStyleCategory);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
