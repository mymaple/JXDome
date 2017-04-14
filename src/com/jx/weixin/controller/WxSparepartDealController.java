package com.jx.weixin.controller;

import java.util.Date;
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
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.entity.ComSparepartDeal;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleFileUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.util.MapleDateUtil.SDF;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.weixin.util.WxUtil;
import com.jx.common.service.ComSparepartDealService;

/** 
 * 类名称：WxSparepartDealController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/weixin/sparepartDeal")
public class WxSparepartDealController extends BaseController {
	
	@Resource(name="comSparepartDealService")
	private ComSparepartDealService comSparepartDealService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		String yearMonth = pd.getString("yearMonth");
		if(StringUtils.isEmpty(yearMonth)){
			yearMonth = MapleDateUtil.formatDate(SDF.MONTH, new Date());
		}
		
		List<ComSparepartDeal>	comSparepartDealList = comSparepartDealService.listByUserE(userId, yearMonth);	//列出comSparepartDeal列表
		mv.addObject("comSparepartDealList", comSparepartDealList);
		mv.addObject("yearMonth", yearMonth);
		
		mv.setViewName("weixin/sparepartDeal/wxSparepartDealList");
		return mv;
	}
	
	/**
	 * 去解析二维码页面
	 */
	@RequestMapping(value="/toParseQRCoder")
	public ModelAndView toParseQRCoder() throws Exception{
		ModelAndView mv = this.getModelAndView();
		
		Map<String, String> ret = WxUtil.sign(PathUtil.getBasePath(request)+"/weixin/sparepartDeal/toParseQRCoder");
		mv.addObject("ret",ret);
		mv.setViewName("weixin/sparepartDeal/wxParseQRCoder");
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
		
		Date nowTime = new Date();
		
		ComSparepartDeal comSparepartDeal = new ComSparepartDeal();
		comSparepartDeal.setSparepartDealCode("");
		comSparepartDeal.setAppUserId("");
		comSparepartDeal.setSparepartId("");
		comSparepartDeal.setCount("0");
		comSparepartDeal.setDealAmt("0.00");
		comSparepartDeal.setOrderTime(nowTime);
		comSparepartDeal.setOrderNum(String.valueOf(nowTime.getTime()));
		
		mv.addObject(comSparepartDeal);
		mv.addObject("methodPath", "add");
		resultInfo.setResultCode("success");
		mv.setViewName("background/sparepartDeal/bgSparepartDealEdit");
		
		mv.addObject(resultInfo);					
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationAdd.class) ComSparepartDeal comSparepartDeal, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		//PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("background/bgResult");

		if(result.hasErrors()) {
			resultInfo.setResultEntity("comSparepartDeal");
			mv.addObject(resultInfo);				
			return mv; 
		}
		
		List<ComSparepartDeal> comSparepartDealList = comSparepartDealService.otherHaveCode("", comSparepartDeal.getSparepartDealCode());
		if(MapleUtil.notEmptyList(comSparepartDealList)){
			mv.addObject(resultInfo);					
			return mv;
		}
			
		comSparepartDealService.add(comSparepartDeal);
		resultInfo.setResultCode("success");

		mv.addObject(resultInfo);
		return mv;
	}
	
}
