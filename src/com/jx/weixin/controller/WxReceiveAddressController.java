package com.jx.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.BaseEntity.ValidationWxAdd;
import com.jx.common.config.BaseEntity.ValidationWxEdit;
import com.jx.common.config.PageData;
import com.jx.common.config.ResultInfo;
import com.jx.common.entity.ComReceiveAddress;
import com.jx.common.util.AppUtil;
import com.jx.weixin.util.WxSessionUtil;
import com.jx.common.service.ComReceiveAddressService;

/** 
 * 类名称：BgReceiveAddressController
 * 创建人：maple
 * 创建时间：2017-03-11
 */
@Controller
@RequestMapping(value="/weixin/receiveAddress")
public class WxReceiveAddressController extends BaseController {
	
	@Resource(name="comReceiveAddressService")
	private ComReceiveAddressService comReceiveAddressService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list() throws Exception{
		ModelAndView mv = this.getModelAndView();
		String userId = WxSessionUtil.getUserId();
		List<ComReceiveAddress>	comReceiveAddressList = comReceiveAddressService.listByUserId(userId);	//列出comReceiveAddress列表
		
		mv.addObject("comReceiveAddressList", comReceiveAddressList);
		mv.setViewName("weixin/receiveAddress/wxReceiveAddressList");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("weixin/wxResult");
		
		ComReceiveAddress comReceiveAddress = new ComReceiveAddress();
		comReceiveAddress.setReceicerName("");
		comReceiveAddress.setPhone("");
		comReceiveAddress.setProvince("");
		comReceiveAddress.setCity("");
		comReceiveAddress.setDistrict("");
		comReceiveAddress.setStreet("");
		comReceiveAddress.setDetail("");
		//非默认
		comReceiveAddress.setDefaultStatus("00");
		mv.addObject(comReceiveAddress);
		mv.addObject("methodPath", "add");
		mv.setViewName("weixin/receiveAddress/wxReceiveAddressEdit");
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add(@Validated(ValidationWxAdd.class) ComReceiveAddress comReceiveAddress, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		
		String userId = WxSessionUtil.getUserId();
		
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comReceiveAddress");
			mv.addObject(resultInfo);				
			return mv; 
		}
		comReceiveAddress.setAppUserId(userId);
		comReceiveAddress.setOrderNum(""+new Date().getTime());
		comReceiveAddressService.add(comReceiveAddress);
		resultInfo.setResultCode("success");
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(@RequestParam String receiveAddressId) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		String userId = WxSessionUtil.getUserId();
		ComReceiveAddress comReceiveAddress = comReceiveAddressService.findByUserIdAndId(userId, receiveAddressId);	//根据ID读取
		if(comReceiveAddress == null){
			mv.addObject(resultInfo);
			return mv;
		}
		mv.addObject("methodPath", "edit");
		mv.addObject(comReceiveAddress);
		resultInfo.setResultCode("success");
		mv.setViewName("weixin/receiveAddress/wxReceiveAddressEdit");
		
		mv.addObject(resultInfo);						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit(@Validated(ValidationWxEdit.class) ComReceiveAddress comReceiveAddress, BindingResult result) throws Exception{
		ModelAndView mv = this.getModelAndView();
		ResultInfo resultInfo = this.getResultInfo();
		mv.setViewName("weixin/wxResult");
		
		String userId = WxSessionUtil.getUserId();
		
		if(result.hasErrors()) {
			resultInfo.setResultEntity("comReceiveAddress");
			mv.addObject(resultInfo);				
			return mv; 
		}
		comReceiveAddress.setAppUserId(userId);
		comReceiveAddressService.editWx(comReceiveAddress);
		resultInfo.setResultCode("success");
		
		mv.addObject(resultInfo);
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDelete")
	@ResponseBody
	public Object toDelete(@RequestParam String receiveAddressId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		
		comReceiveAddressService.deleteByUserIdAndId(userId, receiveAddressId);	//根据ID删除
		resultInfo.setResultCode("success");

		return AppUtil.returnResult(pd, resultInfo);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/toDefault")
	@ResponseBody
	public Object toDefault(@RequestParam String receiveAddressId) throws Exception{
		PageData pd = this.getPageData();
		ResultInfo resultInfo = this.getResultInfo();
		
		String userId = WxSessionUtil.getUserId();
		
		comReceiveAddressService.toDefault(userId, receiveAddressId);	//根据ID删除
		resultInfo.setResultCode("success");
		
		return AppUtil.returnResult(pd, resultInfo);
	}
	
}
