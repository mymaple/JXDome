package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
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

/** 
 * 类名称：BgStyleCategoryController
 * 创建人：maple
 * 创建时间：2017-02-24
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
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comStyleCategoryList = comStyleCategoryService.listPage(bgPage);	//列出comStyleCategory列表
		
		mv.addObject("comStyleCategoryList", comStyleCategoryList);
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComStyleCategory comStyleCategory = new ComStyleCategory();
		comStyleCategory.setProductId("");
		comStyleCategory.setStyleCategoryCode("");
		comStyleCategory.setStyleCategoryName("");
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
		
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveCode("", comStyleCategory.getStyleCategoryCode());
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
		
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveCode(comStyleCategory.getStyleCategoryId(), comStyleCategory.getStyleCategoryCode());	
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
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String styleCategoryId, @RequestParam String styleCategoryCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.otherHaveCode(styleCategoryId, styleCategoryCode);	
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
		
		comStyleCategoryService.deleteById(styleCategoryId);	//根据ID删除
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
		comStyleCategoryService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("规格分类 主键id");		//0
		titles.add("产品编号");	//1
		titles.add("规格分类代号");	//2
		titles.add("规格分类名称");	//3
		titles.add("规格分类状态");	//4
		titles.add("排序编号");	//5
		titles.add("有效标志");	//6
		titles.add("创建人员id");	//7
		titles.add("创建时间");	//8
		titles.add("修改人员id");	//9
		titles.add("修改时间");	//10
		dataMap.put("titles", titles);
		List<ComStyleCategory> varOList = comStyleCategoryService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getStyleCategoryId());			//0
			vpd.put("var1", varOList.get(i).getProductId());	//1
			vpd.put("var2", varOList.get(i).getStyleCategoryCode());	//2
			vpd.put("var3", varOList.get(i).getStyleCategoryName());	//3
			vpd.put("var4", varOList.get(i).getStyleCategoryStatus());	//4
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
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
		titles.add("规格分类代号");	//1
		titles.add("规格分类名称");	//2
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
		//PageData pd = this.getPageData();
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
				
		/**
		 * var0 :产品编号;	//0
		 * var1 :规格分类代号;	//1
		 * var2 :规格分类名称;	//2
		 */
		for(int i=0;i<listPd.size();i++){	
			comStyleCategory.setStyleCategoryId(this.get32UUID());
			comStyleCategory.setProductId(listPd.get(i).getString("var0"));
			comStyleCategory.setStyleCategoryCode(listPd.get(i).getString("var1"));
			comStyleCategory.setStyleCategoryName(listPd.get(i).getString("var2"));
			comStyleCategoryService.add(comStyleCategory);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
