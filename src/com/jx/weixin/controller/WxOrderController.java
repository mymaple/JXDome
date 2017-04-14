package com.jx.weixin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.util.BgSessionUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComOrder;
import com.jx.common.entity.ComOrderDetail;
import com.jx.common.entity.ComProduct;
import com.jx.common.entity.ComProductStyle;
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.entity.ComShopCar;
import com.jx.common.entity.ComSupplier;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleDecimalUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComOrderService;
import com.jx.common.service.ComProductSEService;
import com.jx.common.service.ComReceiveAddressService;
import com.jx.common.service.ComShopCarService;
import com.jx.common.service.ComSupplierService;

/**
 * 类名称：BgOrderController 创建人：maple 创建时间：2017-03-11
 */
@Controller
@RequestMapping(value = "/weixin/order")
public class WxOrderController extends BaseController {

	@Resource(name = "comOrderService")
	private ComOrderService comOrderService;
	@Resource(name="comReceiveAddressService")
	private ComReceiveAddressService comReceiveAddressService;
	@Resource(name="comProductSEService")
	private ComProductSEService comProductSEService;
	@Resource(name="comShopCarService")
	private ComShopCarService comShopCarService;
	@Resource(name="comSupplierService")
	private ComSupplierService comSupplierService;
	@Resource(name = "comAppUserExtService")
	private ComAppUserExtService comAppUserExtService;
	
	/**
	 * 直接购买
	 */
	@RequestMapping(value = "/toConfirmOrder1")
	public ModelAndView toConfirmOrder1(@RequestParam String productStyleId, @RequestParam int count) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		String userId = WxSessionUtil.getUserId();
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findByUserIdSE(userId);
		
