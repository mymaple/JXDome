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
import com.jx.common.service.ComProductService;
import com.jx.common.service.ComProductStyleService;

@Controller
@RequestMapping(value="/weixin/product")
public class WxProductController extends BaseController {
	
	@Resource(name="comProductService")
	private ComProductService comProductService;
	
	@Resource(name="comProductStyleService")
	private ComProductStyleService comProductStyleService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/toProductDetail")
	public ModelAndView toProductDetail(@RequestParam String productId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo =this.getResultInfo();
		
		ComProduct comProduct = comProductService.findByIdSE(productId);
		if(comProduct == null){
			mv.addObject(resultInfo);
			mv.setViewName("weixin/wxResult");
		}else{
			List<ComProductStyle> comProductStyleList = comProductStyleService.listByProductIdSE(productId);
			
			mv.addObject(comProduct);
			mv.addObject("comProductStyleList",comProductStyleList);
			
			mv.setViewName("weixin/product/wxProductDetail");
		}
		return mv;
	}
	
}
