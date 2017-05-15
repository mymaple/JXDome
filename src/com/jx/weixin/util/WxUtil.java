package com.jx.weixin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jx.common.util.WxConnUtil;

public class WxUtil {

	public static String getWxMyOpenId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String openId = WxSessionUtil.getMyOpenId();
//		openId = "oAQf_wgU22N3diLH4TEqxu_8j6Rk";
		WxSessionUtil.setMyOpenId(openId);
		if (StringUtils.isEmpty(openId)) {
			String code = request.getParameter("code");
			if (StringUtils.isEmpty(code)) {
				WxConnUtil.toRedirect(request, response);
			} else {
				openId = WxConnUtil.getMyOpenId(code);
				WxSessionUtil.setMyOpenId(openId);
			}
		}
		return openId;
	}

}