		ComProduct comProduct = comProductSEService.findProductByProductStyleIdSE(productStyleId);
		if(comProduct == null){
			resultInfo.setResultContent("商品已下架");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		ComProductStyle comProductStyle = comProduct.getComProductStyle();
		if(count < 1 || count>Integer.parseInt(comProductStyle.getStockNum())){
			resultInfo.setResultContent("库存不足");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		
		ComOrder comOrder = new ComOrder();
		
		String allPrice = ""+MapleDecimalUtil.multiplyDefealt(comProductStyle.getOriginalPrice(), count);
		String allActPrice = ""+MapleDecimalUtil.multiplyDefealt(comProductStyle.getCurrentPrice(), count);
		String allDisPrice = ""+MapleDecimalUtil.subtractDefealt(allPrice, allActPrice);
		
		comOrder.setOrderProductCount(""+count);
		comOrder.setAllPrice(allPrice);
		comOrder.setAllActPrice(allActPrice);
		comOrder.setAllDisPrice(allDisPrice);
		
		ComSupplier comSupplier = comSupplierService.findById(comProduct.getSupplierId());
		comOrder.setFreight(null);
		comOrder.setSupplierId(comSupplier.getSupplierId());
		comOrder.setSupplierName(comSupplier.getSupplierName());
		
		comOrder.setOrderType("01");
		comOrder.setOrderStatus("01");
		comOrder.setReceiveAddressId(comReceiveAddress==null?"":comReceiveAddress.getReceiveAddressId());
		comOrder.setAppUserId(userId);
		
		ComOrderDetail comOrderDetail = new ComOrderDetail();
		comOrderDetail.setProductId(comProduct.getProductId());
		comOrderDetail.setHeadImgSrc(comProduct.getHeadImgSrc());
		comOrderDetail.setProductName(comProduct.getProductName());
		comOrderDetail.setSummary(comProduct.getSummary());
		comOrderDetail.setCount(""+count);
		comOrderDetail.setProductStyleId(comProductStyle.getProductStyleId());
		comOrderDetail.setProductStyleName(comProductStyle.getProductStyleName());
		comOrderDetail.setCurrentPrice(comProductStyle.getCurrentPrice());
		comOrderDetail.setOriginalPrice(comProductStyle.getOriginalPrice());
		comOrder.getComOrderDetailList().add(comOrderDetail);
		
		String pay = allActPrice;
		
		String integralCount = comAppUserExtService.toGetIntegralCount(userId);
		if(MapleDecimalUtil.subtractDefealt(integralCount, pay)<0){
			resultInfo.setResultContent("积分不足");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		
		comOrderService.toConfirmOrder1(comOrder);
		
		List<ComOrder> comOrderList = new ArrayList<ComOrder>();
		comOrderList.add(comOrder);
		
		mv.addObject("comReceiveAddress", comReceiveAddress);
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pay", pay);
		mv.addObject("payCount", count);
		
		mv.setViewName("weixin/order/wxConfirmOrder");
		return mv;
	}
	
	
	/**
	 * 购物车购买
	 */
	@RequestMapping(value = "/toConfirmOrder2")
	public ModelAndView toConfirmOrder2() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		String userId = WxSessionUtil.getUserId();
		
		String shopCarIds = pd.getString("shopCarId");
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findByUserIdSE(userId);
		String[] shopCarIdArr = shopCarIds.split(",");
		List<ComShopCar> comShopCarList = comProductSEService.listShopCarByUserSE(userId,shopCarIdArr);
		
		if(comShopCarList.size()!=shopCarIdArr.length){
			resultInfo.setResultContent("购物车商品已购买");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		
		List<ComOrder> comOrderList = new ArrayList<ComOrder>();
		ComOrder comOrder = new ComOrder();
		
		String supplierId = "";
		
		String pay = "0";
		String payCount = "0";
		
		String allCount = "0";
		String allPrice = "0";
		String allActPrice = "0";
		String allDisPrice = "0";
		
		for (int i = 0; i < comShopCarList.size(); i++) {
			ComShopCar comShopCar = comShopCarList.get(i);
			ComProduct comProduct = comShopCar.getComProduct();
			int count = Integer.parseInt(comShopCar.getCount());
			ComProductStyle comProductStyle = comProduct.getComProductStyle();
			if(count < 1 || count>Integer.parseInt(comProductStyle.getStockNum())){
				resultInfo.setResultContent("库存不足");
				mv.addObject(resultInfo);
				mv.setViewName("weixin/wxResult");
				return mv;
			}
			
			if(!supplierId.equals(comProduct.getSupplierId())){
				if(i!=0){
					comOrder.setOrderProductCount(allCount);
					comOrder.setAllPrice(allPrice);
					comOrder.setAllActPrice(allActPrice);
					comOrder.setAllDisPrice(allDisPrice);
					comOrderList.add(comOrder);
					
					allPrice = "0";
					allActPrice = "0";
					allDisPrice = "0";
					allCount = "0";
				}
					
				comOrder = new ComOrder();
				ComSupplier comSupplier = comSupplierService.findById(comProduct.getSupplierId());
				comOrder.setFreight("0");
				comOrder.setSupplierId(comSupplier.getSupplierId());
				comOrder.setSupplierName(comSupplier.getSupplierName());
				
				comOrder.setOrderType("01");
				comOrder.setOrderStatus("01");
				comOrder.setReceiveAddressId(comReceiveAddress==null?"":comReceiveAddress.getReceiveAddressId());
				comOrder.setAppUserId(userId);
				
				supplierId = comProduct.getSupplierId();
				
			}
			
			ComOrderDetail comOrderDetail = new ComOrderDetail();
			comOrderDetail.setProductId(comProduct.getProductId());
			comOrderDetail.setHeadImgSrc(comProduct.getHeadImgSrc());
			comOrderDetail.setProductName(comProduct.getProductName());
			comOrderDetail.setSummary(comProduct.getSummary());
			comOrderDetail.setCount(""+count);
			comOrderDetail.setProductStyleId(comProductStyle.getProductStyleId());
			comOrderDetail.setProductStyleName(comProductStyle.getProductStyleName());
			comOrderDetail.setCurrentPrice(comProductStyle.getCurrentPrice());
			comOrderDetail.setOriginalPrice(comProductStyle.getOriginalPrice());
			comOrder.getComOrderDetailList().add(comOrderDetail);
			
			
			pay = ""+MapleDecimalUtil.addDefealt(pay, MapleDecimalUtil.multiplyDefealt(comProductStyle.getCurrentPrice(), count));
			payCount = ""+MapleDecimalUtil.addDefealt(payCount, count);
			
			allCount = ""+MapleDecimalUtil.addDefealt(allCount, count);
			
			allPrice = ""+MapleDecimalUtil.addDefealt(allPrice, MapleDecimalUtil.multiplyDefealt(comProductStyle.getOriginalPrice(), count));
			allActPrice = ""+MapleDecimalUtil.addDefealt(allActPrice, MapleDecimalUtil.multiplyDefealt(comProductStyle.getCurrentPrice(), count));
			
			allDisPrice = ""+MapleDecimalUtil.subtractDefealt(allPrice, allActPrice);
		}
		
		comOrder.setOrderProductCount(allCount);
		comOrder.setAllPrice(allPrice);
		comOrder.setAllActPrice(allActPrice);
		comOrder.setAllDisPrice(allDisPrice);
		comOrderList.add(comOrder);
		
		String integralCount = comAppUserExtService.toGetIntegralCount(userId);
		if(MapleDecimalUtil.subtractDefealt(integralCount, pay)<0){
			resultInfo.setResultContent("积分不足");
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
			return mv;
		}
		comOrderService.toConfirmOrder2(comOrderList, shopCarIdArr);
		mv.addObject("comReceiveAddress", comReceiveAddress);
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pay", pay);
		mv.addObject("payCount", payCount);
		
		mv.setViewName("weixin/order/wxConfirmOrder");
		return mv;
	}

	/**
	 * 直接购买
	 */
	@RequestMapping(value = "/toConfirmOrder3")
	public ModelAndView toConfirmOrder3(@RequestParam String receiveAddressId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		
		String userId = WxSessionUtil.getUserId();
		String orderIds = WxSessionUtil.getOrderIdcra();
		
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findByUserIdAndIdE(userId, receiveAddressId);
		if(comReceiveAddress == null){
			resultInfo.setResultContent("收货地址异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		List<ComOrder> comOrderList = comOrderService.listByOrderIdsSED("01", userId, orderIds.split(","));
		
		if(comOrderList==null || comOrderList.size()==0){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		String pay = "0";
		String payCount = "0";
		for (int i = 0; i < comOrderList.size(); i++) {
			ComOrder comOrder = comOrderList.get(i);
			comOrderService.changeReceiveAddressIdByU(comOrder.getOrderId(), userId, receiveAddressId);
			pay = ""+MapleDecimalUtil.addDefealt(pay, comOrder.getAllActPrice());
			payCount = ""+MapleDecimalUtil.addDefealt(payCount, comOrder.getOrderProductCount());
		}
		
		mv.addObject("comReceiveAddress", comReceiveAddress);
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pay", pay);
		mv.addObject("payCount", payCount);
		
		mv.setViewName("weixin/order/wxConfirmOrder");
		return mv;
	}
	
	
	/**
	 * 选择支付
	 */
	@RequestMapping(value = "/toChoosePay")
	public ModelAndView toChoosePay() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		
		String userId = WxSessionUtil.getUserId();
		String orderIds = pd.getString("orderId");
		
		List<ComOrder> comOrderList = comOrderService.listByOrderIdsSED("01", userId, orderIds.split(","));
		
		if(comOrderList==null || comOrderList.size()==0){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		String pay = "0";
		for (int i = 0; i < comOrderList.size(); i++) {
			ComOrder comOrder = comOrderList.get(i);
			pay = ""+MapleDecimalUtil.addDefealt(pay, comOrder.getAllActPrice());
		}
		
		String integralCount = comAppUserExtService.toGetIntegralCount(userId);
		
		
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pay", pay);
		mv.addObject("integralCount", integralCount);
		
		resultInfo.setResultCode("success");
		mv.setViewName("weixin/order/wxChoosePay");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toPay")
	public ModelAndView toPay() throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		
		
		String orderIds = pd.getString("orderId");
		String userId = WxSessionUtil.getUserId();
		
		comOrderService.toPayByUserE(userId, orderIds.split(","));
		
		resultInfo.setResultCode("success");
		mv.setViewName("weixin/wxResult");

		mv.addObject(resultInfo);
		return mv;
	}
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");
		
		String state = pd.getString("state");
		String userId = WxSessionUtil.getUserId();
		List<ComOrder> comOrderList = comOrderService.listByOrderIdsSED(state, userId, null);
		
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("state", state);
		resultInfo.setResultCode("success");
		mv.setViewName("weixin/order/wxOrderList");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 选择支付
	 */
	@RequestMapping(value = "/toOrderDetail")
	public ModelAndView toOrderDetail(@RequestParam String orderId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		
		ComOrder comOrder = comOrderService.findByUserED(orderId, userId);
		if(comOrder==null){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		//benren..........
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findById(comOrder.getReceiveAddressId());
		
		String orderStatus = comOrder.getOrderStatus(); 
		if("01".equals(orderStatus)){
			List<ComOrder> comOrderList = new ArrayList<ComOrder>();
			comOrderList.add(comOrder);
			String pay = comOrder.getAllActPrice();
			String payCount = comOrder.getOrderProductCount();
			
			mv.addObject("comReceiveAddress", comReceiveAddress);
			mv.addObject("comOrderList", comOrderList);
			mv.addObject("pay", pay);
			mv.addObject("payCount", payCount);
			mv.setViewName("weixin/order/wxConfirmOrder");
		}else{
			mv.addObject("comReceiveAddress", comReceiveAddress);
			mv.addObject("comOrder", comOrder);
			mv.setViewName("weixin/order/wxOrderDetail");
		}
		
		resultInfo.setResultCode("success");
		

		mv.addObject(resultInfo);
		return mv;
	}

	
	/**
	 * 再次购买
	 */
	@RequestMapping(value = "/toBuyMore")
	public ModelAndView toBuyMore(@RequestParam String orderId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		
		ComOrder comOrder = comOrderService.findByUserED(orderId, userId);
		if(comOrder==null){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findById(comOrder.getReceiveAddressId());
		
		String pay = "0";
		String payCount = "0";
		
		String allCount = "0";
		String allPrice = "0";
		String allActPrice = "0";
		String allDisPrice = "0";
		
		List<ComOrder> comOrderList = new ArrayList<ComOrder>();
		ComOrder comOrder1 = new ComOrder();
		
		ComSupplier comSupplier = comSupplierService.findById(comOrder.getSupplierId());
		comOrder1.setFreight("0");
		comOrder1.setSupplierId(comSupplier.getSupplierId());
		comOrder1.setSupplierName(comSupplier.getSupplierName());
		
		comOrder1.setOrderType("01");
		comOrder1.setOrderStatus("01");
		comOrder1.setReceiveAddressId(comReceiveAddress==null?"":comReceiveAddress.getReceiveAddressId());
		comOrder1.setAppUserId(userId);
		
		List<ComOrderDetail> comOrderDetailList = comOrder.getComOrderDetailList();
		for (int i = 0; i < comOrderDetailList.size(); i++) {
			ComOrderDetail comOrderDetail = comOrderDetailList.get(i);
			ComProduct comProduct = comProductSEService.findProductByProductStyleIdSE(comOrderDetail.getProductStyleId());
			ComProductStyle comProductStyle = comProduct.getComProductStyle();
			int count = Integer.parseInt(comOrderDetail.getCount());
			if(MapleDecimalUtil.subtractDefealt(comProductStyle.getStockNum(), count)<0){
				resultInfo.setResultContent("库存不足");
				mv.addObject(resultInfo);
				return mv;
			}
			
			ComOrderDetail comOrderDetail1 = new ComOrderDetail();
			comOrderDetail1.setProductId(comProduct.getProductId());
			comOrderDetail1.setHeadImgSrc(comProduct.getHeadImgSrc());
			comOrderDetail1.setProductName(comProduct.getProductName());
			comOrderDetail1.setSummary(comProduct.getSummary());
			comOrderDetail1.setCount(""+count);
			comOrderDetail1.setProductStyleId(comProductStyle.getProductStyleId());
			comOrderDetail1.setProductStyleName(comProductStyle.getProductStyleName());
			comOrderDetail1.setCurrentPrice(comProductStyle.getCurrentPrice());
			comOrderDetail1.setOriginalPrice(comProductStyle.getOriginalPrice());
			comOrder1.getComOrderDetailList().add(comOrderDetail1);
			
			pay = ""+MapleDecimalUtil.addDefealt(pay, MapleDecimalUtil.multiplyDefealt(comProductStyle.getCurrentPrice(), count));
			payCount = ""+MapleDecimalUtil.addDefealt(payCount, count);
			
			allCount = ""+MapleDecimalUtil.addDefealt(allCount, count);
			allPrice = ""+MapleDecimalUtil.addDefealt(allPrice, MapleDecimalUtil.multiplyDefealt(comProductStyle.getOriginalPrice(), count));
			allActPrice = ""+MapleDecimalUtil.addDefealt(allActPrice, MapleDecimalUtil.multiplyDefealt(comProductStyle.getCurrentPrice(), count));
			allDisPrice = ""+MapleDecimalUtil.subtractDefealt(allPrice, allActPrice);
			
		}
		comOrder1.setOrderProductCount(allCount);
		comOrder1.setAllPrice(allPrice);
		comOrder1.setAllActPrice(allActPrice);
		comOrder1.setAllDisPrice(allDisPrice);
		comOrderList.add(comOrder1);
		
		comOrderService.toConfirmOrder1(comOrder1);
		
		mv.addObject("comReceiveAddress", comReceiveAddress);
		mv.addObject("comOrderList", comOrderList);
		mv.addObject("pay", pay);
		mv.addObject("payCount", payCount);
		
		
		mv.setViewName("weixin/order/wxConfirmOrder");
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 查看物流
	 */
	@RequestMapping(value = "/toWl")
	public ModelAndView toWl(@RequestParam String orderId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		
		ComOrder comOrder = comOrderService.findByUserED(orderId, userId);
		if(comOrder==null){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		mv.setViewName("weixin/order/wxWl");
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 我要退款
	 */
	@RequestMapping(value = "/toRefund")
	public ModelAndView toRefund(@RequestParam String orderId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		
		ComOrder comOrder = comOrderService.findByUserED(orderId, userId);
		if(comOrder==null){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		mv.setViewName("weixin/order/wxRefund");
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 立即评价
	 */
	@RequestMapping(value = "/toEvaluate")
	public ModelAndView toEvaluate(@RequestParam String orderId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		
		ComOrder comOrder = comOrderService.findByUserED(orderId, userId);
		if(comOrder==null){
			resultInfo.setResultContent("订单异常");
			mv.addObject(resultInfo);
			return mv;
		}
		
		mv.setViewName("weixin/order/wxEvaluate");
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping(value="/toCancle")
	@ResponseBody
	public Object toCancle(@RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		
		comOrderService.toCancleByUSE(orderId, userId);
		resultInfo.setResultCode("success");

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 订单提醒
	 */
	@RequestMapping(value="/toRemind")
	@ResponseBody
	public Object toRemind(@RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 确认接收
	 */
	@RequestMapping(value="/toConfirmReceive")
	@ResponseBody
	public Object toConfirmReceive(@RequestParam String orderId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		
		comOrderService.changeStatusByUSE("05", orderId, userId);
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
}
