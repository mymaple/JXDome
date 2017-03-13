package com.jx.background.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRights;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.background.util.BgSessionUtil;
import com.jx.background.util.JudgeRightsUtil;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.MapleStringUtil;
import com.jx.common.util.DrawImageUtil;

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
		try{
			pd = this.getPageData();
			BgConfig bgConfigSystem = new BgConfig();
			bgConfigSystem = bgConfigService.findSessionConfig(Const.CONFIG_BG_SYSTEM_OBJ);
			pd.put("systemName", bgConfigSystem==null?"":bgConfigSystem.getParam1()); // 读取系统名称
			pd.put("hasMusic", "no"); // 读取系统名称
			pd.put("hasRegister", "no"); // 读取系统名称
			
			mv.addObject("pd", pd);
			mv.setViewName("background/main/bgLogin");
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() throws Exception {

		// shiro销毁登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = this.getPageData();
			
		mv.addObject("pd", pd);
		mv.setViewName("redirect:toLogin");
		
		return mv;
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "";
		
		try{
			pd = this.getPageData();
			String keyData[] = pd.getString("keyData").replaceAll("ndknsdkfjksdfj", "")
										.replaceAll("kgnlkfsl", "").split(",jx,");
			if (null != keyData && keyData.length == 3) {
				String sessionCaptcha = BgSessionUtil.getCaptcha();
				String captcha = keyData[2];
				//开发跳过、、登录
				captcha = sessionCaptcha;
				if (null == captcha || "".equals(captcha)) {
					errInfo = "nullcode"; // 验证码为空
				} else {
					String userCode = keyData[0];
					String password = keyData[1];
					pd.put("userCode", userCode);
					if (MapleStringUtil.notEmpty(sessionCaptcha) 
							&& sessionCaptcha.equalsIgnoreCase(captcha)) {
						String passwd = new SimpleHash("SHA-512", userCode, password, 2).toString(); // 密码加密
						pd.put("password", passwd);
						BgUser bgUser = new BgUser();
						//登录验证
						bgUser = bgUserService.checkUserLogin(pd);//有问题
						if (bgUser != null) {
							//修改登录
//							bgUser = this.changeLoginInfo(bgUser);
							BgSessionUtil.removeCaptcha();
							
							// shiro加入身份验证
							Subject subject = SecurityUtils.getSubject();
							UsernamePasswordToken token = new UsernamePasswordToken(bgUser.getUserId(), passwd);
							try {
								subject.login(token);
								BgSessionUtil.setUserId(bgUser.getUserId());
								BgSessionUtil.setUser(bgUser);
								System.out.println("aaaaaaa--------------------"+passwd);
							} catch (AuthenticationException e) {
								errInfo = "身份验证失败！";
							}
						} else {
							errInfo = "usererror"; // 用户名或密码有误
						}
					} else {
						errInfo = "codeerror"; // 验证码输入有误
					}
					if (MapleStringUtil.isEmpty(errInfo)) {
						errInfo = "success"; // 验证成功
					}
				}
			} else {
				errInfo = "error"; // 缺少参数
			}
			map.put("result", errInfo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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
			//获取当前用户 和 角色
			BgUser bgUser = (BgUser)BgSessionUtil.getUser();
			if (bgUser != null) {
				if (null == bgUser.getBgRole()) {
					bgUser.setBgRole(bgRoleService.findById(bgUser.getRoleId()));
					
					bgUser.setAdmin("1".equals(bgUser.getBgRole().getRoleId())
							&&bgUser.getUserCode().equals(bgConfigService.findSessionConfig(Const.CONFIG_BG_SYSTEM_OBJ).getParam3()));
					BgSessionUtil.setUser(bgUser);
				}
				BgRole bgRole = bgUser.getBgRole();
				String roleRights = bgRole != null ? bgRole.getRoleRights() : "";
				
				//获取当前角色菜单列表
				List<BgMenu> bgAllMenuInRankList = new ArrayList<BgMenu>();
				if (null == BgSessionUtil.getSessionBgAllMenuInRankList()) {
					bgAllMenuInRankList = bgMenuService.listInRank("0");
					if (MapleStringUtil.notEmpty(roleRights)) {
						JudgeRightsUtil.getBgMenuListByRoleRights(bgAllMenuInRankList,roleRights);
					}
					BgSessionUtil.setSessionBgAllMenuInRankList( bgAllMenuInRankList); // 菜单权限放入session中
				} else {
					bgAllMenuInRankList = BgSessionUtil.getSessionBgAllMenuInRankList();
				}

				// 切换菜单=====
				List<BgMenu> bgMenuInCurrentList = new ArrayList<BgMenu>();
				if (null == BgSessionUtil.getSessionBgMenuInCurrentList() || ("yes".equals(changeMenu))) {
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
					if ("2".equals(BgSessionUtil.getSessionBgChangeMenu())) {
						BgSessionUtil.setSessionBgMenuInCurrentList(bgMenuInCurrentList1);
						BgSessionUtil.setSessionBgChangeMenu("1");
						bgMenuInCurrentList = bgMenuInCurrentList1;
					} else {
						BgSessionUtil.setSessionBgMenuInCurrentList(bgMenuInCurrentList2);
						BgSessionUtil.setSessionBgChangeMenu("2");
						bgMenuInCurrentList = bgMenuInCurrentList2;
					}
				} else {
					bgMenuInCurrentList = BgSessionUtil.getSessionBgMenuInCurrentList();
				}
				
				BgRights bgRights = new BgRights();
				BgSessionUtil.setSessionBgRights(bgRights);
				
				
				// FusionCharts 报表
				String strXML = "<graph caption='前12个月订单销量柱状图' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='2013-05' value='4' color='AFD8F8'/><set name='2013-04' value='0' color='AFD8F8'/><set name='2013-03' value='0' color='AFD8F8'/><set name='2013-02' value='0' color='AFD8F8'/><set name='2013-01' value='0' color='AFD8F8'/><set name='2012-01' value='0' color='AFD8F8'/><set name='2012-11' value='0' color='AFD8F8'/><set name='2012-10' value='0' color='AFD8F8'/><set name='2012-09' value='0' color='AFD8F8'/><set name='2012-08' value='0' color='AFD8F8'/><set name='2012-07' value='0' color='AFD8F8'/><set name='2012-06' value='0' color='AFD8F8'/></graph>";
				mv.addObject("strXML", strXML);
				// FusionCharts 报表

				// 读取websocket配置
				BgConfig bgConfigOnlineManage = new BgConfig();
				BgConfig bgConfigInstantChat = new BgConfig();
				bgConfigOnlineManage = bgConfigService.findSessionConfig(Const.CONFIG_BG_ONLINEMANAGE_OBJ);
				bgConfigOnlineManage = bgConfigService.findSessionConfig(Const.CONFIG_BG_INSTANTCHAT_OBJ);
				
				pd.put("onlineManage", bgConfigOnlineManage.getParam1()+":"+bgConfigOnlineManage.getParam2());
				pd.put("instantChat",bgConfigInstantChat.getParam1()+":"+bgConfigInstantChat.getParam2());

				mv.addObject("bgUser", bgUser);
				mv.addObject("bgMenuInCurrentList", bgMenuInCurrentList);
				mv.setViewName("background/main/bgIndex");
			} else {
				mv.setViewName("background/main/bgLogin");// session失效后跳转登录页面
			}
			
			BgConfig bgConfigSystem = new BgConfig();
			bgConfigSystem = bgConfigService.findSessionConfig(Const.CONFIG_BG_SYSTEM_OBJ);
			pd.put("systemName", bgConfigSystem==null?"":bgConfigSystem.getParam1()); // 读取系统名称

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
	
/*	public BgUser changeLoginInfo(BgUser bgUser) throws Exception {
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
	}*/
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping(value = "/getCaptcha")
	public void getCaptcha(HttpServletResponse response) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String captcha = DrawImageUtil.drawImg(output);

		try {
			BgSessionUtil.setCaptcha(captcha);;
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
