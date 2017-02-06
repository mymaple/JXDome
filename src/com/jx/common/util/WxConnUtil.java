package com.jx.common.util;

import net.sf.json.JSONObject;

public class WxConnUtil {
	
    public static void main(String[] args) throws Exception {
    	
		System.out.println(getAccessToken("wxf3a522044df3f3df", "2028ab8d1cdb5c5e918bfb2c3f4c47f5"));
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
	 * 获得 
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
				
			}
		}
		return errcode;
	}
	
	
	
}
