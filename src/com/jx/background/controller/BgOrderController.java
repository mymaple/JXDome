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
import com.jx.common.entity.ComOrder;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComOrderService;

/** 
 * 类名称：BgOrderController
 * 创建人：maple
 * 创建时间：2017-03-11
 */
@Controller
@RequestMapping(value="/background/order")
public class BgOrderController extends BaseController {
	
	/**
	 * 后台 菜单代号(权限用)
	 */
	public static final String RIGHTS_BG_MENUCODE_STR = "background_order";
	
	@Resource(name="comOrderService")
	private ComOrderService comOrderService;
	
	
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
		List<PageData>	comOrderList = comOrderService.listPage(bgPage);	//列出comOrder列表
		
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pd", pd);
		mv.addObject("RIGHTS", BgSessionUtil.getSessionBgRights());				//按钮权限
		resultInfo.setResultCode("success");
		mv.setViewName("background/order/bgOrderList");

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
		
		ComOrder comOrder = new ComOrder();
		comOrder.setOrderCode("");
		comOrder.setOrderName("");
		comOrder.setOrderType("01");
		comOrder.setOrderProductCount("0");
		comOrder.setAllPrice("0.00");
		comOrder.setFreight("0.00");
		comOrder.setAllDisPrice("0.00");
		comOrder.setWalletPay("0.00");
		comOrder.setAllActPrice("0.00");
		comOrder.setSupplierId("");
		comOrder.setReceiveAddressId("");
		comOrder.setTradeNum("");
		comOrder.setPayMethod("");
		comOrder.setWlgs("");
		comOrder.setWlNum("");
		comOrder.setOrderNum(String.valueOf(new Date().getTime()));
		
