package com.jx.common.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;

public class BaseController {

	protected Logger logger = Logger.getLogger(this.getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	 @ModelAttribute
	 public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		 this.request = request;
		 this.response = response;
	 }
	
	/**
	 * 得到PageData<String,Sting>
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
	/**
	 * 得到ResultInfo
	 */
	public ResultInfo getResultInfo() {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setResultCode("failure");
		resultInfo.setResultContent("数据异常");
		resultInfo.setVersion("0004");
//		resultInfo.setVersion(Const.COM_VERSION);
		return resultInfo;
	}

	/**
	 * 得到response对象
	 */
	/*public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		return response;
	}*/
	
	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		return request;
	}
	
	/**
	 * 得到session对象
	 * 	shiro管理的session
	 */
	public Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID() {

		return UuidUtil.get32UUID();
	}

	/**
	 * 得到后台分页列表的信息
	 */
	public BgPage getBgPage() {

		return new BgPage();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		
		binder.registerCustomEditor(Integer.class, null,new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null,new CustomNumberEditor(Long.class, null, true));
        binder.registerCustomEditor(Double.class, null,new CustomNumberEditor(Double.class, null, true));
        binder.registerCustomEditor(byte[].class,new ByteArrayMultipartFileEditor());
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(MapleDateUtil.SDF_TIME,true));
	}

}
