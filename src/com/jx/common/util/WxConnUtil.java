package com.jx.common.util;

import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jx.common.config.Const;
import com.jx.common.entity.ComWxAccount;
import com.jx.common.service.ComWxAccountService;

import net.sf.json.JSONObject;

public class WxConnUtil {
	
    
	public static void main(String[] args) throws Exception {
/*    	String url = "http://wx.qlogo.cn/mmopen/ShDz8TqtUV6SNSwGscRgD0nhSMINEpHAkRNaQuDukgOQsFjXDl4YqBrNeDh8m54dXguTCR8rWNFqJfeASKKQW7AcL1ic4S8r5/0";
    	String fileSrc = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/111_headimg.jpg";
    	System.out.println(fileSrc);
		
		HttpManager.download(url, null, fileSrc);
*/		
	}
	
	/**
	 * 获得 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken() throws Exception{
		ComWxAccountService comWxAccountService = 
				(ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		String accessToken = comWxAccountService.findCurrent().getAccessToken();	
		return accessToken;
	 }
	
	/**
	 * 获得 公众号的全局唯一票据
	 * @param appId 	第三方用户唯一凭证
	 * @param appSecret	第三方用户唯一凭证密钥
	 * @return
	 * @throws Exception
	 */
	public static String getAccessToken(String appId, String appSecret) throws Exception{
		String accessToken = "";
		String urlStr = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+appSecret); 
		String resultStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null)
			accessToken = json.getString("access_token");
		return accessToken;
	 }
	   
	/**
	 * 获得 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String getJsApiTicket() throws Exception{
		String accessToken = getAccessToken();
		int errcode = 0;
		String jsApiTicket = "";
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		String resultStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			errcode = (Integer) json.get("errcode");
			if(0==errcode){
				jsApiTicket = (String) json.get("ticket");
			}
		}
	    System.out.println("-----------accessToken:"+accessToken+"-----------------------jsApiTicket:"+jsApiTicket);
		return jsApiTicket;
	}
	
	/**
	 * 创建公众号菜单按钮 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static int toCreateMenuBtn(String menuBtnStr) throws Exception{
		String accessToken = getAccessToken();
		int errcode = 0;
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		String resultStr = HttpManager.post(urlStr, menuBtnStr);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			errcode = (Integer) json.get("errcode");
			if(0!=errcode){
				//log
				System.out.println(json);
			}
		}
		return errcode;
	}
	
	/**
	 * 获得当前公众号菜单按钮 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String getMenuBtn() throws Exception{
		String accessToken = getAccessToken();
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;
		String resultStr = HttpManager.get(urlStr, null);
		if(resultStr.contains("errcode")){
			//log
			resultStr="";
		}
		return resultStr;
	}
	
	/**
	 * 删除公众号菜单按钮 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String toStopMenuBtn() throws Exception{
		String accessToken = getAccessToken();
		String errcode = "";
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		String resultStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			if(json.containsKey("errcode")){
				errcode = json.getString("errcode");
				if(!"".equals(errcode)){
					//log
				}
			}else{
			
			}
		}
		return errcode;
	}
	
	/**
	 * 获取用户基本信息
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String getUserInfo(String openId) throws Exception{
		String accessToken = getAccessToken();
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+accessToken+"&openid="+openId+"&lang=zh_CN";
		String resultStr = HttpManager.get(urlStr, null);
		if(resultStr.contains("errcode")){
			//log
			resultStr="";
		}
		return resultStr;
	}
	
	/**
	 * 生成临时二维码，获得 临时二维码票据
	 * @param accessToken 公众号的全局唯一票据
	 * @param expire_seconds 最多30天 30*24*60*60 =259200
	 * @return
	 * @throws Exception
	 */
	public static String getQRCodeTicket(String markStr, int expire_seconds) throws Exception{
		String accessToken = getAccessToken();
		String ticket = "";
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token="+accessToken;
		String params = "{\"expire_seconds\": "+expire_seconds+", \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": \""+markStr+"\"}}}";
		String resultStr = HttpManager.post(urlStr, params);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			if(json.containsKey("errcode")){
				json.getString("errcode");
			}else{
				ticket = json.getString("ticket");
			}
		}
	    System.out.println("-----------------------getQRCodeTicket:"+ticket);
		return ticket;
	}
	
	/**
	 * 将二维码保存本地
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static void toSaveQRCode(String ticket, String fileSrc) throws Exception{
		// 拼装请求地址  
		String urlStr = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket="+ticket;
		HttpManager.download(urlStr, null, fileSrc);
	}
	
	/**
	 * 
	 * @param fileSrc	文件路径
	 * @param type		媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * @return
	 * @throws Exception
	 */
	public static String getMediaId(String fileSrc, String type) throws Exception{
		String accessToken = getAccessToken();
		String mediaId = "";
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type="+type;
		String resultStr = HttpManager.upload(urlStr, fileSrc);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			if(json.containsKey("errcode")){
				json.getString("errcode");
				//log
			}else{
				mediaId = json.getString("media_id");
			}
		}
		return mediaId;
	}
	
	/**
	 * 主动推送信息(非模板)
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static void toSendText(String openId, String content) throws Exception{
		String accessToken = getAccessToken();
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken; 
		String message = "{\"touser\": \""+openId+"\", \"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";
		String resultStr = HttpManager.post(urlStr, message);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			if(json.containsKey("errcode")){
				//log
			}
		}
	}
	
	
	public static void toRedirect(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String redictUrl = PathUtil.allUrl(request);
		redictUrl = URLEncoder.encode(redictUrl,"utf-8");
		ComWxAccountService comWxAccountService = 
				(ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		ComWxAccount comWxAccount = comWxAccountService.findCurrent();
		String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+comWxAccount.getAppId()+"&redirect_uri="+redictUrl+"&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
		System.out.println("url------------"+url);
		response.sendRedirect(url);
	}
	
	/**
	 * 获得 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static String getMyOpenId(String code) throws Exception{
		String openId = "";
		
		ComWxAccountService comWxAccountService = 
				(ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
		ComWxAccount comWxAccount = comWxAccountService.findCurrent();
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+comWxAccount.getAppId()+"&secret="+comWxAccount.getAppSecret()+"&code="+code+"&grant_type=authorization_code";
		String resultStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resultStr); 
		if(json != null){
			//获取返回码
			if(json.containsKey("errcode")){
				//log
			}else{
				openId = (String) json.get("openid");
			}
		}
		return openId;
	}
}
