package com.jx.background.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class BgWxMenuBtn extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<BgWxMenuBtn> subBgWxMenuBtnList;
	
	/** 子列表 路径*/
	private String subBgWxMenuBtnPath;
	
	/** 是否有此微信菜单按钮 */
	private boolean hasWxMenuBtn;

	
	/**
	 * 获取 指标
	 * 
	 * @return String target
	 */
	public String getTarget() {
		return target;
	}
	
	/**
	 * 设置 指标
	 * 
	 * @param String target
	 */
	public void setTarget(String target) {
		this.target = MapleStringUtil.trim(target);
	}
	
	/**
	 * 获取 子列表
	 * 
	 * @return List<BgWxMenuBtn> subBgWxMenuBtnList
	 */
	public List<BgWxMenuBtn> getSubBgWxMenuBtnList() {
		return this.subBgWxMenuBtnList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<BgWxMenuBtn> subBgWxMenuBtnList
	 */
	public void setSubBgWxMenuBtnList(List<BgWxMenuBtn> subBgWxMenuBtnList) {
		this.subBgWxMenuBtnList = subBgWxMenuBtnList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subBgWxMenuBtnPath
	 */
	public String getSubBgWxMenuBtnPath() {
		return this.subBgWxMenuBtnPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subBgWxMenuBtnPath
	 */
	public void setSubBgWxMenuBtnPath(String subBgWxMenuBtnPath) {
		this.subBgWxMenuBtnPath = MapleStringUtil.trim(subBgWxMenuBtnPath);
	}
	
	/**
	 * 获取 是否有此微信菜单按钮 
	 * 
	 * @return boolean hasWxMenuBtn
	 */
	public boolean isHasWxMenuBtn() {
		return this.hasWxMenuBtn;
	}
	
	/**
	 * 设置 是否有此微信菜单按钮
	 * 
	 * @param boolean hasWxMenuBtn
	 */
	public void setHasWxMenuBtn(boolean hasWxMenuBtn) {
		this.hasWxMenuBtn = hasWxMenuBtn;
	}
	
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 微信菜单按钮 主键id */
	@NotBlank(message="微信菜单按钮 主键id 不能为空", groups={ValidationEdit.class})
	private String wxMenuBtnId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 微信菜单按钮代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="微信菜单按钮代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String wxMenuBtnCode;
		
	/** 微信菜单按钮名称 */
	@NotBlank(message="微信菜单按钮名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wxMenuBtnName;
		
	/** 微信菜单按钮类型 */
	@NotBlank(message="微信菜单按钮类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String wxMenuBtnType;
		
	/** 微信菜单按钮状态 */
	private String wxMenuBtnStatus;
		
	/** 菜单KEY值 */
	//@NotBlank(message="菜单KEY值 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String menuBtnKey;
		
	/** 网页链接 */
	//@NotBlank(message="网页链接 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String menuBtnUrl;
		
	/** 永久素材id */
	//@NotBlank(message="永久素材id 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String media_id;
		
	
	
	/**
	 * 设置微信菜单按钮 主键id
	 * 
	 * @param String wxMenuBtnId
	 */
	public void setWxMenuBtnId(String wxMenuBtnId) {
		this.wxMenuBtnId = MapleStringUtil.trim(wxMenuBtnId);
	}
	
	/**
	 * 获取微信菜单按钮 主键id
	 * 
	 * @return String wxMenuBtnId
	 */
	public String getWxMenuBtnId() {
		return this.wxMenuBtnId;
	}
	
	/**
	 * 设置 上级id
	 * 
	 * @param String parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = MapleStringUtil.trim(parentId);
	}
	
	/**
	 * 获取 上级id
	 * 
	 * @return String parentId
	 */
	public String getParentId() {
		return this.parentId;
	}
	
	/**
	 * 设置 微信菜单按钮代号
	 * 
	 * @param String wxMenuBtnCode
	 */
	public void setWxMenuBtnCode(String wxMenuBtnCode) {
		this.wxMenuBtnCode = MapleStringUtil.trim(wxMenuBtnCode);
	}
	
	/**
	 * 获取 微信菜单按钮代号
	 * 
	 * @return String wxMenuBtnCode
	 */
	public String getWxMenuBtnCode() {
		return this.wxMenuBtnCode;
	}
	
	/**
	 * 设置 微信菜单按钮名称
	 * 
	 * @param String wxMenuBtnName
	 */
	public void setWxMenuBtnName(String wxMenuBtnName) {
		this.wxMenuBtnName = MapleStringUtil.trim(wxMenuBtnName);
	}
	
	/**
	 * 获取 微信菜单按钮名称
	 * 
	 * @return String wxMenuBtnName
	 */
	public String getWxMenuBtnName() {
		return this.wxMenuBtnName;
	}
	
	/**
	 * 设置 微信菜单按钮类型
	 * 
	 * @param String wxMenuBtnType
	 */
	public void setWxMenuBtnType(String wxMenuBtnType) {
		this.wxMenuBtnType = MapleStringUtil.trim(wxMenuBtnType);
	}
	
	/**
	 * 获取 微信菜单按钮类型
	 * 
	 * @return String wxMenuBtnType
	 */
	public String getWxMenuBtnType() {
		return this.wxMenuBtnType;
	}
	
	/**
	 * 设置 微信菜单按钮状态
	 * 
	 * @param String wxMenuBtnStatus
	 */
	public void setWxMenuBtnStatus(String wxMenuBtnStatus) {
		this.wxMenuBtnStatus = MapleStringUtil.trim(wxMenuBtnStatus);
	}
	
	/**
	 * 获取 微信菜单按钮状态
	 * 
	 * @return String wxMenuBtnStatus
	 */
	public String getWxMenuBtnStatus() {
		return this.wxMenuBtnStatus;
	}
	
	/**
	 * 设置 菜单KEY值
	 * 
	 * @param String menuBtnKey
	 */
	public void setMenuBtnKey(String menuBtnKey) {
		this.menuBtnKey = MapleStringUtil.trim(menuBtnKey);
	}
	
	/**
	 * 获取 菜单KEY值
	 * 
	 * @return String menuBtnKey
	 */
	public String getMenuBtnKey() {
		return this.menuBtnKey;
	}
	
	/**
	 * 设置 网页链接
	 * 
	 * @param String menuBtnUrl
	 */
	public void setMenuBtnUrl(String menuBtnUrl) {
		this.menuBtnUrl = MapleStringUtil.trim(menuBtnUrl);
	}
	
	/**
	 * 获取 网页链接
	 * 
	 * @return String menuBtnUrl
	 */
	public String getMenuBtnUrl() {
		return this.menuBtnUrl;
	}
	
	/**
	 * 设置 永久素材id
	 * 
	 * @param String media_id
	 */
	public void setMedia_id(String media_id) {
		this.media_id = MapleStringUtil.trim(media_id);
	}
	
	/**
	 * 获取 永久素材id
	 * 
	 * @return String media_id
	 */
	public String getMedia_id() {
		return this.media_id;
	}
	
	
	/**************************table prop  end  *********************************/
}