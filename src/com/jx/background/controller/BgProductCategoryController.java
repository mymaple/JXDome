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
import com.jx.common.entity.ComProductCategory;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComProductCategoryService;

/** 
 * 类名称：BgProductCategoryController
 * 创建人：maple
 * 创建时间：2017-03-21
 */
@Controller
@RequestMapping(value="/background/productCategory")
public class BgProductCategoryController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_productCategory";
	
	@Resource(name="comProductCategoryService")
	private ComProductCategoryService comProductCategoryService;
	
	
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
		List<PageData>	comProductCategoryList = comProductCategoryService.listPage(bgPage);	//列出comProductCategory列表
		
		mv.addObject("comProductCategoryList", comProductCategoryList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategory/bgProductCategoryList");

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
		
		ComProductCategory comProductCategory = new ComProductCategory();
		comProductCategory.setProductCategoryCode("");
		comProductCategory.setProductCategoryName("");
		comProductCategory.setProductCategoryType("01");
		comProductCategory.setHeadImgSrc("");
		comProductCategory.setImgSrc1("");
		comProductCategory.setImgSrc2("");
		comProductCategory.setSummary("");
		comProductCategory.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comProductCategory);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategory/bgProductCategoryEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComProductCategory comProductCategory, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductCategory");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProductCategory> comProductCategoryList = comProductCategoryService.otherHaveCode("", comProductCategory.getProductCategoryCode());
		if(MapleUtil.notEmptyList(comProductCategoryList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comProductCategoryService.add(comProductCategory);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String productCategoryId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComProductCategory comProductCategory = comProductCategoryService.findById(productCategoryId);	//根据ID读取
		if(comProductCategory == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comProductCategory);
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategory/bgProductCategoryEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComProductCategory comProductCategory, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductCategory");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProductCategory> comProductCategoryList = comProductCategoryService.otherHaveCode(comProductCategory.getProductCategoryId(), comProductCategory.getProductCategoryCode());	
		if(MapleUtil.notEmptyList(comProductCategoryList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comProductCategoryService.edit(comProductCategory);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String productCategoryId, @RequestParam String productCategoryCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComProductCategory> comProductCategoryList = comProductCategoryService.otherHaveCode(productCategoryId, productCategoryCode);	
		if(MapleUtil.emptyList(comProductCategoryList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String productCategoryId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comProductCategoryService.deleteById(productCategoryId);	//根据ID删除
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
		comProductCategoryService.batchDeleteByIds(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改状态
	 */
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public Object changeStatus(@RequestParam String flag, @RequestParam String productCategoryId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comProductCategoryService.changeStatus(flag, productCategoryId);	
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String productCategoryId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comProductCategoryService.changeEffective(flag, productCategoryId);	
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
		titles.add("商品分类 主键id");		//0
		titles.add("商品分类代号");	//1
		titles.add("商品分类名称");	//2
		titles.add("商品分类类型");	//3
		titles.add("商品分类状态");	//4
		titles.add("分类头像");	//5
		titles.add("长框图");	//6
		titles.add("滚播图");	//7
		titles.add("摘要");	//8
		titles.add("排序编号");	//9
		titles.add("有效标志");	//10
		titles.add("创建人员id");	//11
		titles.add("创建时间");	//12
		titles.add("修改人员id");	//13
		titles.add("修改时间");	//14
		dataMap.put("titles", titles);
		List<ComProductCategory> varOList = comProductCategoryService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getProductCategoryId());			//0
			vpd.put("var1", varOList.get(i).getProductCategoryCode());	//1
			vpd.put("var2", varOList.get(i).getProductCategoryName());	//2
			vpd.put("var3", varOList.get(i).getProductCategoryType());	//3
			vpd.put("var4", varOList.get(i).getProductCategoryStatus());	//4
			vpd.put("var5", varOList.get(i).getHeadImgSrc());	//5
			vpd.put("var6", varOList.get(i).getImgSrc1());	//6
			vpd.put("var7", varOList.get(i).getImgSrc2());	//7
			vpd.put("var8", varOList.get(i).getSummary());	//8
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
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		mv.addObject("controllerPath", "background_productCategory");
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
		titles.add("商品分类代号");	//0
		titles.add("商品分类名称");	//1
		titles.add("商品分类类型");	//2
		titles.add("分类头像");	//3
		titles.add("长框图");	//4
		titles.add("滚播图");	//5
		titles.add("摘要");	//6
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "productCategoryexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComProductCategory comProductCategory = new ComProductCategory();
				
		/**
		 * var0 :商品分类代号;	//0
		 * var1 :商品分类名称;	//1
		 * var2 :商品分类类型;	//2
		 * var3 :分类头像;	//3
		 * var4 :长框图;	//4
		 * var5 :滚播图;	//5
		 * var6 :摘要;	//6
		 */
		for(int i=0;i<listPd.size();i++){	
			comProductCategory.setProductCategoryId(this.get32UUID());
			comProductCategory.setProductCategoryCode(listPd.get(i).getString("var0"));
			comProductCategory.setProductCategoryName(listPd.get(i).getString("var1"));
			comProductCategory.setProductCategoryType(listPd.get(i).getString("var2"));
			comProductCategory.setHeadImgSrc(listPd.get(i).getString("var3"));
			comProductCategory.setImgSrc1(listPd.get(i).getString("var4"));
			comProductCategory.setImgSrc2(listPd.get(i).getString("var5"));
			comProductCategory.setSummary(listPd.get(i).getString("var6"));
			comProductCategoryService.add(comProductCategory);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
