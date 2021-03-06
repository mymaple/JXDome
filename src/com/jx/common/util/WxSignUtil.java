package com.jx.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jx.common.service.ComWxAccountService;

/**
 * 类名: SignUtil </br>
 * 描述: 检验signature 工具类 </br>
 */
public class WxSignUtil {
    
    /**
     * @throws Exception 
     * 方法名：checkSignature</br>
     * 详述：验证签名</br>
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     * @throws
     */
    public static boolean checkSignature(String signature, String timestamp,String nonce) throws Exception {
        // 1.将token、timestamp、nonce三个参数进行字典序排序
    	ComWxAccountService comWxAccountService = (ComWxAccountService)SpringContextUtil.getBean("comWxAccountService");
    	String token = comWxAccountService.findCurrent().getToken();
    	
        String[] arr = new String[] { token, timestamp, nonce };
        Arrays.sort(arr);
        
        // 2. 将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        content = null;
        // 3.将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
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
	                  "&url=" + url;
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(string1.getBytes());
            signature = byteToStr(digest);
            
            
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
    

    /**
     * 方法名：byteToStr</br>
     * 详述：将字节数组转换为十六进制字符串</br>
     * @param byteArray
     * @return
     * @throws
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 方法名：byteToHexStr</br>
     * 详述：将字节转换为十六进制字符串</br>
     * @param mByte
     * @return
     * @throws
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A','B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }
    
    private static String create_nonce_str() {
		return StringUtils.right(String.valueOf(System.currentTimeMillis()), 10);
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}
	
}