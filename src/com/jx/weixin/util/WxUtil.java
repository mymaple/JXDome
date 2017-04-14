package com.jx.weixin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jx.common.util.WxConnUtil;

public class WxUtil {

	public static String getWxMyOpenId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String openId = WxSessionUtil.getMyOpenId();
		openId = "oAQf_wgU22N3diLH4TEqxu_8j6Rk";
		WxSessionUtil.setMyOpenId(openId);
		if (StringUtils.isEmpty(openId)) {
			String code = request.getParameter("code");
			if (StringUtils.isEmpty(code)) {
				WxConnUtil.toRedirect(request, response);
			} else {
				openId = WxConnUtil.getMyOpenId(code);
				WxSessionUtil.setMyOpenId(openId);
			}
		}
		return openId;
	}

	public static Map<String, String> sign(String url) {
		Map<String, String> ret = new HashMap<String, String>();
		
		try {
			// 这里的jsapi_ticket是获取的jsapi_ticket。
			String jsapi_ticket = WxConnUtil.getJsApiTicket();
			String nonceStr = create_nonce_str();
			String timestamp = create_timestamp();
			String string1;
			String signature = "";

			//注意这里参数名必须全部小写，且必须有序
	        string1 = "jsapi_ticket=" + jsapi_ticket +
	                  "&noncestr=" + nonceStr +
	                  "&timestamp=" + timestamp +
	                  "&url=" + url;MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			/*crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());*/
	                  
	                  MessageDigest messageDigest = MessageDigest.getInstance("sha1");  
	                  messageDigest.update(string1.getBytes());  
	                    
	                   char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };   
	                   byte[] bytes= messageDigest.digest();  
	                      int len = bytes.length;  
	                      StringBuilder buf = new StringBuilder(len * 2);   
	                      for (int j = 0; j < len; j++) {            
	                          buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);   
	                          buf.append(HEX_DIGITS[bytes[j] & 0x0f]);   
	                      } 
	                      signature = buf.toString();
			ret.put("appId", WxConnUtil.getAppId());
			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("nonceStr", nonceStr);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);
		
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return StringUtils.right(String.valueOf(System.currentTimeMillis()), 10);
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

}
