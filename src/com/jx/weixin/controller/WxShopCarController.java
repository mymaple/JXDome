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
		
		String userId = WxSessionUtil.getUserId();
		List<ComShopCar> comShopCarList = comProductSEService.listShopCarByUserSE(userId, null);
		
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
		ComShopCar comShopCar = comShopCarService.findByUserSE(userId, productStyleId);
		if(comShopCar!=null){
			resultInfo.setResultContent("已加入购物车");
			return AppUtil.returnResult(pd, resultInfo);
		}else{
			comShopCar = new ComShopCar();
			comShopCar.setAppUserId(userId);
			comShopCar.setProductStyleId(productStyleId);
			comShopCar.setCount(""+count);
			comShopCar.setShopCarStatus("01");
			comShopCar.setOrderNum(""+new Date().getTime());
			comShopCarService.add(comShopCar);
			resultInfo.setResultContent("已加入购物车");
			resultInfo.setResultCode("success");
		}
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
}
