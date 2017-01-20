package com.jx.common.entity;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComWxAccount extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 微信账户 主键id */
	@NotBlank(message="微信账户 主键id 不能为空", groups={ValidationEdit.class})
	private String wxAccountId;
	
	/** 微信账户代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="微信账户代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String wxAccountCode;
		
	/** 微信账户名称 */
	@NotBlank(message="微信账户名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wxAccountName;
		
	/** 微信账户类型 */
	@NotBlank(message="微信账户类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wxAccountType;
		
	/** 微信账户状态 */
	private String wxAccountStatus;
		
	/** AppID(应用ID) */
	@NotBlank(message="AppID(应用ID) 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appId;
		
	/** AppSecret(应用密钥) */
	@NotBlank(message="AppSecret(应用密钥) 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String appSecret;
		
	/** Token(令牌) */
	@NotBlank(message="Token(令牌) 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String token;
		
	/** 微信支付商户号 */
	@NotBlank(message="微信支付商户号 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String mchId;
		
	/** API密钥 */
	@NotBlank(message="API密钥 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String apiKey;
		
	/** 公众号的全局唯一票据 */
	private String accessToken;
		
	/** 微信JS接口的临时票据 */
	private String jsApiTicket;
		
	
	
	/**
	 * 设置微信账户 主键id
	 * 
	 * @param String wxAccountId
	 */
	public void setWxAccountId(String wxAccountId) {
		this.wxAccountId = MapleStringUtil.trim(wxAccountId);
	}
	
	/**
	 * 获取微信账户 主键id
	 * 
	 * @return String wxAccountId
	 */
	public String getWxAccountId() {
		return this.wxAccountId;
	}
	
	/**
	 * 设置 微信账户代号
	 * 
	 * @param String wxAccountCode
	 */
	public void setWxAccountCode(String wxAccountCode) {
		this.wxAccountCode = MapleStringUtil.trim(wxAccountCode);
	}
	
	/**
	 * 获取 微信账户代号
	 * 
	 * @return String wxAccountCode
	 */
	public String getWxAccountCode() {
		return this.wxAccountCode;
	}
	
	/**
	 * 设置 微信账户名称
	 * 
	 * @param String wxAccountName
	 */
	public void setWxAccountName(String wxAccountName) {
		this.wxAccountName = MapleStringUtil.trim(wxAccountName);
	}
	
	/**
	 * 获取 微信账户名称
	 * 
	 * @return String wxAccountName
	 */
	public String getWxAccountName() {
		return this.wxAccountName;
	}
	
	/**
	 * 设置 微信账户类型
	 * 
	 * @param String wxAccountType
	 */
	public void setWxAccountType(String wxAccountType) {
		this.wxAccountType = MapleStringUtil.trim(wxAccountType);
	}
	
	/**
	 * 获取 微信账户类型
	 * 
	 * @return String wxAccountType
	 */
	public String getWxAccountType() {
		return this.wxAccountType;
	}
	
	/**
	 * 设置 微信账户状态
	 * 
	 * @param String wxAccountStatus
	 */
	public void setWxAccountStatus(String wxAccountStatus) {
		this.wxAccountStatus = MapleStringUtil.trim(wxAccountStatus);
	}
	
	/**
	 * 获取 微信账户状态
	 * 
	 * @return String wxAccountStatus
	 */
	public String getWxAccountStatus() {
		return this.wxAccountStatus;
	}
	
	/**
	 * 设置 AppID(应用ID)
	 * 
	 * @param String appId
	 */
	public void setAppId(String appId) {
		this.appId = MapleStringUtil.trim(appId);
	}
	
	/**
	 * 获取 AppID(应用ID)
	 * 
	 * @return String appId
	 */
	public String getAppId() {
		return this.appId;
	}
	
	/**
	 * 设置 AppSecret(应用密钥)
	 * 
	 * @param String appSecret
	 */
	public void setAppSecret(String appSecret) {
		this.appSecret = MapleStringUtil.trim(appSecret);
	}
	
	/**
	 * 获取 AppSecret(应用密钥)
	 * 
	 * @return String appSecret
	 */
	public String getAppSecret() {
		return this.appSecret;
	}
	
	/**
	 * 设置 Token(令牌)
	 * 
	 * @param String token
	 */
	public void setToken(String token) {
		this.token = MapleStringUtil.trim(token);
	}
	
	/**
	 * 获取 Token(令牌)
	 * 
	 * @return String token
	 */
	public String getToken() {
		return this.token;
	}
	
	/**
	 * 设置 微信支付商户号
	 * 
	 * @param String mchId
	 */
	public void setMchId(String mchId) {
		this.mchId = MapleStringUtil.trim(mchId);
	}
	
	/**
	 * 获取 微信支付商户号
	 * 
	 * @return String mchId
	 */
	public String getMchId() {
		return this.mchId;
	}
	
	/**
	 * 设置 API密钥
	 * 
	 * @param String apiKey
	 */
	public void setApiKey(String apiKey) {
		this.apiKey = MapleStringUtil.trim(apiKey);
	}
	
	/**
	 * 获取 API密钥
	 * 
	 * @return String apiKey
	 */
	public String getApiKey() {
		return this.apiKey;
	}
	
	/**
	 * 设置 公众号的全局唯一票据
	 * 
	 * @param String accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = MapleStringUtil.trim(accessToken);
	}
	
	/**
	 * 获取 公众号的全局唯一票据
	 * 
	 * @return String accessToken
	 */
	public String getAccessToken() {
		return this.accessToken;
	}
	
	/**
	 * 设置 微信JS接口的临时票据
	 * 
	 * @param String jsApiTicket
	 */
	public void setJsApiTicket(String jsApiTicket) {
		this.jsApiTicket = MapleStringUtil.trim(jsApiTicket);
	}
	
	/**
	 * 获取 微信JS接口的临时票据
	 * 
	 * @return String jsApiTicket
	 */
	public String getJsApiTicket() {
		return this.jsApiTicket;
	}
	
	
	/**************************table prop  end  *********************************/
}