package com.jx.wechat.util;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.jx.wechat.entity.messageReq.ImageMessageReq;
import com.jx.wechat.entity.messageReq.TextMessageReq;
import com.jx.wechat.entity.messageReq.VoiceMessageReq;
import com.jx.wechat.entity.messageResp.TextMessageResp;

public class WechatRespUtil {
	
	/**
	 * 接收	文本消息处理方法
	 * @param requestMap
	 * @return
	 * @throws Exception
	 */
	public static String reqText(Map<String, String> requestMap) throws Exception {
		
		TextMessageReq messageReq = new TextMessageReq();
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
        messageResp.setToUserName(messageReq.getFromUserName());
        messageResp.setFromUserName(messageReq.getToUserName());
        messageResp.setCreateTime(new Date().getTime());
        messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        messageResp.setContent("客服正在赶来的路上，请稍候！");
        
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
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
		BeanUtils.populate(messageReq, requestMap);
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp();
		messageResp.setToUserName(messageReq.getFromUserName());
		messageResp.setFromUserName(messageReq.getToUserName());
		messageResp.setCreateTime(new Date().getTime());
		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		messageResp.setContent("客服正在赶来的路上，请稍候！");
		
		return MessageUtil.messageToXml(messageResp);
	}
	
	
	
	
}
