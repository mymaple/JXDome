package com.jx.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComIntegralNote;
import com.jx.common.service.ComIntegralNoteService;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleDateUtil.SDF;
import com.jx.weixin.util.WxSessionUtil;

/** 
 * 类名称：BgIntegralNoteController
 * 创建人：maple
 * 创建时间：2017-03-06
 */
@Controller
@RequestMapping(value="/weixin/note")
public class WxNoteController extends BaseController {
	
	
	@Resource(name="comIntegralNoteService")
	private ComIntegralNoteService comIntegralNoteService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/toIntegralNoteList")
	public ModelAndView toIntegralNoteList() throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
		String userId = WxSessionUtil.getUserId();
		String yearMonth = pd.getString("yearMonth");
		if(StringUtils.isEmpty(yearMonth)){
			yearMonth = MapleDateUtil.formatDate(SDF.MONTH, new Date());
		}
		
		List<ComIntegralNote> comIntegralNoteList = comIntegralNoteService.listByUserE(userId, yearMonth);	//列出comReceiveAddress列表
		mv.addObject("comIntegralNoteList", comIntegralNoteList);
		mv.addObject("yearMonth", yearMonth);
		
		mv.setViewName("weixin/note/wxIntegralNoteList");
		return mv;
	}
	
}
