package com.jx.common.util;

import net.sf.json.JSONObject;

public class WxConnUtil {
	
    public static void main(String[] args) throws Exception {
    	
    	String accessToken = getAccessToken("wxf3a522044df3f3df", "2028ab8d1cdb5c5e918bfb2c3f4c47f5");
		System.out.println(accessToken);
		
		
		
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
		String resulrStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resulrStr); 
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
	public static String getJsApiTicket(String accessToken) throws Exception{
		int errcode = 0;
		String jsApiTicket = "";
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		String resulrStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resulrStr); 
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
	public static int toCreateMenuBtn(String accessToken, String menuBtnStr) throws Exception{
		int errcode = 0;
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken;
		String resulrStr = HttpManager.post(urlStr, menuBtnStr);
		JSONObject json = JSONObject.fromObject(resulrStr); 
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
	public static String getMenuBtn(String accessToken) throws Exception{
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="+accessToken;
		String resulrStr = HttpManager.get(urlStr, null);
		if(resulrStr.contains("errcode")){
			//log
			resulrStr="";
		}
		return resulrStr;
	}
	
	/**
	 * 删除公众号菜单按钮 
	 * @param accessToken 公众号的全局唯一票据
	 * @return
	 * @throws Exception
	 */
	public static int toStopMenuBtn(String accessToken) throws Exception{
		int errcode = 0;
		// 拼装请求地址  
		String urlStr = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+accessToken;
		String resulrStr = HttpManager.get(urlStr, null);
		JSONObject json = JSONObject.fromObject(resulrStr); 
		if(json != null){
			//获取返回码
			errcode = (Integer) json.get("errcode");
			if(0!=errcode){
				//log
			}
		}
		return errcode;
	}
	
	
	
}
