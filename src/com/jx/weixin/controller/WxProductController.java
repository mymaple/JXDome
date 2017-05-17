package com.jx.weixin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComProduct;
import com.jx.common.entity.ComProductStyle;
import com.jx.common.entity.ComShopCar;
import com.jx.common.service.ComProductSEService;
import com.jx.common.service.ComProductService;
import com.jx.common.service.ComProductStyleService;
import com.jx.weixin.util.WxSessionUtil;

@Controller
@RequestMapping(value="/weixin/product")
public class WxProductController extends BaseController {
	
	@Resource(name="comProductService")
	private ComProductService comProductService;
	
	@Resource(name="comProductStyleService")
	private ComProductStyleService comProductStyleService;
	
	@Resource(name="comProductSEService")
	private ComProductSEService comProductSEService;
	/**
	 * 列表
	 */
	@RequestMapping(value="/toProductDetail")
	public ModelAndView toProductDetail(@RequestParam String productId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo =this.getResultInfo();
		String userId = WxSessionUtil.getUserId();
		
		ComProduct comProduct = comProductService.findByIdSE(productId);
		if(comProduct == null){
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
		}else{
			List<ComProductStyle> comProductStyleList = comProductStyleService.listByProductIdSE(productId);
			
			List<ComShopCar> comShopCarList = comProductSEService.listShopCarByUSE(userId, null);
			int shopCarCount = comShopCarList.size();
			
			resultInfo.setResultCode("success");
			mv.addObject(resultInfo);
			mv.addObject(comProduct);
			mv.addObject("comProductStyleList",comProductStyleList);
			mv.addObject("shopCarCount",shopCarCount);
			
			mv.setViewName("weixin/product/wxProductDetail");
		}
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/toCategory")
	public ModelAndView toCategory() throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo =this.getResultInfo();
//		String userId = WxSessionUtil.getUserId();
		
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		mv.setViewName("weixin/product/wxProductCategory");
		return mv;
	}
}
