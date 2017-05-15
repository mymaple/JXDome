package com.jx.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComProductStyle;
import com.jx.common.entity.ComShopCar;
import com.jx.common.service.ComProductSEService;
import com.jx.common.service.ComProductStyleService;
import com.jx.common.service.ComShopCarService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleDecimalUtil;
import com.jx.weixin.util.WxSessionUtil;

@Controller
@RequestMapping(value="/weixin/shopCar")
public class WxShopCarController extends BaseController {
	
	@Resource(name="comShopCarService")
	private ComShopCarService comShopCarService;
	@Resource(name="comProductStyleService")
	private ComProductStyleService comProductStyleService;
	@Resource(name="comProductSEService")
	private ComProductSEService comProductSEService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		List<ComShopCar> comShopCarList = comProductSEService.listShopCarByUSE(userId, null);
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.addObject("comShopCarList", comShopCarList);
		mv.setViewName("weixin/shopCar/wxShopCarList");
		return mv;
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(@RequestParam String productStyleId, @RequestParam int count) throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		
		ComProductStyle comProductStyle = comProductStyleService.findByIdSE(productStyleId);
		if(comProductStyle == null){
			resultInfo.setResultContent("已下架");
			return AppUtil.returnResult(pd, resultInfo);
		}
		if(count < 1 || count>Integer.parseInt(comProductStyle.getStockNum())){
			resultInfo.setResultContent("库存不足");
			return AppUtil.returnResult(pd, resultInfo);
		}
		
		String userId = WxSessionUtil.getUserId();
		ComShopCar comShopCar = comShopCarService.findByIdUSE(userId, productStyleId);
		if(comShopCar!=null){
			resultInfo.setResultContent("已加入购物车");
			return AppUtil.returnResult(pd, resultInfo);
		}else{
			
			try {
				comShopCar = new ComShopCar();
				comShopCar.setAppUserId(userId);
				comShopCar.setProductStyleId(productStyleId);
				comShopCar.setCount(""+count);
				comShopCar.setShopCarStatus("01");
				comShopCar.setOrderNum(""+new Date().getTime());
				comShopCarService.add(comShopCar);
				resultInfo.setResultContent("已加入购物车");
				resultInfo.setResultCode("success");
			} catch (Exception e) {
				resultInfo.setResultContent("加入购物车失败");
			}
			
		}
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String shopCarIds) throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		
		comShopCarService.deleteByIdsUSE(userId, shopCarIds);
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toGetAllPay")
	@ResponseBody
	public Object toGetAllPay(@RequestParam String shopCarIds) throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		
		String[] shopCarIdArr = shopCarIds.split(",");
		List<ComShopCar> comShopCarList = comProductSEService.listShopCarByUSE(userId,shopCarIdArr);
		if(comShopCarList.size()!=shopCarIdArr.length){
			resultInfo.setResultContent("购物车异常");
			return AppUtil.returnResult(pd, resultInfo);
		}
		
		String allPay = "0";
		
		for (int i = 0; i < comShopCarList.size(); i++) {
			ComShopCar comShopCar = comShopCarList.get(i);
			String count = comShopCar.getCount();
			String currentPrice = comShopCar.getComProduct().getComProductStyle().getCurrentPrice();
			allPay = ""+MapleDecimalUtil.addDefealt(MapleDecimalUtil.multiplyDefealt(count, currentPrice), allPay);
		}
		
		resultInfo.setResultCode("success");
		resultInfo.setResultContent(allPay);
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/toChangeCount")
	@ResponseBody
	public Object toChangeCount(@RequestParam String shopCarId, @RequestParam int count) throws Exception {
		ResultInfo resultInfo = this.getResultInfo();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		
		comShopCarService.changeCountUSE(userId, shopCarId, ""+count);
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
}
