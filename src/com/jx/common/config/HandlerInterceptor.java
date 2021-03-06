package com.jx.common.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.jx.background.entity.BgUser;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.weixin.util.WxUtil;

/**
 * 类名称：LoginHandlerInterceptor.java 类描述：
 * @author FH 作者单位： 联系方式： 创建时间：2015年1月1日
 * @version 1.6
 */
public class HandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String path = request.getServletPath();
		System.out.println("path-------------------"+path);
		String[] pathArr = path.split("/");
		if(pathArr.length > 3){
			//分隔"?"需特殊处理
			String ss = pathArr[3].split(".do")[0].split("//?")[0];
			
			if("background".equals(pathArr[1])){
				if(!"main".equals(pathArr[2])){
					// shiro管理的session
					BgUser bgUser = (BgUser)ShiroSessionUtil.getUser();
					if (bgUser != null && bgUser.getBgRole() != null) {
						String type = "";
						if("main".equals(ss)||"list".equals(ss)){
							type = "sele";
						}else if("toAdd".equals(ss)||"add".equals(ss)){
							type = "add";
						}else if("toDelete".equals(ss)||"toBatchDelete".equals(ss)){
							type = "del";
						}else if("toEdit".equals(ss)||"edit".equals(ss)||"toChange".equals(ss)||"change".equals(ss)){
							type = "edit";
						}else{
							return true;
						}
						boolean b = JudgeRightsUtil.hasRights(pathArr[1]+"_"+pathArr[2].split("Detail")[0], type);
						if (!b) {
							response.sendRedirect(request.getContextPath() + Const.PATH_BG_TOLOGIN_STR);
						}
						return b;
					} else {
						// 登陆过滤
						response.sendRedirect(request.getContextPath() + Const.PATH_BG_TOLOGIN_STR);
						return false;
					}
				}
			}
			else if("weixin".equals(pathArr[1])){
				//获取myOpenId
				String myOpenId = WxUtil.getWxMyOpenId(request, response);
				if(StringUtils.isEmpty(myOpenId)){
					return false;
				}
				if(!"main".equals(pathArr[2])){
					String userId = ShiroSessionUtil.getUserId();
					if(StringUtils.isEmpty(userId)){
						response.sendRedirect(request.getContextPath() + Const.PATH_WX_TOLOGIN_STR);
						return false;
					}
				}
			}
		}
		return true;
		
/*		if (path.matches(Const.INTERCEPTOR_PATH)) {
			// shiro管理的session
			BgUser bgUser = ShiroSessionUtil.getUserId();
			if (bgUser != null && bgUser.getBgRole() != null) {
				String[] pathArr = path.split("/");
				
				path = path.substring(1, path.length());
				boolean b = JudgeRightsUtil.hasRights(path,"all");
				if (!b) {
					response.sendRedirect(request.getContextPath() + Const.URL_BG_LOGIN_STR);
				}
				return b;
				return true;
			} else {
				// 登陆过滤
				response.sendRedirect(request.getContextPath() + Const.URL_BG_LOGIN_STR);
				return false;
				// return true;
			}
		} else {
			return true;
		}*/
	}

}
