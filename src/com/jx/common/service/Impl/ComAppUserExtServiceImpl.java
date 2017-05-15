package com.jx.common.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.jx.common.config.DaoSupport;
import com.jx.common.config.exception.UncheckedException;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.PathUtil;
import com.jx.common.util.UuidUtil;
import com.jx.common.util.WxConnUtil;
import com.jx.common.entity.ComAppUserExt;
import com.jx.common.service.ComAppUserExtService;
import com.jx.common.service.ComAppUserService;

@Service("comAppUserExtService")
public class ComAppUserExtServiceImpl implements ComAppUserExtService{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "comAppUserService")
	private ComAppUserService comAppUserService;
	
	/****************************custom * start***********************************/
	/**
	 * 初始化扩展属性
	 * @param String appUserId
	 * @throws Exception
	 */
	public void toInit(String appUserId, String integralCount, String mediaExpiry
			, String mediaId, String wxQRcodeExpiry, String wxQRcodeSrc, String openId) throws Exception {
		
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setCreateUserId(appUserId);
		comAppUserExt.setModifyUserId(appUserId);
		
		comAppUserExt.setExtValue(integralCount);
		comAppUserExt.setExtCode(ComAppUserExt.INTEGRALCOUNT);
		comAppUserExt.setExtName("积分数");
		this.add(comAppUserExt);

		comAppUserExt.setExtValue(mediaExpiry);
		comAppUserExt.setExtCode(ComAppUserExt.MEDIAEXPIRY);
		comAppUserExt.setExtName("媒体文件有效时间");
		this.add(comAppUserExt);
		
		comAppUserExt.setExtValue(mediaId);
		comAppUserExt.setExtCode(ComAppUserExt.MEDIAID);
		comAppUserExt.setExtName("媒体文件id");
		this.add(comAppUserExt);
		
		comAppUserExt.setExtValue(wxQRcodeExpiry);
		comAppUserExt.setExtCode(ComAppUserExt.WXQRCODEEXPIRY);
		comAppUserExt.setExtName("微信二维码有效期");
		this.add(comAppUserExt);
		
		comAppUserExt.setExtValue(wxQRcodeSrc);
		comAppUserExt.setExtCode(ComAppUserExt.WXQRCODESRC);
		comAppUserExt.setExtName("微信二维码");
		this.add(comAppUserExt);
		
		comAppUserExt.setExtValue(openId);
		comAppUserExt.setExtCode(ComAppUserExt.OPENID);
		comAppUserExt.setExtName("微信公众号个人唯一标识");
		this.add(comAppUserExt);
		
	}
	
	/**
	 * 获得 积分数量
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetIntegralCount(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.INTEGRALCOUNT);
		return comAppUserExt.getExtValue();
	}
	
	/**
	 * 增加、减少 积分数量
	 * @param String appUserId, String addValue
	 * @throws Exception
	 */
	public void addIntegralCount(String appUserId, String addValue) throws Exception {
		this.changeValue(appUserId, ComAppUserExt.INTEGRALCOUNT, addValue);
	}
	
	/**
	 * 获得 微信二维码地址
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetWxQRcodeSrc(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.WXQRCODESRC);
		return comAppUserExt.getExtValue();
	}
	
	/**
	 * 修改 微信二维码地址
	 * @param String appUserId, String wxQRcodeSrc
	 * @throws Exception
	 */
	public void changeWxQRcodeSrc(String appUserId, String wxQRcodeSrc) throws Exception {
		this.changeValue(appUserId, ComAppUserExt.WXQRCODESRC, wxQRcodeSrc);
	}
	
	/**
	 * 获得 微信二维码有效期
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetWxQRcodeExpiry(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.WXQRCODEEXPIRY);
		return comAppUserExt.getExtValue();
	}
	
	/**
	 * 修改 微信二维码信息
	 * @param String appUserId
	 * @throws Exception
	 */
	public void changeWxQRcodeExpiry(String appUserId, String wxQRcodeSrc) throws Exception {
		this.changeValue(appUserId, ComAppUserExt.WXQRCODEEXPIRY, wxQRcodeSrc);
	}
	
	/**
	 * 获得 媒体文件id
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetMediaId(String appUserId) throws Exception {
		
		Date nowTime = new Date();
		String mediaId = "";
		if(nowTime.after(MapleDateUtil.parseDateStr(this.toGetMediaExpiry(appUserId)))){
			if(nowTime.after(MapleDateUtil.parseDateStr(this.toGetWxQRcodeExpiry(appUserId)))){
				String appUserCode = comAppUserService.findById(appUserId).getAppUserCode();
				//以用户代号为二维码参数
				String ticket = WxConnUtil.getQRCodeTicket(appUserCode, 259200);
				String filePath = PathUtil.getProjectPath() + ComAppUserExt.PATH_IMG_AU_WXQRCODE;
				String qrcodeSrc = filePath+"/"+appUserId+"_qrcode.jpg";
				WxConnUtil.toSaveQRCode(ticket, qrcodeSrc);
//				String headimgSr = PathUtil.getProjectPath()+Const.PATH_MYHEADIMG+"/"+appUserId+"_headimg.jpg";
//				String pressText = "";
//				DrawImageUtil.pressImage(qrcodeSrc, filePath+"/cooler.jpg", qrcodeSrc, 233, 1520, 211, 211);
//				DrawImageUtil.pressImage(headimgSr, qrcodeSrc, qrcodeSrc, 561, 1946, 160, 160);
//				DrawImageUtil.pressText(pressText, qrcodeSrc, Color.black, "黑体", Font.BOLD, 23, 350, 1000);
				
				this.changeWxQRcodeSrc(appUserId, ComAppUserExt.PATH_IMG_AU_WXQRCODE+"/"+appUserId+"_qrcode.jpg");
				//在有效期内更换
				this.changeWxQRcodeExpiry(appUserId, MapleDateUtil.formatDate(MapleDateUtil.getNextDays(nowTime, 29)));
			}
			mediaId = WxConnUtil.getMediaId(PathUtil.getProjectPath()+this.toGetWxQRcodeSrc(appUserId), "image");
			
			this.changeMediaId(appUserId, mediaId);
			this.changeMediaExpiry(appUserId, MapleDateUtil.formatDate(MapleDateUtil.getNextDays(nowTime, 2)));
		}else{
			ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.MEDIAID);
			mediaId =  comAppUserExt.getExtValue();
		}
		
		return mediaId;
		
	}
	
	/**
	 * 修改 媒体文件id
	 * @param String appUserId, String mediaId
	 * @throws Exception
	 */
	public void changeMediaId(String appUserId, String mediaId) throws Exception {
		this.changeValue(appUserId, ComAppUserExt.MEDIAID, mediaId);
	}
	
	/**
	 * 获得 媒体文件有效时间
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetMediaExpiry(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.MEDIAEXPIRY);
		return comAppUserExt.getExtValue();
	}
	
	/**
	 * 修改 媒体文件有效时间
	 * @param String appUserId, String mediaExpiry
	 * @throws Exception
	 */
	public void changeMediaExpiry(String appUserId, String mediaExpiry) throws Exception {
		this.changeValue(appUserId, ComAppUserExt.MEDIAEXPIRY, mediaExpiry);
	}
	
	/**
	 * 获取 平台用户Id
	 * @param String openId
	 * @throws Exception
	 */
	public String toGetAppUserId(String openId) throws Exception {
		List<ComAppUserExt> comAppUserExtList = this.listByVC(openId, ComAppUserExt.OPENID);
		String appUserId = "";
		if(comAppUserExtList!=null && comAppUserExtList.size()>0){
			for (int i = 0; i < comAppUserExtList.size(); i++) {
				if(i==0){
					appUserId = comAppUserExtList.get(0).getAppUserId();
				}else{
					this.changeOpenId(comAppUserExtList.get(i).getAppUserId(), "");
				}
			}
		}
		return appUserId;
	}
	
	/**
	 * 微信公众号个人唯一标识
	 * @param String appUserId
	 * @throws Exception
	 */
	public String toGetOpenId(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = this.find(appUserId, ComAppUserExt.OPENID);
		return comAppUserExt.getExtValue();
	}
	
	/**
	 * 修改 微信公众号个人唯一标识
	 * @param String appUserId, String openId
	 * @throws Exception
	 */
	public void changeOpenId(String appUserId, String openId) throws Exception {
		String appUserId1 = this.toGetAppUserId(openId);
		if(StringUtils.isNotEmpty(appUserId1)){
			this.changeValue(appUserId1, ComAppUserExt.OPENID, "");
		}
		this.changeValue(appUserId, ComAppUserExt.OPENID, openId);
	}
	
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComAppUserExt comAppUserExt
	 * @throws Exception
	 */
	public void add(ComAppUserExt comAppUserExt) throws Exception {
		
		if(this.find(comAppUserExt.getAppUserId(), comAppUserExt.getExtCode())!=null){
			
		}
		Date nowTime = new Date();
		
		comAppUserExt.setAppUserExtId(UuidUtil.get32UUID());
		comAppUserExt.setOrderNum(""+nowTime.getTime());
		comAppUserExt.setEffective("01");
		
		comAppUserExt.setCreateTime(nowTime);
		comAppUserExt.setModifyTime(nowTime);
		
		dao.add("ComAppUserExtMapper.add", comAppUserExt);
	}
	
	/**
	 * 加减改值
	 * @param String appUserId, String extCode, String changeValue
	 * @throws Exception
	 */
	public void addValue(String appUserId, String extCode, String addValue) throws Exception {
		if(StringUtils.isEmpty(appUserId)
				|| StringUtils.isEmpty(extCode)
				|| addValue == null
				){
			throw new UncheckedException("", null, "参数不足");
		}
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		comAppUserExt.setExtValue(addValue);
		
		Date nowTime = new Date();
		comAppUserExt.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserExt.setModifyTime(nowTime);
		if(comAppUserExt.getLastModifyTime() == null){
			comAppUserExt.setLastModifyTime(this.find(appUserId, extCode).getModifyTime());
		}
		if(comAppUserExt.getModifyTime().compareTo(comAppUserExt.getLastModifyTime()) == 0){
			comAppUserExt.setModifyTime(MapleDateUtil.getNextSecond(comAppUserExt.getModifyTime()));
		}
		dao.update("ComAppUserExtMapper.addValue", comAppUserExt);
	}
	
	/**
	 * 更改
	 * @param String appUserId, String extCode, String changeValue
	 * @throws Exception
	 */
	public void changeValue(String appUserId, String extCode, String changeValue) throws Exception {
		if(StringUtils.isEmpty(appUserId)
				|| StringUtils.isEmpty(extCode)
				|| changeValue == null
				){
			throw new UncheckedException("", null, "参数不足");
		}
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		comAppUserExt.setExtValue(changeValue);
		
		Date nowTime = new Date();
		comAppUserExt.setModifyUserId(ShiroSessionUtil.getUserId());
		comAppUserExt.setModifyTime(nowTime);
		if(comAppUserExt.getLastModifyTime() == null){
			comAppUserExt.setLastModifyTime(this.find(appUserId, extCode).getModifyTime());
		}
		if(comAppUserExt.getModifyTime().compareTo(comAppUserExt.getLastModifyTime()) == 0){
			comAppUserExt.setModifyTime(MapleDateUtil.getNextSecond(comAppUserExt.getModifyTime()));
		}
		dao.update("ComAppUserExtMapper.changeValue", comAppUserExt);
	}

	/**
	 * 删除 
	 * @param String appUserId, String extCode
	 * @throws Exception
	 */
	public void delete(String appUserId, String extCode) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		dao.delete("ComAppUserExtMapper.delete", comAppUserExt);
	}
	
	/**
	 * 删除 
	 * @param String appUserId
	 * @throws Exception
	 */
	public void delete(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		dao.delete("ComAppUserExtMapper.delete", comAppUserExt);
	}
	
	/**
	 * 获取(类)数据
	 * @param String appUserId, String extCode
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	public ComAppUserExt find(String appUserId, String extCode) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		comAppUserExt.setExtCode(extCode);
		return (ComAppUserExt) dao.findForObject("ComAppUserExtMapper.find", comAppUserExt);
	}

	/**
	 * 获取(类)数据
	 * @param String extValue, String extCode
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUserExt> listByVC(String extValue, String extCode) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setExtValue(extValue);
		comAppUserExt.setExtCode(extCode);
		return (List<ComAppUserExt>) dao.findForList("ComAppUserExtMapper.listByVC", comAppUserExt);
	}
	
	/**
	 * 获取(类)数据
	 * @param String appUserId
	 * @return ComAppUserExt
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<ComAppUserExt> listByU(String appUserId) throws Exception {
		ComAppUserExt comAppUserExt = new ComAppUserExt();
		comAppUserExt.setAppUserId(appUserId);
		return (List<ComAppUserExt>) dao.findForList("ComAppUserExtMapper.listByU", comAppUserExt);
	}
	
	/****************************common * end***********************************/
}