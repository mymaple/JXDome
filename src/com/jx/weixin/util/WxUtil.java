package com.jx.weixin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jx.common.util.WxConnUtil;

public class WxUtil {
	
	
	public static String getWxMyOpenId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String openId = WxSessionUtil.getSessionWxMyOpenId();
		if(StringUtils.isEmpty(openId)){
			String code = request.getParameter("code");
			if(StringUtils.isEmpty(code)){
				WxConnUtil.toRedirect(request, response);
			}else{
				openId = WxConnUtil.getMyOpenId(code);
				WxSessionUtil.setSessionWxMyOpenId(openId);
			}
		}
		return openId;
	}
}
