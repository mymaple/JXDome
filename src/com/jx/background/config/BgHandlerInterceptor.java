package com.jx.background.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx.background.entity.BgUser;
import com.jx.background.util.BgSessionUtil;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.Const;

/**
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class BgHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		if (path.matches(Const.INTERCEPTOR_PATH)) {
			// shiro管理的session
			BgUser bgUser = BgSessionUtil.getSessionBgUserRole();
			if (bgUser != null && bgUser.getBgRole() != null) {
				path = path.substring(1, path.length());
				boolean b = JudgeRightsUtil.hasRights(path,"all");
				if (!b) {
					response.sendRedirect(request.getContextPath() + Const.URL_BG_LOGIN_STR);
				}
				return b;
			} else {
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Const.URL_BG_LOGIN_STR);
				return false;
				// return true;
			}
		} else {
			return true;
		}
	}

}
