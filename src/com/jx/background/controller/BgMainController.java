package com.jx.background.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.DateUtil;
import com.jx.common.util.DrawImageUtil;
import com.jx.common.util.RightsHelper;
import com.jx.common.util.Tools;

/*
 * 总入口
 */
@Controller
@RequestMapping(value = "/background/main")
public class BgMainController extends BaseController {

	@Resource(name = "bgUserService")
	private BgUserService bgUserService;
	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;
	@Resource(name = "bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name = "bgConfigService")
	private BgConfigService bgConfigService;

	
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		pd = this.getPageData();
		
		pd.put("systemName", this.getSystemName()); // 读取系统名称
		pd.put("hasMusic", "no"); // 读取系统名称
		pd.put("hasRegister", "no"); // 读取系统名称
		
		mv.addObject("pd", pd);
		mv.setViewName("background/main/bgLogin");
		return mv;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {

		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		session.removeAttribute(Const.SESSION_BG_USER_OBJ);
		session.removeAttribute(Const.SESSION_BG_ROLEPERMISSIONS_STR);
		session.removeAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST);
		session.removeAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
		session.removeAttribute(Const.SESSION_BG_QX_STR);
		session.removeAttribute(Const.SESSION_BG_USERNAME_STR);
		session.removeAttribute(Const.SESSION_BG_USER_ROLE_OBJ);
		session.removeAttribute(Const.SESSION_BG_CHANGEMENU_STR);

		// shiro销毁登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = pd.getString("msg");
		pd.put("msg", msg);
		
		BgConfig bgConfigSystem = null;
		try {
			bgConfigSystem = bgConfigService.findByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pd.put("systemName", bgConfigSystem==null?"":bgConfigSystem.getParam1()); // 读取系统名称
		mv.addObject("pd", pd);
		mv.setViewName("background/main/bgLogin");
		
		return mv;
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String keyData[] = pd.getString("keyData").replaceAll("ndknsdkfjksdfj", "").replaceAll("kgnlkfsl", "").split(",jx,");

		if (null != keyData && keyData.length == 3) {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String sessionBgVerificationCode = (String) session.getAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR); // 获取session中的验证码

			String bgVerificationCode = keyData[2];
			
			//开发跳过、、登录
			bgVerificationCode = sessionBgVerificationCode;
			
			
			if (null == bgVerificationCode || "".equals(bgVerificationCode)) {
				errInfo = "nullcode"; // 验证码为空
			} else {
				String userName = keyData[0];
				String password = keyData[1];
				pd.put("userName", userName);
				if (Tools.notEmpty(sessionBgVerificationCode) && sessionBgVerificationCode.equalsIgnoreCase(bgVerificationCode)) {
					String passwd = new SimpleHash("SHA-1", userName, password).toString(); // 密码加密
					pd.put("password", passwd);
					BgUser bgUser = new BgUser();
					bgUser = bgUserService.checkUserLogin(pd);
					if (bgUser != null) {
						
						bgUser = this.changeLoginInfo(bgUser);
						
						session.setAttribute(Const.SESSION_BG_USER_OBJ, bgUser);
						session.removeAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR);

						// shiro加入身份验证
						Subject subject = SecurityUtils.getSubject();
						UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
						try {
							subject.login(token);
						} catch (AuthenticationException e) {
							errInfo = "身份验证失败！";
						}
					} else {
						errInfo = "usererror"; // 用户名或密码有误
					}
				} else {
					errInfo = "codeerror"; // 验证码输入有误
				}
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success"; // 验证成功
				}
			}
		} else {
			errInfo = "error"; // 缺少参数
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 访问系统首页
	 */
	@RequestMapping(value = "/{changeMenu}")
	public ModelAndView loginToIndex(@PathVariable("changeMenu") String changeMenu) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Session session = this.getSession();

			BgUser bgUser = (BgUser) session.getAttribute(Const.SESSION_BG_USER_OBJ);
			if (bgUser != null) {
				if (null == bgUser.getBgRole()) {
					bgUser = bgUserService.getUserRoleById(bgUser.getUserId());
					session.setAttribute(Const.SESSION_BG_USER_OBJ, bgUser);
				}
				BgRole bgRole = bgUser.getBgRole();
				String roleRights = bgRole != null ? bgRole.getRoleRights() : "";

				List<BgMenu> bgAllMenuInRankList = new ArrayList<BgMenu>();
				if (null == session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST)) {
					bgAllMenuInRankList = bgMenuService.listAllMenuInRank(0);
					if (Tools.notEmpty(roleRights)) {
						this.subBgMenuListTestRights(bgAllMenuInRankList,roleRights);
					}
					session.setAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST, bgAllMenuInRankList); // 菜单权限放入session中
				} else {
					bgAllMenuInRankList = (List<BgMenu>) session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST);
				}

				// 切换菜单=====
				List<BgMenu> bgMenuInCurrentList = new ArrayList<BgMenu>();
				if (null == session.getAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST) || ("yes".equals(changeMenu))) {
					List<BgMenu> bgMenuInCurrentList1 = new ArrayList<BgMenu>();
					List<BgMenu> bgMenuInCurrentList2 = new ArrayList<BgMenu>();

					// 拆分菜单
					for (int i = 0; i < bgAllMenuInRankList.size(); i++) {
						BgMenu bgMenu = bgAllMenuInRankList.get(i);
						if ("1".equals(bgMenu.getMenuType())) {
							bgMenuInCurrentList1.add(bgMenu);
						} else {
							bgMenuInCurrentList2.add(bgMenu);
						}
					}

					session.removeAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
					if ("2".equals(session.getAttribute("changeMenu"))) {
						session.setAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST, bgMenuInCurrentList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						bgMenuInCurrentList = bgMenuInCurrentList1;
					} else {
						session.setAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST, bgMenuInCurrentList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						bgMenuInCurrentList = bgMenuInCurrentList2;
					}
				} else {
					bgMenuInCurrentList = (List<BgMenu>) session.getAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
				}

				// FusionCharts 报表
				String strXML = "<graph caption='前12个月订单销量柱状图' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='2013-05' value='4' color='AFD8F8'/><set name='2013-04' value='0' color='AFD8F8'/><set name='2013-03' value='0' color='AFD8F8'/><set name='2013-02' value='0' color='AFD8F8'/><set name='2013-01' value='0' color='AFD8F8'/><set name='2012-01' value='0' color='AFD8F8'/><set name='2012-11' value='0' color='AFD8F8'/><set name='2012-10' value='0' color='AFD8F8'/><set name='2012-09' value='0' color='AFD8F8'/><set name='2012-08' value='0' color='AFD8F8'/><set name='2012-07' value='0' color='AFD8F8'/><set name='2012-06' value='0' color='AFD8F8'/></graph>";
				mv.addObject("strXML", strXML);
				// FusionCharts 报表

				// 读取websocket配置
				BgConfig bgConfigOnlineManage = (BgConfig) session.getAttribute(Const.CONFIG_BG_ONLINEMANAGE_OBJ);
				if (bgConfigOnlineManage == null) {
					bgConfigOnlineManage = bgConfigService.findByConfigType(Const.CONFIG_BG_ONLINEMANAGE_OBJ);
					session.setAttribute(Const.CONFIG_BG_ONLINEMANAGE_OBJ,bgConfigOnlineManage);
				}
				
				BgConfig bgConfigInstantChat = (BgConfig) session.getAttribute(Const.CONFIG_BG_INSTANTCHAT_OBJ);
				if (bgConfigInstantChat == null) {
					bgConfigInstantChat = bgConfigService.findByConfigType(Const.CONFIG_BG_INSTANTCHAT_OBJ);
					session.setAttribute(Const.CONFIG_BG_INSTANTCHAT_OBJ,bgConfigInstantChat);
				}
				
				pd.put("onlineManage", bgConfigOnlineManage.getParam1()+":"+bgConfigOnlineManage.getParam2());
				pd.put("instantChat",bgConfigInstantChat.getParam1()+":"+bgConfigInstantChat.getParam2());
				// 读取websocket配置
				mv.addObject("bgUser", bgUser);
				mv.addObject("bgMenuInCurrentList", bgMenuInCurrentList);
				mv.setViewName("background/main/bgIndex");
			} else {
				mv.setViewName("background/main/bgLogin");// session失效后跳转登录页面
			}
			
			pd.put("systemName", this.getSystemName()); // 读取系统名称

		} catch (Exception e) {
			mv.setViewName("background/main/bgLogin");
			logger.error(e.getMessage(), e);
		}
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value = "/tab")
	public String tab() {
		return "background/main/bgTab";
	}

	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value = "/default")
	public String defaultPage() {
		return "background/main/bgDefault";
	}
	
	public BgUser changeLoginInfo(BgUser bgUser) throws Exception {
		HttpServletRequest request = this.getRequest();
		String loginIp = "";
		if (request.getHeader("x-forwarded-for") == null) {
			loginIp = request.getRemoteAddr();
		} else {
			loginIp = request.getHeader("x-forwarded-for");
		}
		bgUser.setLastLoginTime(new Date());
		bgUser.setLastLoginIp(loginIp);
		bgUserService.changeLoginInfo(bgUser);
		return bgUser;
	}
	
	/**根据角色权限获取本权限的菜单列表(递归处理)
	 * @param menuList：传入的总菜单
	 * @param roleRights：加密的权限字符串
	 * @return
	 */
	public List<BgMenu> subBgMenuListTestRights(List<BgMenu> subBgMenuList,String roleRights){
		
		for (BgMenu bgMenu : subBgMenuList) {
			bgMenu.setHasRight(RightsHelper.testRights(roleRights, bgMenu.getMenuId()));
			if (bgMenu.isHasRight()) {
				this.subBgMenuListTestRights(bgMenu.getSubBgMenuList(), roleRights);//是：继续排查其子菜
			}
		}
		return subBgMenuList;
	}
	
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping(value = "/getVerificationCode")
	public void getVerificationCode(HttpServletResponse response) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String verificationCode = DrawImageUtil.drawImg(output);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR, verificationCode);

		try {
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getSystemName() {
		Session session = this.getSession();
		BgConfig bgConfigSystem = (BgConfig) session.getAttribute(Const.CONFIG_BG_SYSTEM_OBJ);
		
		if (bgConfigSystem == null) {
			try {
				bgConfigSystem = bgConfigService.findByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
				session.setAttribute(Const.CONFIG_BG_SYSTEM_OBJ,bgConfigSystem);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return bgConfigSystem.getParam1();
	}

}
