package com.jx.background.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx.background.entity.BgUser;
import com.jx.common.config.Const;
import com.jx.common.util.Jurisdiction;

/**
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class BgHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if (path.matches(Const.INTERCEPTOR_PATH)) {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			BgUser bgUser = (BgUser) session.getAttribute(Const.SESSION_BG_USER_OBJ);
			if (bgUser != null && bgUser.getBgRole() != null) {
				path = path.substring(1, path.length());
//				boolean b = Jurisdiction.hasJurisdiction(path);
				boolean b = true;
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
