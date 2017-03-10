package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import com.jx.common.entity.ComProductStyle;
import com.jx.common.entity.ComStyleCategory;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComProductStyleService;
import com.jx.common.service.ComStyleCategoryService;

/** 
 * 类名称：BgProductStyleController
 * 创建人：maple
 * 创建时间：2017-02-24
 */
@Controller
@RequestMapping(value="/background/productStyle")
public class BgProductStyleController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_productStyle";
	
	@Resource(name="comProductStyleService")
	private ComProductStyleService comProductStyleService;
	
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
		List<PageData>	comProductStyleList = comProductStyleService.listPage(bgPage);	//列出comProductStyle列表
		
		mv.addObject("comProductStyleList", comProductStyleList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/productStyle/bgProductStyleList");

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
		
		String productId = StringUtils.isEmpty(pd.getString("productId"))?"":pd.getString("productId");
		
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.listByParentId(productId, "0");
		
		ComProductStyle comProductStyle = new ComProductStyle();
		comProductStyle.setProductId(productId);
		comProductStyle.setProductStyleCode("");
		comProductStyle.setProductStyleName("");
		comProductStyle.setProductStyleType("01");
		comProductStyle.setStockNum("0");
		comProductStyle.setOriginalPrice("0.00");
		comProductStyle.setCurType("01");
		comProductStyle.setDiscountRate("0.00");
		comProductStyle.setDiscountPrice("0.00");
		comProductStyle.setCurrentPrice("0.00");
		comProductStyle.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comProductStyle);
		mv.addObject("comStyleCategoryList", comStyleCategoryList);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/productStyle/bgProductStyleEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComProductStyle comProductStyle, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductStyle");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProductStyle> comProductStyleList = comProductStyleService.otherHaveCode("", comProductStyle.getProductStyleCode());
		if(MapleUtil.notEmptyList(comProductStyleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comProductStyle.setProductStyleType(comProductStyle.getProductStyleType().replaceAll(",", Const.REG_COM_SPLIT));
		
		comProductStyleService.add(comProductStyle);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String productStyleId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComProductStyle comProductStyle = comProductStyleService.findById(productStyleId);	//根据ID读取
		if(comProductStyle == null){
			mv.addObject(resultInfo);
			return mv;
		}
		List<ComStyleCategory> comStyleCategoryList = comStyleCategoryService.listByParentId(comProductStyle.getProductId(), "0");
		String[] productStyleType = comProductStyle.getProductStyleType().split(Const.REG_COM_SPLIT);
		for (int i = 0; i < comStyleCategoryList.size(); i++) {
			comStyleCategoryList.get(i).setStyleCategoryStatus(productStyleType[i]);
		}
		
		mv.addObject("methodPath", "edit");
		mv.addObject(comProductStyle);
		resultInfo.setResultCode("success");
		mv.setViewName("background/productStyle/bgProductStyleEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComProductStyle comProductStyle, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comProductStyle");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComProductStyle> comProductStyleList = comProductStyleService.otherHaveCode(comProductStyle.getProductStyleId(), comProductStyle.getProductStyleCode());	
		if(MapleUtil.notEmptyList(comProductStyleList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		comProductStyle.setProductStyleType(comProductStyle.getProductStyleType().replaceAll(",", Const.REG_COM_SPLIT));
		
		comProductStyleService.edit(comProductStyle);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String productStyleId, @RequestParam String productStyleCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComProductStyle> comProductStyleList = comProductStyleService.otherHaveCode(productStyleId, productStyleCode);	
		if(MapleUtil.emptyList(comProductStyleList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String productStyleId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comProductStyleService.deleteById(productStyleId);	//根据ID删除
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
		comProductStyleService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("产品规格 主键id");		//0
		titles.add("产品规格初始id");	//1
		titles.add("商品id");	//2
		titles.add("产品规格代号");	//3
		titles.add("产品规格名称");	//4
		titles.add("产品规格类型");	//5
		titles.add("产品规格状态");	//6
		titles.add("库存数量");	//7
		titles.add("原价");	//8
		titles.add("货币种类");	//9
		titles.add("折扣");	//10
		titles.add("折扣优惠");	//11
		titles.add("现价");	//12
		titles.add("排序编号");	//13
		titles.add("有效标志");	//14
		titles.add("创建人员id");	//15
		titles.add("创建时间");	//16
		titles.add("修改人员id");	//17
		titles.add("修改时间");	//18
		dataMap.put("titles", titles);
		List<ComProductStyle> varOList = comProductStyleService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getProductStyleId());			//0
			vpd.put("var1", varOList.get(i).getProductStyleInitId());	//1
			vpd.put("var2", varOList.get(i).getProductId());	//2
			vpd.put("var3", varOList.get(i).getProductStyleCode());	//3
			vpd.put("var4", varOList.get(i).getProductStyleName());	//4
			vpd.put("var5", varOList.get(i).getProductStyleType());	//5
			vpd.put("var6", varOList.get(i).getProductStyleStatus());	//6
			vpd.put("var7", varOList.get(i).getStockNum());	//7
			vpd.put("var8", varOList.get(i).getOriginalPrice());	//8
			vpd.put("var9", varOList.get(i).getCurType());	//9
			vpd.put("var10", varOList.get(i).getDiscountRate());	//10
			vpd.put("var11", varOList.get(i).getDiscountPrice());	//11
			vpd.put("var12", varOList.get(i).getCurrentPrice());	//12
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
		
		mv.addObject("controllerPath", "background_productStyle");
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
		titles.add("商品id");	//0
		titles.add("产品规格代号");	//1
		titles.add("产品规格名称");	//2
		titles.add("产品规格类型");	//3
		titles.add("库存数量");	//4
		titles.add("原价");	//5
		titles.add("货币种类");	//6
		titles.add("折扣");	//7
		titles.add("折扣优惠");	//8
		titles.add("现价");	//9
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "productStyleexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComProductStyle comProductStyle = new ComProductStyle();
				
		/**
		 * var0 :商品id;	//0
		 * var1 :产品规格代号;	//1
		 * var2 :产品规格名称;	//2
		 * var3 :产品规格类型;	//3
		 * var4 :库存数量;	//4
		 * var5 :原价;	//5
		 * var6 :货币种类;	//6
		 * var7 :折扣;	//7
		 * var8 :折扣优惠;	//8
		 * var9 :现价;	//9
		 */
		for(int i=0;i<listPd.size();i++){	
			comProductStyle.setProductStyleId(this.get32UUID());
			comProductStyle.setProductId(listPd.get(i).getString("var0"));
			comProductStyle.setProductStyleCode(listPd.get(i).getString("var1"));
			comProductStyle.setProductStyleName(listPd.get(i).getString("var2"));
			comProductStyle.setProductStyleType(listPd.get(i).getString("var3"));
			comProductStyle.setStockNum(listPd.get(i).getString("var4"));
			comProductStyle.setOriginalPrice(listPd.get(i).getString("var5"));
			comProductStyle.setCurType(listPd.get(i).getString("var6"));
			comProductStyle.setDiscountRate(listPd.get(i).getString("var7"));
			comProductStyle.setDiscountPrice(listPd.get(i).getString("var8"));
			comProductStyle.setCurrentPrice(listPd.get(i).getString("var9"));
			comProductStyleService.add(comProductStyle);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
