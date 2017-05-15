package com.jx.common.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类名称：MyExceptionResolver.java 类描述：
 * @author FH 作者单位： 联系方式：QQ123456789
 * @version 1.0
 */
public class MyExceptionResolver implements HandlerExceptionResolver {

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		// TODO Auto-generated method stub
		System.out.println("==============异常开始=============");
		ex.printStackTrace();
		System.out.println("\n==============异常结束=============");
		
		ModelAndView mv = new ModelAndView();
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setResultCode("failure");
		
		String path = request.getServletPath();
		String[] pathArr = path.split("/");
		
		if(pathArr.length>2){
			if("weixin".equals(pathArr[1])){
				resultInfo.setResultContent(ex.toString().replaceAll("\n", "<br/>"));
				mv.setViewName("error");
			}else if("weixin".equals(pathArr[1])){
				resultInfo.setResultContent("处理失败！");
				resultInfo.setResultUrl("weixin/index/toIndex.do");
				mv.setViewName("error");
			}
		}else{
			resultInfo.setResultContent(ex.toString().replaceAll("\n", "<br/>"));
			mv.setViewName("error");
		}
		mv.addObject(resultInfo);
		
		return mv;
	}

}
