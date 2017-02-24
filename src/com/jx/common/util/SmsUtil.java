package com.jx.common.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

import net.sf.json.JSONObject;

public class SmsUtil {
	
	// 开发者参数
	private static String XXK_DEVID = "252e97e423e24916b83997955846b5cd";
	private static String XXK_DEVKEY = "65a18ac5fe774a1eba17fa765c2a663e";
	private static String XXK_SENDURL = "http://www.xinxinke.com/api/send";
	private static String XXK_TEMPCODE_LOGIN = "chengta001";
	
	
	
	// 转码
	private static String encode(String input) throws Exception {
		return URLEncoder.encode(input, "UTF-8");
	}

	// 计算 MD5 值
	private static String md5(String input) throws Exception {
		byte[] digest = MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8"));
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < digest.length; i++) {
			sb.append(String.format("%02x", Integer.valueOf(digest[i] & 0xFF)));
		}
		return sb.toString();
	}
	
	// 发起 POST 请求
	private static String post(String url, String data) throws Exception {

		// 打开连接
		HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.connect();

		// 输出参数
		DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
		dos.writeBytes(data);
		dos.flush();
		dos.close();

		// 读取响应
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line = br.readLine();
		br.close();

		// 关闭连接
		conn.disconnect();

		return line;
	}
	
	
	/**
	 * 发送信息
	 * @param recNum				接收号码
	 * @param smsTemplateCode		短信模板代码
	 * @param smsParam				短信模板变量
	 * @return	25010	成功
	 * @throws Exception
	 */
	public static String xxk_tempSend(String recNum, String smsTemplateCode, JSONObject smsParam) throws Exception {

		// 参数
		String sign = md5(XXK_DEVID + XXK_DEVKEY + recNum);

		// 拼接
		StringBuffer data = new StringBuffer();
		data.append("").append("dev_id").append("=").append(encode(XXK_DEVID));
		data.append("&").append("sign").append("=").append(encode(sign));
		data.append("&").append("sms_template_code").append("=").append(encode(smsTemplateCode));
		data.append("&").append("sms_param").append("=").append(encode(smsParam.toString()));
		data.append("&").append("rec_num").append("=").append(encode(recNum));

		// 请求
		String resultStr = post(XXK_SENDURL, data.toString());
		JSONObject json = JSONObject.fromObject(resultStr);
		String code = json.getString("code");
		return code;
	}
	
	/**
	 * 发送登录验证码
	 * @param phone			接收号码
	 * @param captcha		验证码
	 * @return	25010		成功
	 * @throws Exception
	 */
	public static String xxk_login(String phone, String captcha) throws Exception {
		JSONObject smsParam = new JSONObject ();
		smsParam.put("code", captcha);
		return xxk_tempSend(phone, XXK_TEMPCODE_LOGIN, smsParam);
	}
	
}
