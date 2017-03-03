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
import com.jx.common.entity.ComProduct;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComProductService;

/** 
 * 类名称：BgProductController
 * 创建人：maple
 * 创建时间：2017-02-24
 */
@Controller
@RequestMapping(value="/background/product")
public class BgProductController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_product";
	
	@Resource(name="comProductService")
	private ComProductService comProductService;
	
	
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
		List<PageData>	comProductList = comProductService.listPage(bgPage);	//列出comProduct列表
		
		mv.addObject("comProductList", comProductList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/product/bgProductList");

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
		
		ComProduct comProduct = new ComProduct();
		comProduct.setSupplierId("");
		comProduct.setProductCode("");
		comProduct.setProductName("");
		comProduct.setProductType("01");
		comProduct.setProductModel("");
		comProduct.setSummary("");
		comProduct.setIntroduction("");
		comProduct.setHeadImgSrc("");
		comProduct.setImgSrc1("");
		comProduct.setImgSrc2("");
		comProduct.setImgSrc3("");
		comProduct.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comProduct);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/product/bgProductEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComProduct comProduct, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProduct");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProduct> comProductList = comProductService.otherHaveCode("", comProduct.getProductCode());
		if(MapleUtil.notEmptyList(comProductList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comProductService.add(comProduct);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String productId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComProduct comProduct = comProductService.findById(productId);	//根据ID读取
		if(comProduct == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comProduct);
		resultInfo.setResultCode("success");
		mv.setViewName("background/product/bgProductEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComProduct comProduct, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProduct");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProduct> comProductList = comProductService.otherHaveCode(comProduct.getProductId(), comProduct.getProductCode());	
		if(MapleUtil.notEmptyList(comProductList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comProductService.edit(comProduct);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String productId, @RequestParam String productCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComProduct> comProductList = comProductService.otherHaveCode(productId, productCode);	
		if(MapleUtil.emptyList(comProductList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String productId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comProductService.deleteById(productId);	//根据ID删除
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
		comProductService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("产品 主键id");		//0
		titles.add("供应商Id");	//1
		titles.add("产品代号");	//2
		titles.add("产品名称");	//3
		titles.add("产品类型");	//4
		titles.add("产品状态");	//5
		titles.add("产品模型");	//6
		titles.add("摘要");	//7
		titles.add("简介");	//8
		titles.add("产品头像");	//9
		titles.add("长框图");	//10
		titles.add("滚播图");	//11
		titles.add("详情图");	//12
		titles.add("排序编号");	//13
		titles.add("有效标志");	//14
		titles.add("创建人员id");	//15
		titles.add("创建时间");	//16
		titles.add("修改人员id");	//17
		titles.add("修改时间");	//18
		dataMap.put("titles", titles);
		List<ComProduct> varOList = comProductService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getProductId());			//0
			vpd.put("var1", varOList.get(i).getSupplierId());	//1
			vpd.put("var2", varOList.get(i).getProductCode());	//2
			vpd.put("var3", varOList.get(i).getProductName());	//3
			vpd.put("var4", varOList.get(i).getProductType());	//4
			vpd.put("var5", varOList.get(i).getProductStatus());	//5
			vpd.put("var6", varOList.get(i).getProductModel());	//6
			vpd.put("var7", varOList.get(i).getSummary());	//7
			vpd.put("var8", varOList.get(i).getIntroduction());	//8
			vpd.put("var9", varOList.get(i).getHeadImgSrc());	//9
			vpd.put("var10", varOList.get(i).getImgSrc1());	//10
			vpd.put("var11", varOList.get(i).getImgSrc2());	//11
			vpd.put("var12", varOList.get(i).getImgSrc3());	//12
			vpd.put("var13", varOList.get(i).getOrderNum());		//13
			vpd.put("var14", varOList.get(i).getEffective());	//14
			vpd.put("var15", varOList.get(i).getCreateUserId());	//15
			vpd.put("var16", varOList.get(i).getCreateTime());	//16
			vpd.put("var17", varOList.get(i).getModifyUserId());//17
			vpd.put("var18", varOList.get(i).getModifyTime());	//18
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
		
		mv.addObject("controllerPath", "background_product");
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
		titles.add("供应商Id");	//0
		titles.add("产品代号");	//1
		titles.add("产品名称");	//2
		titles.add("产品类型");	//3
		titles.add("产品模型");	//4
		titles.add("摘要");	//5
		titles.add("简介");	//6
		titles.add("产品头像");	//7
		titles.add("长框图");	//8
		titles.add("滚播图");	//9
		titles.add("详情图");	//10
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "productexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComProduct comProduct = new ComProduct();
				
		/**
		 * var0 :供应商Id;	//0
		 * var1 :产品代号;	//1
		 * var2 :产品名称;	//2
		 * var3 :产品类型;	//3
		 * var4 :产品模型;	//4
		 * var5 :摘要;	//5
		 * var6 :简介;	//6
		 * var7 :产品头像;	//7
		 * var8 :长框图;	//8
		 * var9 :滚播图;	//9
		 * var10 :详情图;	//10
		 */
		for(int i=0;i<listPd.size();i++){	
			comProduct.setProductId(this.get32UUID());
			comProduct.setSupplierId(listPd.get(i).getString("var0"));
			comProduct.setProductCode(listPd.get(i).getString("var1"));
			comProduct.setProductName(listPd.get(i).getString("var2"));
			comProduct.setProductType(listPd.get(i).getString("var3"));
			comProduct.setProductModel(listPd.get(i).getString("var4"));
			comProduct.setSummary(listPd.get(i).getString("var5"));
			comProduct.setIntroduction(listPd.get(i).getString("var6"));
			comProduct.setHeadImgSrc(listPd.get(i).getString("var7"));
			comProduct.setImgSrc1(listPd.get(i).getString("var8"));
			comProduct.setImgSrc2(listPd.get(i).getString("var9"));
			comProduct.setImgSrc3(listPd.get(i).getString("var10"));
			comProductService.add(comProduct);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
