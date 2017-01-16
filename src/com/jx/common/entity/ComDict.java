package com.jx.common.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.config.Const;
import com.jx.common.util.MapleStringUtil;

public class ComDict extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<ComDict> subComDictList;
	
	/** 子列表 路径*/
	private String subComDictPath;
	
	/** 是否有此数据字典 */
	private boolean hasDict;
	
	
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
	 * @return List<ComDict> subComDictList
	 */
	public List<ComDict> getSubComDictList() {
		return this.subComDictList;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<ComDict> subComDictList
	 */
	public void setSubComDictList(List<ComDict> subComDictList) {
		this.subComDictList = subComDictList;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String subComDictPath
	 */
	public String getSubComDictPath() {
		return this.subComDictPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String subComDictPath
	 */
	public void setSubComDictPath(String subComDictPath) {
		this.subComDictPath = MapleStringUtil.trim(subComDictPath);
	}
	
	/**
	 * 获取 是否有此数据字典 
	 * 
	 * @return boolean hasDict
	 */
	public boolean isHasDict() {
		return this.hasDict;
	}
	
	/**
	 * 设置 是否有此数据字典
	 * 
	 * @param boolean hasDict
	 */
	public void setHasDict(boolean hasDict) {
		this.hasDict = hasDict;
	}
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** 数据字典 主键id */
	@NotBlank(message="数据字典 主键id 不能为空", groups={ValidationEdit.class})
	private String dictId;
	
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	/** 数据字典代号 */
	@Pattern(regexp = Const.REG_COM_CODE_STR, message="数据字典代号 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
	private String dictCode;
		
	/** 数据字典名称 */
	@NotBlank(message="数据字典名称 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String dictName;
		
	/** 数据字典类型 */
	@NotBlank(message="数据字典类型 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String dictType;
		
	/** 数据字典状态 */
	private String dictStatus;
		
	/** 数据字典值 */
	@NotBlank(message="数据字典值 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
	private String dictValue;
		
	/** 级别 */
	private int level;
		
	
	
	/**
	 * 设置数据字典 主键id
	 * 
	 * @param String dictId
	 */
	public void setDictId(String dictId) {
		this.dictId = MapleStringUtil.trim(dictId);
	}
	
	/**
	 * 获取数据字典 主键id
	 * 
	 * @return String dictId
	 */
	public String getDictId() {
		return this.dictId;
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
	 * 设置 数据字典代号
	 * 
	 * @param String dictCode
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = MapleStringUtil.trim(dictCode);
	}
	
	/**
	 * 获取 数据字典代号
	 * 
	 * @return String dictCode
	 */
	public String getDictCode() {
		return this.dictCode;
	}
	
	/**
	 * 设置 数据字典名称
	 * 
	 * @param String dictName
	 */
	public void setDictName(String dictName) {
		this.dictName = MapleStringUtil.trim(dictName);
	}
	
	/**
	 * 获取 数据字典名称
	 * 
	 * @return String dictName
	 */
	public String getDictName() {
		return this.dictName;
	}
	
	/**
	 * 设置 数据字典类型
	 * 
	 * @param String dictType
	 */
	public void setDictType(String dictType) {
		this.dictType = MapleStringUtil.trim(dictType);
	}
	
	/**
	 * 获取 数据字典类型
	 * 
	 * @return String dictType
	 */
	public String getDictType() {
		return this.dictType;
	}
	
	/**
	 * 设置 数据字典状态
	 * 
	 * @param String dictStatus
	 */
	public void setDictStatus(String dictStatus) {
		this.dictStatus = MapleStringUtil.trim(dictStatus);
	}
	
	/**
	 * 获取 数据字典状态
	 * 
	 * @return String dictStatus
	 */
	public String getDictStatus() {
		return this.dictStatus;
	}
	
	/**
	 * 设置 数据字典值
	 * 
	 * @param String dictValue
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = MapleStringUtil.trim(dictValue);
	}
	
	/**
	 * 获取 数据字典值
	 * 
	 * @return String dictValue
	 */
	public String getDictValue() {
		return this.dictValue;
	}
	
	/**
	 * 设置 级别
	 * 
	 * @param int level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * 获取 级别
	 * 
	 * @return int level
	 */
	public int getLevel() {
		return this.level;
	}
	
	
	/**************************table prop  end  *********************************/
}