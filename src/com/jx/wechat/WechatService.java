package com.jx.wechat;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.jx.wechat.entity.messageResp.TextMessageResp;
import com.jx.wechat.util.MessageUtil;
import com.jx.wechat.util.WechatRespUtil;

public class WechatService {
	
    /**
     * 处理微信发来的请求
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request) {
    	
        // xml格式的消息数据
        String respXml = "";
        try {
            // 调用parseXml方法解析请求消息
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号
            String fromUserName = requestMap.get("FromUserName");
            // 开发者微信号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");


            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
            	respXml = WechatRespUtil.reqText(requestMap);
            }
            // 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
            	respXml = WechatRespUtil.reqImage(requestMap);
            }
            // 语音消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
            	respXml = WechatRespUtil.reqVoice(requestMap);
            }
            // 视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) {
            	respXml = WechatRespUtil.reqVideo(requestMap);
            }
            // 短视频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORTVIDEO)) {
            	respXml = WechatRespUtil.reqShortVideo(requestMap);
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
            	respXml = WechatRespUtil.reqLocation(requestMap);
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
            	respXml = WechatRespUtil.reqLink(requestMap);
            }
            // 事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 关注
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                	respXml = WechatRespUtil.reqSubscribe(requestMap);
                }
                // 取消关注
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                	respXml = WechatRespUtil.reqUnsubscribe(requestMap);
                }
                // 用户已关注扫描带参数二维码
                else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
                	respXml = WechatRespUtil.reqScan(requestMap);
                }
                // 上报地理位置
                else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
                	respXml = WechatRespUtil.reqLocationEvent(requestMap);
                }
                // 自定义菜单跳转链接
                else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
                	respXml = WechatRespUtil.reqViewEvent(requestMap);
                }
                // 自定义菜单点击
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                	respXml = WechatRespUtil.reqClickEvent(requestMap);
                }
            }
            
            if(StringUtils.isEmpty(respXml)){
            	// 回复消息
        		TextMessageResp messageResp = new TextMessageResp();
        		messageResp.setToUserName(fromUserName);
        		messageResp.setFromUserName(toUserName);
        		messageResp.setCreateTime(String.valueOf(new Date().getTime()));
        		messageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        		messageResp.setContent("未知的消息类型！");
        		MessageUtil.messageToXml(messageResp);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return respXml;
    }
}