		mv.addObject(comOrder);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/order/bgOrderEdit");
			
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComOrder comOrder, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrder");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComOrder> comOrderList = comOrderService.otherHaveCode("", comOrder.getOrderCode());
		if(MapleUtil.notEmptyList(comOrderList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comOrderService.add(comOrder);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String orderId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrder comOrder = comOrderService.findById(orderId);	//根据ID读取
		if(comOrder == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comOrder);
		resultInfo.setResultCode("success");
		mv.setViewName("background/order/bgOrderEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationEdit.class) ComOrder comOrder, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
			
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comOrder");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComOrder> comOrderList = comOrderService.otherHaveCode(comOrder.getOrderId(), comOrder.getOrderCode());	
		if(MapleUtil.notEmptyList(comOrderList)){
			mv.addObject(resultInfo);					
			return mv;
		}
		
		comOrderService.edit(comOrder);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 判断是否存在dictCode
	 */
	@RequestMapping(value="/otherNotCode")
	@ResponseBody
	public Object otherNotCode(@RequestParam String orderId, @RequestParam String orderCode) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();

		List<ComOrder> comOrderList = comOrderService.otherHaveCode(orderId, orderCode);	
		if(MapleUtil.emptyList(comOrderList)){
			resultInfo.setResultCode("success");
		}

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		comOrderService.deleteById(orderId);	//根据ID删除
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
		comOrderService.batchDeleteByIds(ids.split(","));	//根据ID删除
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
		titles.add("订单 主键id");		//0
		titles.add("订单代号");	//1
		titles.add("订单名称");	//2
		titles.add("订单类型");	//3
		titles.add("订单状态");	//4
		titles.add("订单商品总数");	//5
		titles.add("商品总价");	//6
		titles.add("运费");	//7
		titles.add("总优惠");	//8
		titles.add("钱包支付");	//9
		titles.add("实付款");	//10
		titles.add("供应商");	//11
		titles.add("编号");	//12
		titles.add("收货地址");	//13
		titles.add("付款时间");	//14
		titles.add("发货时间");	//15
		titles.add("交易号");	//16
		titles.add("付款方式");	//17
		titles.add("物流公司");	//18
		titles.add("运单编号");	//19
		titles.add("排序编号");	//20
		titles.add("有效标志");	//21
		titles.add("创建人员id");	//22
		titles.add("创建时间");	//23
		titles.add("修改人员id");	//24
		titles.add("修改时间");	//25
		dataMap.put("titles", titles);
		List<ComOrder> varOList = comOrderService.listByPd(pd);
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getOrderId());			//0
			vpd.put("var1", varOList.get(i).getOrderCode());	//1
			vpd.put("var2", varOList.get(i).getOrderName());	//2
			vpd.put("var3", varOList.get(i).getOrderType());	//3
			vpd.put("var4", varOList.get(i).getOrderStatus());	//4
			vpd.put("var5", varOList.get(i).getOrderProductCount());	//5
			vpd.put("var6", varOList.get(i).getAllPrice());	//6
			vpd.put("var7", varOList.get(i).getFreight());	//7
			vpd.put("var8", varOList.get(i).getAllDisPrice());	//8
			vpd.put("var9", varOList.get(i).getWalletPay());	//9
			vpd.put("var10", varOList.get(i).getAllActPrice());	//10
			vpd.put("var11", varOList.get(i).getSupplierName());	//11
			vpd.put("var12", varOList.get(i).getSupplierId());	//12
			vpd.put("var13", varOList.get(i).getReceiveAddressId());	//13
			vpd.put("var14", varOList.get(i).getPayTimeStr());	//14
			vpd.put("var15", varOList.get(i).getSendTimeStr());	//15
			vpd.put("var16", varOList.get(i).getTradeNum());	//16
			vpd.put("var17", varOList.get(i).getPayMethod());	//17
			vpd.put("var18", varOList.get(i).getWlgs());	//18
			vpd.put("var19", varOList.get(i).getWlNum());	//19
			vpd.put("var20", varOList.get(i).getOrderNum());		//20
			vpd.put("var21", varOList.get(i).getEffective());	//21
			vpd.put("var22", varOList.get(i).getCreateUserId());	//22
			vpd.put("var23", varOList.get(i).getCreateTime());	//23
			vpd.put("var24", varOList.get(i).getModifyUserId());//24
			vpd.put("var25", varOList.get(i).getModifyTime());	//25
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
		
		mv.addObject("controllerPath", "background_order");
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
		titles.add("订单代号");	//0
		titles.add("订单名称");	//1
		titles.add("订单类型");	//2
		titles.add("订单商品总数");	//3
		titles.add("商品总价");	//4
		titles.add("运费");	//5
		titles.add("总优惠");	//6
		titles.add("钱包支付");	//7
		titles.add("实付款");	//8
		titles.add("编号");	//9
		titles.add("收货地址");	//10
		titles.add("交易号");	//11
		titles.add("付款方式");	//12
		titles.add("物流公司");	//13
		titles.add("运单编号");	//14
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
		String fileName =  MapleFileUtil.fileUp(file, filePath, "orderexcel");		//执行上传
		List<PageData> listPd = (List)ObjectExcelView.readExcel(filePath, fileName, 1, 0, 0);		//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
		/*存入数据库操作======================================*/
		
		ComOrder comOrder = new ComOrder();
				
		/**
		 * var0 :订单代号;	//0
		 * var1 :订单名称;	//1
		 * var2 :订单类型;	//2
		 * var3 :订单商品总数;	//3
		 * var4 :商品总价;	//4
		 * var5 :运费;	//5
		 * var6 :总优惠;	//6
		 * var7 :钱包支付;	//7
		 * var8 :实付款;	//8
		 * var9 :编号;	//9
		 * var10 :收货地址;	//10
		 * var11 :交易号;	//11
		 * var12 :付款方式;	//12
		 * var13 :物流公司;	//13
		 * var14 :运单编号;	//14
		 */
		for(int i=0;i<listPd.size();i++){	
			comOrder.setOrderId(this.get32UUID());
			comOrder.setOrderCode(listPd.get(i).getString("var0"));
			comOrder.setOrderName(listPd.get(i).getString("var1"));
			comOrder.setOrderType(listPd.get(i).getString("var2"));
			comOrder.setOrderProductCount(listPd.get(i).getString("var3"));
			comOrder.setAllPrice(listPd.get(i).getString("var4"));
			comOrder.setFreight(listPd.get(i).getString("var5"));
			comOrder.setAllDisPrice(listPd.get(i).getString("var6"));
			comOrder.setWalletPay(listPd.get(i).getString("var7"));
			comOrder.setAllActPrice(listPd.get(i).getString("var8"));
			comOrder.setSupplierId(listPd.get(i).getString("var9"));
			comOrder.setReceiveAddressId(listPd.get(i).getString("var10"));
			comOrder.setTradeNum(listPd.get(i).getString("var11"));
			comOrder.setPayMethod(listPd.get(i).getString("var12"));
			comOrder.setWlgs(listPd.get(i).getString("var13"));
			comOrder.setWlNum(listPd.get(i).getString("var14"));
			comOrderService.add(comOrder);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	

}
