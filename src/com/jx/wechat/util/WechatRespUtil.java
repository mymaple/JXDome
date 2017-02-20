package com.jx.wechat.util;

import java.awt.Color;
import java.awt.Font;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jx.common.config.Const;
import com.jx.common.entity.ComAppUser;
import com.jx.common.entity.ComInvite;
import com.jx.common.service.ComAppUserService;
import com.jx.common.service.ComInviteService;
import com.jx.common.util.DrawImageUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.SpringContextUtil;
import com.jx.common.util.WxConnUtil;
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
import com.jx.wechat.entity.messageResp.Image;
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
		String openId = event.getFromUserName();
		String jsonCode = event.getEventKey().replaceFirst("qrscene_", "");
		String content = "";
/*		ComAppUserService comAppUserService = 
				(ComAppUserService)SpringContextUtil.getBean("comAppUserService");
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setOpenId(openId);
		List<ComAppUser> comAppUserList = comAppUserService.otherHave(comAppUser);
		if(!comAppUserList.isEmpty() && comAppUserList.size()>0){
			comAppUser = comAppUserList.get(0);
			comAppUser.setAppUserStatus("01");
			comAppUser.setModifyUserId(openId);
			comAppUserService.edit(comAppUser);
		}else{
			Date nowtime = new Date();
			
			//获取个人用户信息
			String json = WxConnUtil.getUserInfo(openId);
			UserInfo userInfo = new UserInfo();
			userInfo = (UserInfo)MapleUtil.convertJson(userInfo.getClass(), JSONObject.fromObject(json));
			String fileSrc = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/"+ openId+"_headimg.jpg";
			if(StringUtils.isNotEmpty(userInfo.getHeadimgurl())){
				//下载头像图片
				HttpManager.download(userInfo.getHeadimgurl(), null, fileSrc);
			}else{
				String headimgDefault = PathUtil.getProjectPath() + Const.PATH_MYHEADIMG + "/default_headimg.jpg";
				MapleFileUtil.copyFile(headimgDefault , fileSrc);
			}
			
			//生成个人
			String appUserNum = "";
			
			comAppUser.setOpenId(openId);
			comAppUser.setAppUserCode(StringUtils.isEmpty(userInfo.getNickname())?appUserNum:userInfo.getNickname());
			comAppUser.setSex("1".equals(userInfo.getSex())?"01":"02");
			comAppUser.setBrithday(nowtime);
			comAppUser.setWxQRcodeExpiry(nowtime);
			comAppUser.setMediaExpiry(nowtime);
			comAppUser.setParentId(jsonCode);
			comAppUser.setHeadImgUrl(Const.PATH_MYHEADIMG+"/"+openId+"_headimg.jpg");
			comAppUser.setOrderNum(""+nowtime.getTime());
			comAppUser.setAppUserNum(appUserNum);
			
			//微信关注
			comAppUser.setAppUserStatus("01");
			//自主微信关注用户
			comAppUser.setAppUserType("01");
			comAppUser.setCreateUserId(openId);
			comAppUser.setModifyUserId(openId);
			
			
			comAppUserService.add(comAppUser);
		}*/
		
		if(StringUtils.isNotEmpty(jsonCode)){
			ComAppUserService comAppUserService = 
					(ComAppUserService)SpringContextUtil.getBean("comAppUserService");
			ComAppUser comAppUser = comAppUserService.findById(jsonCode);
			if(comAppUser != null){
				ComInviteService comInviteService = 
						(ComInviteService)SpringContextUtil.getBean("comInviteService");
				ComInvite comInvite = new ComInvite();
				comInvite.setInvitedUserId(openId);
				comInvite.setEffective("01");
				List<ComInvite> comInviteList =	comInviteService.otherHave(comInvite);
				if(comInviteList == null || comInviteList.size() == 0){
					comInvite = new ComInvite();
//					comInvite.setInviteCode(inviteCode);
//					comInvite.setInviteName(inviteName);
					comInvite.setInviteStatus("01");
					comInvite.setEffective("01");
					comInvite.setInviteType("wx");
					comInvite.setInviteUserId(jsonCode);
					comInvite.setInvitedUserId(openId);
					comInvite.setOrderNum(""+new Date().getTime());
					comInvite.setCreateUserId(openId);
					comInvite.setModifyUserId(openId);
					
					comInviteService.add(comInvite);
				}else{
					comInvite = comInviteList.get(0);
					comAppUser = comAppUserService.findById(comInvite.getInviteUserId());
				}
				content = "hey,您已被您的小伙伴“"+comAppUser.getAppUserCode()+"”邀请,点击注册，大礼包等你来拿哦！";
			}else{
				content = "hey,无法识别二维码哦";
			}
		}else{
			content = "hey,欢迎来到酷礼！";
		}
		
		
		// 回复消息
		TextMessageResp messageResp = new TextMessageResp(event);
		messageResp.setContent(content);
		
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
		String openId = event.getFromUserName();
		
		ComAppUserService comAppUserService = 
				(ComAppUserService)SpringContextUtil.getBean("comAppUserService");
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setOpenId(event.getFromUserName());
		List<ComAppUser> comAppUserList = comAppUserService.otherHave(comAppUser);
		if(!comAppUserList.isEmpty() && comAppUserList.size()>0){
			comAppUser = comAppUserList.get(0);
			//微信取关
			comAppUser.setAppUserStatus("00");
			comAppUser.setModifyUserId(openId);
			comAppUserService.edit(comAppUser);
		}
		// 回复消息
		
		return "";
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
		messageResp.setContent("您已经关注了本平台，请勿扫码！");
		
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
		
		 String eventKey = event.getEventKey();
		 if("ewm".equals(eventKey)){
			 return getMyQRcode(event);
		 }
		
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
		
		String openId = event.getFromUserName();
		
		ComAppUserService comAppUserService = 
				(ComAppUserService)SpringContextUtil.getBean("comAppUserService");
		ComAppUser comAppUser = new ComAppUser();
		comAppUser.setOpenId(openId);
		List<ComAppUser> comAppUserList = comAppUserService.otherHave(comAppUser);
		if(!comAppUserList.isEmpty() && comAppUserList.size()>0){
			comAppUser = comAppUserList.get(0);
			
			Date nowTime = new Date();
			if(nowTime.after(comAppUser.getMediaExpiry())){
				if(nowTime.after(comAppUser.getWxQRcodeExpiry())){
					String ticket = WxConnUtil.getQRCodeTicket(openId, 259200);
					String filePath = PathUtil.getProjectPath() + Const.PATH_MYWXQRCODE;
					String qrcodeSrc = filePath+"/"+openId+"_qrcode.jpg";
					String headimgSr = PathUtil.getProjectPath()+Const.PATH_MYHEADIMG+"/"+openId+"_headimg.jpg";
					WxConnUtil.toSaveQRCode(ticket, qrcodeSrc);
					String pressText = "";
					DrawImageUtil.pressImage(qrcodeSrc, filePath+"/cooler.jpg", qrcodeSrc, 233, 1520, 211, 211);
					DrawImageUtil.pressImage(headimgSr, qrcodeSrc, qrcodeSrc, 561, 1946, 160, 160);
					DrawImageUtil.pressText(pressText, qrcodeSrc, Color.black, "黑体", Font.BOLD, 23, 350, 1000);
					
					comAppUser.setWxQRcodeUrl(Const.PATH_MYWXQRCODE+"/"+openId+"_qrcode.jpg");
					//在有效期内更换
					comAppUser.setWxQRcodeExpiry(MapleDateUtil.getNextDays(nowTime, 29));
				}
				String mediaId= WxConnUtil.getMediaId(PathUtil.getProjectPath()+comAppUser.getWxQRcodeUrl(), "image");
				if(StringUtils.isNotEmpty(mediaId)){
					comAppUser.setMediaId(mediaId);
					comAppUser.setMediaExpiry(MapleDateUtil.getNextDays(nowTime, 2));
					comAppUserService.edit(comAppUser);
				}else{
					
				}
			}
		}else{
			
		}
		
		// 回复消息
		ImageMessageResp messageResp = new ImageMessageResp(event);
		messageResp.setImage(new Image(comAppUser.getMediaId()));
		return MessageUtil.messageToXml(messageResp);
	}
	
}
