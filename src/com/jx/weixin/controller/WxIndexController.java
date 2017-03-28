package com.jx.weixin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.entity.ComLbt;
import com.jx.common.entity.ComProduct;
import com.jx.common.service.ComLbtService;
import com.jx.common.service.ComProductSEService;
import com.jx.common.service.ComProductService;

/** 
 * 类名称：WxIndexController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/weixin/index")
public class WxIndexController extends BaseController {
	
	
	
	@Resource(name="comProductSEService")
	private ComProductSEService comProductSEService;
	@Resource(name="comLbtService")
	private ComLbtService comLbtService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/toIndex")
	public ModelAndView toIndex() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		List<ComLbt> comLbtList = comLbtService.listE();
		//分类
		List<ComProduct> comProductList = comProductSEService.listByCategoryCodeSE("spfl100172");
		
		mv.addObject("comLbtList",comLbtList);
		mv.addObject("comProductList",comProductList);
		mv.setViewName("weixin/index/wxIndex");
		
		return mv;
	}
	
	
}
