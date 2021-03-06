package com.jx.common.config;

import org.springframework.context.ApplicationContext;

/**
 * 全局静态变量命名规则  作用_服务包_名称_类型
 */
public class Const {
	
	
	/**
	 * 版本
	 */
	public static final String COM_VERSION = "0004";
	
	/**
	 * 后台网址
	 */
	public static final String BG_WEBSITE = "http://bg.gemojp.com";
//	public static final String BG_WEBSITE = "http://1b62112g29.iok.la/JXDome";
	
	/**
	 * 真
	 */
	public static final String TRUE = "TRUE";
	
	/**
	 * 假
	 */
	public static final String FALSE = "FALSE";
	
	
	/**
	 * 后台 登录地址
	 */
	public static final String PATH_BG_TOLOGIN_STR = "/background/main/toLogin.do";
	
	/**
	 * 后台 登录地址
	 */
	public static final String PATH_BG_TOLOGOUT_STR = "/background/main/toLogout.do";
	
	/**
	 * 微信 登录地址
	 */
	public static final String PATH_WX_TOLOGIN_STR = "/weixin/main/toLogin.do";
	
	/**
	 * 微信 登录地址
	 */
	public static final String PATH_WX_ROLOGOUT_STR = "/weixin/main/toLogout.do";
	
	
	/**
	 * 后台 系统配置
	 */
	public static final String CONFIG_BG_SYSTEM_OBJ = "configBgSystem";
	
	/**
	 * 后台 邮箱服务器配置
	 */
	public static final String CONFIG_BG_EMAILSERVER_OBJ = "configBgEmailServer";
	
	/**
	 * 后台 短信账户配置配置
	 */
	public static final String CONFIG_BG_MESSAGE_STR = "configBgMessage";
	
	/**
	 * 后台 文字水印配置
	 */
	public static final String CONFIG_BG_WORDWATERMARK_OBJ = "configBgWordWaterMark";
	
	/**
	 * 后台 图片水印配置
	 */
	public static final String CONFIG_BG_IMAGEWATERMARK_OBJ = "configBgImageWaterMark";
	
	/**
	 * 后台 微信接口配置
	 */
	public static final String CONFIG_BG_WEIXIN_OBJ = "configBgWeiXin";
	
	/**
	 * 后台 即时聊天服务器配置
	 */
	public static final String CONFIG_BG_INSTANTCHAT_OBJ = "configBgInstantChat";
	
	/**
	 * 后台 在线管理服务器配置
	 */
	public static final String CONFIG_BG_ONLINEMANAGE_OBJ = "configBgOnlineManage";
	
	/**
	 * 路径 文件上传缓存
	 */
	public static final String PATH_FILEUPCACHE = "uploadFiles/cache";
	
	
	
	
	/**
	 * 标记 登录直接跳转
	 */
	public static final String FLAG_DIRECTLOGIN = "direct";
	
	
	
	
	
	
//	public static final String SYSNAME = "admin/config/SYSNAME.txt"; // 系统名称路径
	
	public static final String PAGE = "admin/config/PAGE.txt"; // 分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt"; // 邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt"; // 短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt"; // 短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt"; // 文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt"; // 图片水印配置路径
	public static final String WEIXIN = "admin/config/WEIXIN.txt"; // 微信配置路径
//	public static final String WEBSOCKET = "admin/config/WEBSOCKET.txt";// WEBSOCKET配置路径
	public static final String WEBSOCKET = "127.0.0.1,jx,8887,jx,127.0.0.1,jx,8889";
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/"; // 图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; // 二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static final String INTERCEPTOR_PATH = "^/background/(?!((verificationCode)|(main))).*";
	
	/**
	 * 分隔符标记
	 */
	public static final String REG_COM_SPLIT = "_maple,";
	
	/**
	 * 正则 非负整数
	 */
	public static final String REG_COM_FFZS_STR = "^[1-9]\\d*$|^0$";
	
	/**
	 * 正则 正整数
	 */
	public static final String REG_COM_ZZS_STR = "^[1-9]\\d*$";
	
	/**
	 * 正则 非负小数
	 */
	public static final String REG_COM_FFXS_STR = "^(?!0+(?:\\.0+)?$)(?:[1-9]\\d*|0)(?:\\.\\d{1,2})?$|^0$";
	
	/**
	 * 正则 正小数
	 */
	public static final String REG_COM_ZXS_STR = "^(?!0+(?:\\.0+)?$)(?:[1-9]\\d*|0)(?:\\.\\d{1,2})?$";
	
	/**
	 * 正则 0-1小数
	 */
	public static final String REG_COM_XS_STR = "^0(\\.\\d{1,2})?$|^1(\\.0|\\.00)?$";
	
	/**
	 * 正则 以小写字母开头的字母数字
	 */
	public static final String REG_COM_CODE_STR = "^[a-z][a-zA-Z0-9_]*$";
	
	/**
	 * 正则 手机号码
	 */
	public static final String REG_COM_PHONE_STR = "^1[34578]\\d{9}$";
	
	/**
	 * 正则 电子邮箱
	 */
	public static final String REG_COM_EMAIL_STR = "^(?:[a-zA-Z0-9]+[_\\-\\+\\.]?)*[a-zA-Z0-9]+@(?:([a-zA-Z0-9]+[_\\-]?)*[a-zA-Z0-9]+\\.)+([a-zA-Z]{2,})+$";
	
	
	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化

	/**
	 * APP Constants
	 */
	// app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[] { "countries", "uname", "passwd", "title", "full_name", "company_name", "countries_code", "area_code", "telephone", "mobile" };
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[] { "国籍", "邮箱帐号", "密码", "称谓", "名称", "公司名称", "国家编号", "区号", "电话", "手机号" };

	// app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[] { "USERNAME" };
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[] { "用户名" };

}
