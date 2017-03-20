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
import com.jx.common.entity.ComProductCategoryDetail;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComProductCategoryService;
import com.jx.common.service.ComProductCategoryDetailService;

/** 
 * 类名称：BgProductCategoryDetailController
 * 创建人：maple
 * 创建时间：2017-03-20
 */
@Controller
@RequestMapping(value="/background/productCategoryDetail")
public class BgProductCategoryDetailController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_productCategory";
	
	@Resource(name="comProductCategoryDetailService")
	private ComProductCategoryDetailService comProductCategoryDetailService;
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
		
		String productCategoryId = pd.getString("productCategoryId");								//商品分类 id 
		String keywords = pd.getString("keywords");								//关键词检索条件
		if(MapleStringUtil.notEmpty(keywords)){
			pd.put("keywords", keywords.trim());
		}
			
		bgPage.setPd(pd);
		List<PageData>	comProductCategoryDetailList = comProductCategoryDetailService.listPage(bgPage);	//列出comProductCategoryDetail列表
		
		mv.addObject("comProductCategoryDetailList", comProductCategoryDetailList);
		mv.addObject("comProductCategory", comProductCategoryService.findById(productCategoryId ));
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategoryDetail/bgProductCategoryDetailList");

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
		
		String productCategoryId = pd.getString("productCategoryId");
		if(comProductCategoryService.findById(productCategoryId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		comProductCategoryDetail.setProductCategoryId(productCategoryId);
		comProductCategoryDetail.setProductId("");
		comProductCategoryDetail.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comProductCategoryDetail);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategoryDetail/bgProductCategoryDetailEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComProductCategoryDetail comProductCategoryDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductCategoryDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		String productCategoryId = comProductCategoryDetail.getProductCategoryId();
		if(comProductCategoryService.findById(productCategoryId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		List<ComProductCategoryDetail> comProductCategoryDetailList = comProductCategoryDetailService.otherHaveCode("", comProductCategoryDetail.getProductCategoryDetailCode());
		if(MapleUtil.notEmptyList(comProductCategoryDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comProductCategoryDetailService.add(comProductCategoryDetail);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String productCategoryDetailId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComProductCategoryDetail comProductCategoryDetail = comProductCategoryDetailService.findById(productCategoryDetailId);	//根据ID读取
		if(comProductCategoryDetail == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comProductCategoryDetail);
		resultInfo.setResultCode("success");
		mv.setViewName("background/productCategoryDetail/bgProductCategoryDetailEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComProductCategoryDetail comProductCategoryDetail, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductCategoryDetail");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProductCategoryDetail> comProductCategoryDetailList = comProductCategoryDetailService.otherHaveCode(comProductCategoryDetail.getProductCategoryDetailId(), comProductCategoryDetail.getProductCategoryDetailCode());	
		if(MapleUtil.notEmptyList(comProductCategoryDetailList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comProductCategoryDetailService.edit(comProductCategoryDetail);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String productCategoryDetailId, @RequestParam String productCategoryDetailCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComProductCategoryDetail> comProductCategoryDetailList = comProductCategoryDetailService.otherHaveCode(productCategoryDetailId, productCategoryDetailCode);	
		if(MapleUtil.emptyList(comProductCategoryDetailList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String productCategoryDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comProductCategoryDetailService.deleteById(productCategoryDetailId);	//根据ID删除
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
		comProductCategoryDetailService.batchDeleteByIds(ids.split(","));	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toEffective")
	@ResponseBody
	public Object toEffective(@RequestParam String flag, @RequestParam String productCategoryDetailId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		if("00".equals(flag)){
			comProductCategoryDetailService.toEffective(productCategoryDetailId);	//根据ID删除
			resultInfo.setResultCode("success");
		}else if("01".equals(flag)){
			comProductCategoryDetailService.toDisEffective(productCategoryDetailId);	//根据ID删除
			resultInfo.setResultCode("success");
		}else{
			resultInfo.setResultContent("参数异常！");
		}
		
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
		titles.add("商品分类详情 主键id");		//0
		titles.add("商品分类 id");	//1
		titles.add("商品");	//2
		titles.add("排序编号");	//3
		titles.add("有效标志");	//4
		titles.add("创建人员id");	//5
		titles.add("创建时间");	//6
		titles.add("修改人员id");	//7
		titles.add("修改时间");	//8
		dataMap.put("titles", titles);
		List<ComProductCategoryDetail> varOList = comProductCategoryDetailService.list(null);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getProductCategoryDetailId());			//0
			vpd.put("var1",varOList.get(i).getProductCategoryId());	//1
			vpd.put("var2", varOList.get(i).getProductId());	//2
			vpd.put("var3", varOList.get(i).getOrderNum());		//3
			vpd.put("var4", varOList.get(i).getEffective());	//4
			vpd.put("var5", varOList.get(i).getCreateUserId());	//5
			vpd.put("var6", varOList.get(i).getCreateTime());	//6
			vpd.put("var7", varOList.get(i).getModifyUserId());//7
			vpd.put("var8", varOList.get(i).getModifyTime());	//8
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
				
		if(comProductCategoryService.findById(pId) == null){
			mv.addObject(resultInfo);					
			return mv;
		}	
			
		mv.addObject("pId",pId);
		mv.addObject("controllerPath", "background_productCategoryDetail");
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
		titles.add("商品");	//0
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "productCategoryDetailexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComProductCategoryDetail comProductCategoryDetail = new ComProductCategoryDetail();
		String productCategoryId = pd.getString("pId");//上级id	
		comProductCategoryDetail.setProductCategoryId(productCategoryId);
				
		/**
		 * var0 :商品;	//0
		 */
		for(int i=0;i<listPd.size();i++){	
			comProductCategoryDetail.setProductCategoryDetailId(this.get32UUID());
			comProductCategoryDetail.setProductId(listPd.get(i).getString("var0"));
			comProductCategoryDetailService.add(comProductCategoryDetail);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
