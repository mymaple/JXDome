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
import com.jx.common.entity.ComOrderProduct;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComOrderProductService;

/** 
 * 类名称：BgOrderProductController
 * 创建人：maple
 * 创建时间：2017-03-11
 */
@Controller
@RequestMapping(value="/background/orderProduct")
public class BgOrderProductController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_orderProduct";
	
	@Resource(name="comOrderProductService")
	private ComOrderProductService comOrderProductService;
	
	
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
		List<PageData>	comOrderProductList = comOrderProductService.listPage(bgPage);	//列出comOrderProduct列表
		
		mv.addObject("comOrderProductList", comOrderProductList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderProduct/bgOrderProductList");

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
		
		ComOrderProduct comOrderProduct = new ComOrderProduct();
		comOrderProduct.setOrderId("");
		comOrderProduct.setProductId("");
		comOrderProduct.setProductName("");
		comOrderProduct.setSummary("");
		comOrderProduct.setProductStyleName("");
		comOrderProduct.setHeadImgSrc("");
		comOrderProduct.setOriginalPrice("0.00");
		comOrderProduct.setCurrentPrice("0.00");
		comOrderProduct.setCount("0");
		comOrderProduct.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comOrderProduct);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderProduct/bgOrderProductEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComOrderProduct comOrderProduct, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrderProduct");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		comOrderProductService.add(comOrderProduct);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String orderProductId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrderProduct comOrderProduct = comOrderProductService.findById(orderProductId);	//根据ID读取
		if(comOrderProduct == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comOrderProduct);
		resultInfo.setResultCode("success");
		mv.setViewName("background/orderProduct/bgOrderProductEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComOrderProduct comOrderProduct, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrderProduct");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		comOrderProductService.edit(comOrderProduct);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String orderProductId, @RequestParam String orderProductCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComOrderProduct> comOrderProductList = comOrderProductService.otherHaveCode(orderProductId, orderProductCode);	
		if(MapleUtil.emptyList(comOrderProductList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String orderProductId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comOrderProductService.deleteById(orderProductId);	//根据ID删除
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
		comOrderProductService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("订单商品 主键id");		//0
		titles.add("订单id");	//1
		titles.add("商品Id");	//2
		titles.add("商品名称");	//3
		titles.add("摘要");	//4
		titles.add("商品类型名称");	//5
		titles.add("产品头像");	//6
		titles.add("原价");	//7
		titles.add("现价");	//8
		titles.add("购买数量");	//9
		titles.add("排序编号");	//10
		titles.add("有效标志");	//11
		titles.add("创建人员id");	//12
		titles.add("创建时间");	//13
		titles.add("修改人员id");	//14
		titles.add("修改时间");	//15
		dataMap.put("titles", titles);
		List<ComOrderProduct> varOList = comOrderProductService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getOrderProductId());			//0
			vpd.put("var1", varOList.get(i).getOrderId());	//1
			vpd.put("var2", varOList.get(i).getProductId());	//2
			vpd.put("var3", varOList.get(i).getProductName());	//3
			vpd.put("var4", varOList.get(i).getSummary());	//4
			vpd.put("var5", varOList.get(i).getProductStyleName());	//5
			vpd.put("var6", varOList.get(i).getHeadImgSrc());	//6
			vpd.put("var7", varOList.get(i).getOriginalPrice());	//7
			vpd.put("var8", varOList.get(i).getCurrentPrice());	//8
			vpd.put("var9", varOList.get(i).getCount());	//9
			vpd.put("var10", varOList.get(i).getOrderNum());		//10
			vpd.put("var11", varOList.get(i).getEffective());	//11
			vpd.put("var12", varOList.get(i).getCreateUserId());	//12
			vpd.put("var13", varOList.get(i).getCreateTime());	//13
			vpd.put("var14", varOList.get(i).getModifyUserId());//14
			vpd.put("var15", varOList.get(i).getModifyTime());	//15
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
		
		mv.addObject("controllerPath", "background_orderProduct");
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
		titles.add("订单id");	//0
		titles.add("商品Id");	//1
		titles.add("商品名称");	//2
		titles.add("摘要");	//3
		titles.add("商品类型名称");	//4
		titles.add("产品头像");	//5
		titles.add("原价");	//6
		titles.add("现价");	//7
		titles.add("购买数量");	//8
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "orderProductexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComOrderProduct comOrderProduct = new ComOrderProduct();
				
		/**
		 * var0 :订单id;	//0
		 * var1 :商品Id;	//1
		 * var2 :商品名称;	//2
		 * var3 :摘要;	//3
		 * var4 :商品类型名称;	//4
		 * var5 :产品头像;	//5
		 * var6 :原价;	//6
		 * var7 :现价;	//7
		 * var8 :购买数量;	//8
		 */
		for(int i=0;i<listPd.size();i++){	
			comOrderProduct.setOrderProductId(this.get32UUID());
			comOrderProduct.setOrderId(listPd.get(i).getString("var0"));
			comOrderProduct.setProductId(listPd.get(i).getString("var1"));
			comOrderProduct.setProductName(listPd.get(i).getString("var2"));
			comOrderProduct.setSummary(listPd.get(i).getString("var3"));
			comOrderProduct.setProductStyleName(listPd.get(i).getString("var4"));
			comOrderProduct.setHeadImgSrc(listPd.get(i).getString("var5"));
			comOrderProduct.setOriginalPrice(listPd.get(i).getString("var6"));
			comOrderProduct.setCurrentPrice(listPd.get(i).getString("var7"));
			comOrderProduct.setCount(listPd.get(i).getString("var8"));
			comOrderProductService.add(comOrderProduct);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
