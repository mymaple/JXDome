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
import com.jx.common.entity.ComOrder;
import com.jx.common.entity.ComOrderDetail;
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.service.ComOrderDetailService;
import com.jx.common.service.ComOrderService;
import com.jx.common.service.ComReceiveAddressService;

/** 
 * 类名称：BgOrderController
 * 创建人：maple
 * 创建时间：2017-03-20
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
	@Resource(name="comOrderDetailService")
	private ComOrderDetailService comOrderDetailService;
	@Resource(name="comReceiveAddressService")
	private ComReceiveAddressService comReceiveAddressService;
	
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
		comOrder.setOrderStatus("00");
		comOrder.setAppUserId("");
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
		comOrder.setRemark("");
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
	 * 更改状态
	 */
	/*@RequestMapping(value="/changeStatus")
	@ResponseBody
	public Object changeStatus(@RequestParam String flag, @RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comOrderService.changeStatus(flag, orderId);	
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}*/
	
	/**
	 * 更改有效性
	 */
	@RequestMapping(value="/changeEffective")
	@ResponseBody
	public Object changeEffective(@RequestParam String flag, @RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comOrderService.changeEffective(flag, orderId);	
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
		titles.add("订单 主键id");		//0
		titles.add("订单代号");	//1
		titles.add("订单名称");	//2
		titles.add("订单类型");	//3
		titles.add("订单状态");	//4
		titles.add("平台用户");	//5
		titles.add("订单商品总数");	//6
		titles.add("商品总价");	//7
		titles.add("运费");	//8
		titles.add("总优惠");	//9
		titles.add("钱包支付");	//10
		titles.add("实付款");	//11
		titles.add("供应商");	//12
		titles.add("供应商id");	//13
		titles.add("收货地址");	//14
		titles.add("付款时间");	//15
		titles.add("发货时间");	//16
		titles.add("交易号");	//17
		titles.add("付款方式");	//18
		titles.add("物流公司");	//19
		titles.add("运单编号");	//20
		titles.add("订单备注");	//21
		titles.add("排序编号");	//22
		titles.add("有效标志");	//23
		titles.add("创建人员id");	//24
		titles.add("创建时间");	//25
		titles.add("修改人员id");	//26
		titles.add("修改时间");	//27
		dataMap.put("titles", titles);
		List<ComOrder> varOList = comOrderService.listAll();
		List<PageData> varList = new ArrayList<PageData>();
		for(int i=0;i<varOList.size();i++){
			PageData vpd = new PageData();	
			vpd.put("var0",varOList.get(i).getOrderId());			//0
			vpd.put("var1", varOList.get(i).getOrderCode());	//1
			vpd.put("var2", varOList.get(i).getOrderName());	//2
			vpd.put("var3", varOList.get(i).getOrderType());	//3
			vpd.put("var4", varOList.get(i).getOrderStatus());	//4
			vpd.put("var5", varOList.get(i).getAppUserId());	//5
			vpd.put("var6", varOList.get(i).getOrderProductCount());	//6
			vpd.put("var7", varOList.get(i).getAllPrice());	//7
			vpd.put("var8", varOList.get(i).getFreight());	//8
			vpd.put("var9", varOList.get(i).getAllDisPrice());	//9
			vpd.put("var10", varOList.get(i).getWalletPay());	//10
			vpd.put("var11", varOList.get(i).getAllActPrice());	//11
			vpd.put("var12", varOList.get(i).getSupplierName());	//12
			vpd.put("var13", varOList.get(i).getSupplierId());	//13
			vpd.put("var14", varOList.get(i).getReceiveAddressId());	//14
			vpd.put("var15", varOList.get(i).getPayTime());	//15
			vpd.put("var16", varOList.get(i).getSendTime());	//16
			vpd.put("var17", varOList.get(i).getTradeNum());	//17
			vpd.put("var18", varOList.get(i).getPayMethod());	//18
			vpd.put("var19", varOList.get(i).getWlgs());	//19
			vpd.put("var20", varOList.get(i).getWlNum());	//20
			vpd.put("var21", varOList.get(i).getRemark());	//21
			vpd.put("var22", varOList.get(i).getOrderNum());		//22
			vpd.put("var23", varOList.get(i).getEffective());	//23
			vpd.put("var24", varOList.get(i).getCreateUserId());	//24
			vpd.put("var25", varOList.get(i).getCreateTime());	//25
			vpd.put("var26", varOList.get(i).getModifyUserId());//26
			vpd.put("var27", varOList.get(i).getModifyTime());	//27
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
		titles.add("订单状态");	//3
		titles.add("平台用户");	//4
		titles.add("订单商品总数");	//5
		titles.add("商品总价");	//6
		titles.add("运费");	//7
		titles.add("总优惠");	//8
		titles.add("钱包支付");	//9
		titles.add("实付款");	//10
		titles.add("供应商id");	//11
		titles.add("收货地址");	//12
		titles.add("交易号");	//13
		titles.add("付款方式");	//14
		titles.add("物流公司");	//15
		titles.add("运单编号");	//16
		titles.add("订单备注");	//17
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
		 * var3 :订单状态;	//3
		 * var4 :平台用户;	//4
		 * var5 :订单商品总数;	//5
		 * var6 :商品总价;	//6
		 * var7 :运费;	//7
		 * var8 :总优惠;	//8
		 * var9 :钱包支付;	//9
		 * var10 :实付款;	//10
		 * var11 :供应商id;	//11
		 * var12 :收货地址;	//12
		 * var13 :交易号;	//13
		 * var14 :付款方式;	//14
		 * var15 :物流公司;	//15
		 * var16 :运单编号;	//16
		 * var17 :订单备注;	//17
		 */
		for(int i=0;i<listPd.size();i++){	
			comOrder.setOrderId(this.get32UUID());
			comOrder.setOrderCode(listPd.get(i).getString("var0"));
			comOrder.setOrderName(listPd.get(i).getString("var1"));
			comOrder.setOrderType(listPd.get(i).getString("var2"));
			comOrder.setOrderStatus(listPd.get(i).getString("var3"));
			comOrder.setAppUserId(listPd.get(i).getString("var4"));
			comOrder.setOrderProductCount(listPd.get(i).getString("var5"));
			comOrder.setAllPrice(listPd.get(i).getString("var6"));
			comOrder.setFreight(listPd.get(i).getString("var7"));
			comOrder.setAllDisPrice(listPd.get(i).getString("var8"));
			comOrder.setWalletPay(listPd.get(i).getString("var9"));
			comOrder.setAllActPrice(listPd.get(i).getString("var10"));
			comOrder.setSupplierId(listPd.get(i).getString("var11"));
			comOrder.setReceiveAddressId(listPd.get(i).getString("var12"));
			comOrder.setTradeNum(listPd.get(i).getString("var13"));
			comOrder.setPayMethod(listPd.get(i).getString("var14"));
			comOrder.setWlgs(listPd.get(i).getString("var15"));
			comOrder.setWlNum(listPd.get(i).getString("var16"));
			comOrder.setRemark(listPd.get(i).getString("var17"));
			comOrderService.add(comOrder);
		}
		/*存入数据库操作======================================*/
		resultInfo.setResultCode("success");
		mv.setViewName("background/bgResult");
	
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toInfo")
	public ModelAndView toInfo(@RequestParam String orderId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrder comOrder = comOrderService.findById(orderId);	//根据ID读取
		if(comOrder == null){
			mv.addObject(resultInfo);
			return mv;
		}
		
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findById(comOrder.getReceiveAddressId());
		List<ComOrderDetail> comOrderDetailList = comOrderDetailService.listByOrderId(orderId);
		
		
		mv.addObject(comOrder);
		mv.addObject(comReceiveAddress);
		mv.addObject("comOrderDetailList", comOrderDetailList);
		
		resultInfo.setResultCode("success");
		mv.setViewName("background/order/bgOrderInfo");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toDeliver")
	public ModelAndView toDeliver(@RequestParam String orderId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		ComOrder comOrder = comOrderService.findById(orderId);	//根据ID读取
		if(comOrder == null || !"02".equals(comOrder.getOrderStatus())){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject(comOrder);
		resultInfo.setResultCode("success");
		mv.setViewName("background/order/bgOrderDeliver");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/deliver")
	public ModelAndView deliver(@RequestParam String orderId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String wlgs = pd.getString("wlgs");
		String wlNum = pd.getString("wlNum");
		
		if(StringUtils.isEmpty(wlgs)||StringUtils.isEmpty(wlNum)) {
			resultInfo.setResultEntity("comOrder");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		ComOrder comOrder = new ComOrder();
		comOrder.setOrderId(orderId);
		comOrder.setWlgs(wlgs);
		comOrder.setWlNum(wlNum);
		comOrder.setSendTime(new Date());
		
		comOrderService.changeStatus("03", comOrder);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 更改状态
	 */
	@RequestMapping(value="/refund")
	@ResponseBody
	public Object refund(@RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		comOrderService.toRefund(orderId);
		resultInfo.setResultCode("success");
		
		comOrderService.toRefund(orderId);
		return AppUtil.returnResult(pd, resultInfo);
	}

}
