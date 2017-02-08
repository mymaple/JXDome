package com.jx.wechat.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jx.wechat.entity.messageResp.ImageMessageResp;
import com.jx.wechat.entity.messageResp.MusicMessageResp;
import com.jx.wechat.entity.messageResp.News;
import com.jx.wechat.entity.messageResp.NewsMessageResp;
import com.jx.wechat.entity.messageResp.TextMessageResp;
import com.jx.wechat.entity.messageResp.VideoMessageResp;
import com.jx.wechat.entity.messageResp.VoiceMessageResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 微信消息工具类
 * @author pc
 *
 */
public class MessageUtil {
	
	/** 
     * 事件类型：subscribe(订阅) 
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型：unsubscribe(取消订阅) 
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
  
    /** 
     * 事件类型：CLICK(点击菜单拉取消息时的事件) 
     */  
    public static final String EVENT_TYPE_CLICK = "CLICK";  
    
    /**
     * 事件类型：VIEW(点击菜单跳转链接时的事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";
    
    /**
     * 事件类别：SCAN(用户已关注事件)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";
    
    /**
     * 事件类型：LOCATION(上报地理位置)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";
    
    
    
    /** 
     * 请求消息类型：文本 
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型：图片 
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
    
    /** 
     * 请求消息类型：音频 
     */  
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    
    /** 
     * 请求消息类型：视频
     */  
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    
    /** 
     * 请求消息类型：小视频
     */  
    public static final String REQ_MESSAGE_TYPE_SHORTVIDEO = "shortvideo";
  
    /** 
     * 请求消息类型：链接 
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  
  
    /** 
     * 请求消息类型：地理位置 
     */  
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
  
    /** 
     * 请求消息类型：事件推送
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    
    
	
	/** 
     * 响应消息类型：文本 
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
    
    /**
     * 响应消息类型：图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    /**
     * 响应消息类型：语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 响应消息类型：视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
  
    /** 
     * 响应消息类型：音乐 
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
  
    /** 
     * 响应消息类型：图文 
     */  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
  
    
    
  
    /** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {  
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();  
        Document document = reader.read(inputStream);  
        // 得到xml根元素  
        Element root = document.getRootElement();  
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();  
  
        // 遍历所有子节点  
        for (Element e : elementList)  
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    }  
    
    /** 
     * 扩展xstream，使其支持CDATA块 
     *  
     */  
    private static XStream xstream = new XStream(new XppDriver() {  
        public HierarchicalStreamWriter createWriter(Writer out) {  
            return new PrettyPrintWriter(out) {  
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);  
                }  
  
                protected void writeText(QuickWriter writer, String text) {  
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });  
    
  
    /**
     * 文本消息对象转换成xml
     * 
     * @param textMessageResp 文本消息对象
     * @return xml
     */
    public static String messageToXml(TextMessageResp textMessageResp) {
        xstream.alias("xml", textMessageResp.getClass());
        return xstream.toXML(textMessageResp);
    }

    /**
     * 图片消息对象转换成xml
     * 
     * @param imageMessageResp 图片消息对象
     * @return xml
     */
    public static String messageToXml(ImageMessageResp imageMessageResp) {
        xstream.alias("xml", imageMessageResp.getClass());
        return xstream.toXML(imageMessageResp);
    }

    /**
     * 语音消息对象转换成xml
     * 
     * @param voiceMessageResp 语音消息对象
     * @return xml
     */
    public static String messageToXml(VoiceMessageResp voiceMessageResp) {
        xstream.alias("xml", voiceMessageResp.getClass());
        return xstream.toXML(voiceMessageResp);
    }

    /**
     * 视频消息对象转换成xml
     * 
     * @param videoMessageResp 视频消息对象
     * @return xml
     */
    public static String messageToXml(VideoMessageResp videoMessageResp) {
        xstream.alias("xml", videoMessageResp.getClass());
        return xstream.toXML(videoMessageResp);
    }

    /**
     * 音乐消息对象转换成xml
     * 
     * @param musicMessageResp 音乐消息对象
     * @return xml
     */
    public static String messageToXml(MusicMessageResp musicMessageResp) {
        xstream.alias("xml", musicMessageResp.getClass());
        return xstream.toXML(musicMessageResp);
    }

    /**
     * 图文消息对象转换成xml
     * 
     * @param newsMessageResp 图文消息对象
     * @return xml
     */
    public static String messageToXml(NewsMessageResp newsMessageResp) {
        xstream.alias("xml", newsMessageResp.getClass());
        xstream.alias("item", new News().getClass());
        return xstream.toXML(newsMessageResp);
    } 
  


}
