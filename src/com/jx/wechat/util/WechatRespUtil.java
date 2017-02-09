package com.jx.wechat.util;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.jx.common.util.MapleUtil;
import com.jx.wechat.entity.event.LocationEvent;
import com.jx.wechat.entity.event.MenuEvent;
import com.jx.wechat.entity.event.QRCodeEvent;
import com.jx.wechat.entity.event.SubscribeEvent;
import com.jx.wechat.entity.messageReq.ImageMessageReq;
import com.jx.wechat.entity.messageReq.LinkMessageReq;
import com.jx.wechat.entity.messageReq.LocationMessageReq;
import com.jx.wechat.entity.messageReq.TextMessageReq;
import com.jx.wechat.entity.messageReq.VideoMessageReq;
import com.jx.wechat.entity.messageReq.VoiceMessageReq;
import com.jx.wechat.entity.messageResp.ImageMessageResp;
import com.jx.wechat.entity.messageResp.TextMessageResp;

public class WechatRespUtil {
	
	public static void main(String[] agrs) throws IllegalAccessException, InvocationTargetException, IntrospectionException {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("ToUserName", "aaaaaaaaaaaaaaaa");
		requestMap.put("FromUserName", "bbbbbbbbbbbbb");
		requestMap.put("CreateTime", "32454654534132123");
		requestMap.put("MsgType", "text");
		requestMap.put("Content", "asdasdasdasdasd");
		requestMap.put("MsgId", "2312312312312312123");
		
//		TextMessageReq messageReq = new TextMessageReq();
//		messageReq.setToUserName("aaaaaaaaaaaaaaaa");
//		messageReq.setFromUserName("bbbbbbbbbbbbb");
//		messageReq.setCreateTime(32454654534132123l);
//		messageReq.setMsgType("text");
//		messageReq.setContent("asdasdasdasdasd");
//		messageReq.setMsgId(2312312312312312123l);
//		Map requestMap = MapleUtil.convertBean(messageReq);
		
		
		try {
			System.out.println(reqText(requestMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * 回复默认文本消息内容
     */
    public static final String RESP_MESSAGE_TEXT_CONTENT_DEFAULT = "客服正在赶来的路上，请稍候！";
	
	/**
	 * 接收	文本消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqText(Map<String, String> requestMap) throws Exception {
		
		TextMessageReq messageReq = new TextMessageReq();
		messageReq = (TextMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
        messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
        
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	图片消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqImage(Map<String, String> requestMap) throws Exception {
		
		ImageMessageReq messageReq = new ImageMessageReq();
		messageReq = (ImageMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	语音消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqVoice(Map<String, String> requestMap) throws Exception {
		
		VoiceMessageReq messageReq = new VoiceMessageReq();
		messageReq = (VoiceMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	视频消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqVideo(Map<String, String> requestMap) throws Exception {
		
		VideoMessageReq messageReq = new VideoMessageReq();
		messageReq = (VideoMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	短视频消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqShortVideo(Map<String, String> requestMap) throws Exception {
		
		VideoMessageReq messageReq = new VideoMessageReq();
		messageReq = (VideoMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	地理位置消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqLocation(Map<String, String> requestMap) throws Exception {
		
		LocationMessageReq messageReq = new LocationMessageReq();
		messageReq = (LocationMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	链接消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqLink(Map<String, String> requestMap) throws Exception {
		
		LinkMessageReq messageReq = new LinkMessageReq();
		messageReq = (LinkMessageReq) MapleUtil.convertMapUpper(messageReq.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(messageReq);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/*********************************接收	事件消息处理方法************************************************/
	
	/**
	 * 接收	关注事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqSubscribe(Map<String, String> requestMap) throws Exception {
		
		SubscribeEvent event = new SubscribeEvent();
		event = (SubscribeEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	取消关注事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqUnsubscribe(Map<String, String> requestMap) throws Exception {
		
		SubscribeEvent event = new SubscribeEvent();
		event = (SubscribeEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	用户已关注扫描带参数二维码事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqScan(Map<String, String> requestMap) throws Exception {
		
		QRCodeEvent event = new QRCodeEvent();
		event = (QRCodeEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent("您已经关注了本平台，请勿重复扫码！");
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	自定义菜单跳转链接事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqLocationEvent(Map<String, String> requestMap) throws Exception {
		
		LocationEvent event = new LocationEvent();
		event = (LocationEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	关注事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqViewEvent(Map<String, String> requestMap) throws Exception {
		
		MenuEvent event = new MenuEvent();
		event = (MenuEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	自定义菜单点击事件处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqClickEvent(Map<String, String> requestMap) throws Exception {
		
		MenuEvent event = new MenuEvent();
		event = (MenuEvent) MapleUtil.convertMapUpper(event.getClass(), requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(RESP_MESSAGE_TEXT_CONTENT_DEFAULT);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	/**
	 * 接收	自定义菜单点击事件处理方法 -- 获取个人二维码
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String getMyQRcode(MenuEvent event) throws Exception {
		
		//生成邀请码
		String inviteCode = "";
		
		
		
		// 回复消息
		ImageMessageResp messageResp = new ImageMessageResp(event);
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	
	
}
