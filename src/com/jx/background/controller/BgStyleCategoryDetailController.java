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
import com.jx.common.entity.ComStyleCategoryDetail;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComStyleCategoryService;
import com.jx.common.service.ComStyleCategoryDetailService;

/** 
 * 类名称：BgStyleCategoryDetailController
 * 创建人：maple
 * 创建时间：2017-02-24
 */
@Controller
@RequestMapping(value="/background/styleCategoryDetail")
public class BgStyleCategoryDetailController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_styleCategory";
	
	@Resource(name="comStyleCategoryDetailService")
	private ComStyleCategoryDetailService comStyleCategoryDetailService;
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
		
		String styleCategoryId = pd.getString("styleCategoryId");								//规格分类 id 
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comStyleCategoryDetailList = comStyleCategoryDetailService.listPage(bgPage);	//列出comStyleCategoryDetail列表
		
		mv.addObject("comStyleCategoryDetailList", comStyleCategoryDetailList);
		mv.addObject("comStyleCategory", comStyleCategoryService.findById(styleCategoryId ));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategoryDetail/bgStyleCategoryDetailList");

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
		
		String styleCategoryId = pd.getString("styleCategoryId");
		if(comStyleCategoryService.findById(styleCategoryId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComStyleCategoryDetail comStyleCategoryDetail = new ComStyleCategoryDetail();
		comStyleCategoryDetail.setStyleCategoryId(styleCategoryId);
		comStyleCategoryDetail.setStyleCategoryDetailCode("");
		comStyleCategoryDetail.setStyleCategoryDetailName("");
		comStyleCategoryDetail.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comStyleCategoryDetail);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategoryDetail/bgStyleCategoryDetailEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComStyleCategoryDetail comStyleCategoryDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comStyleCategoryDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String styleCategoryId = comStyleCategoryDetail.getStyleCategoryId();
		if(comStyleCategoryService.findById(styleCategoryId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComStyleCategoryDetail> comStyleCategoryDetailList = comStyleCategoryDetailService.otherHaveCode("", comStyleCategoryDetail.getStyleCategoryDetailCode());
		if(MapleUtil.notEmptyList(comStyleCategoryDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comStyleCategoryDetailService.add(comStyleCategoryDetail);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String styleCategoryDetailId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComStyleCategoryDetail comStyleCategoryDetail = comStyleCategoryDetailService.findById(styleCategoryDetailId);	//根据ID读取
		if(comStyleCategoryDetail == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comStyleCategoryDetail);
		resultInfo.setResultCode("success");
		mv.setViewName("background/styleCategoryDetail/bgStyleCategoryDetailEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComStyleCategoryDetail comStyleCategoryDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comStyleCategoryDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComStyleCategoryDetail> comStyleCategoryDetailList = comStyleCategoryDetailService.otherHaveCode(comStyleCategoryDetail.getStyleCategoryDetailId(), comStyleCategoryDetail.getStyleCategoryDetailCode());	
		if(MapleUtil.notEmptyList(comStyleCategoryDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comStyleCategoryDetailService.edit(comStyleCategoryDetail);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String styleCategoryDetailId, @RequestParam String styleCategoryDetailCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComStyleCategoryDetail> comStyleCategoryDetailList = comStyleCategoryDetailService.otherHaveCode(styleCategoryDetailId, styleCategoryDetailCode);	
		if(MapleUtil.emptyList(comStyleCategoryDetailList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String styleCategoryDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comStyleCategoryDetailService.deleteById(styleCategoryDetailId);	//根据ID删除
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
		comStyleCategoryDetailService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("规格分类详情 主键id");		//0
		titles.add("规格分类 id");	//1
		titles.add("规格分类详情代号");	//2
		titles.add("规格分类详情名称");	//3
		titles.add("规格分类详情状态");	//4
		titles.add("排序编号");	//5
		titles.add("有效标志");	//6
		titles.add("创建人员id");	//7
		titles.add("创建时间");	//8
		titles.add("修改人员id");	//9
		titles.add("修改时间");	//10
		dataMap.put("titles", titles);
		List<ComStyleCategoryDetail> varOList = comStyleCategoryDetailService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getStyleCategoryDetailId());			//0
			vpd.put("var1",varOList.get(i).getStyleCategoryId());	//1
			vpd.put("var2", varOList.get(i).getStyleCategoryDetailCode());	//2
			vpd.put("var3", varOList.get(i).getStyleCategoryDetailName());	//3
			vpd.put("var4", varOList.get(i).getStyleCategoryDetailStatus());	//4
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
		
		String pId = pd.getString("pId");//上级id
				
		if(comStyleCategoryService.findById(pId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}	
			
		mv.addObject("pId",pId);
		mv.addObject("controllerPath", "background_styleCategoryDetail");
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
		titles.add("规格分类详情代号");	//0
		titles.add("规格分类详情名称");	//1
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "styleCategoryDetailexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComStyleCategoryDetail comStyleCategoryDetail = new ComStyleCategoryDetail();
		String styleCategoryId = pd.getString("pId");//上级id	
		comStyleCategoryDetail.setStyleCategoryId(styleCategoryId);
				
		/**
		 * var0 :规格分类详情代号;	//0
		 * var1 :规格分类详情名称;	//1
		 */
		for(int i=0;i<listPd.size();i++){	
			comStyleCategoryDetail.setStyleCategoryDetailId(this.get32UUID());
			comStyleCategoryDetail.setStyleCategoryDetailCode(listPd.get(i).getString("var0"));
			comStyleCategoryDetail.setStyleCategoryDetailName(listPd.get(i).getString("var1"));
			comStyleCategoryDetailService.add(comStyleCategoryDetail);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
