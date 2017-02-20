package com.jx.common.config.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import com.jx.common.config.Const;

public class WxFormAuthenticationFilter extends FormAuthenticationFilter {
	
	/**
	 * 在认证之前
	 */
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = WebUtils.getSavedRequest(req).getRequestUrl();
		if(Const.PATH_WX_TOLOGIN_STR.equals(url)){
			
		}
		
		return super.onAccessDenied(request, response);
	}
	
}
