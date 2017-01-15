package com.jx.${bgMaple.entityPackage}.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.jx.common.config.BaseEntity;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class ${bgMaple.mapleEntityUpper} extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	<#if bgMaple.mapleType == "02">
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<${bgMaple.mapleEntityUpper}> sub${bgMaple.mapleEntityUpper}List;
	
	/** 子列表 路径*/
	private String sub${bgMaple.mapleEntityUpper}Path;
	
	/** 是否有此${bgMaple.mapleName} */
	private boolean has${bgMaple.mapleCodeUpper};
	
	/** 修改时间 */
	private Date lastModifyTime;
	
	
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
	 * @return List<${bgMaple.mapleEntityUpper}> sub${bgMaple.mapleEntityUpper}List
	 */
	public List<${bgMaple.mapleEntityUpper}> getSub${bgMaple.mapleEntityUpper}List() {
		return this.sub${bgMaple.mapleEntityUpper}List;
	}
	
	/**
	 * 设置 子列表
	 * 
	 * @param List<${bgMaple.mapleEntityUpper}> sub${bgMaple.mapleEntityUpper}List
	 */
	public void setSub${bgMaple.mapleEntityUpper}List(List<${bgMaple.mapleEntityUpper}> sub${bgMaple.mapleEntityUpper}List) {
		this.sub${bgMaple.mapleEntityUpper}List = sub${bgMaple.mapleEntityUpper}List;
	}
	
	/**
	 * 获取 子列表 路径
	 * 
	 * @return String sub${bgMaple.mapleEntityUpper}Path
	 */
	public String getSub${bgMaple.mapleEntityUpper}Path() {
		return this.sub${bgMaple.mapleEntityUpper}Path;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String sub${bgMaple.mapleEntityUpper}Path
	 */
	public void setSub${bgMaple.mapleEntityUpper}Path(String sub${bgMaple.mapleEntityUpper}Path) {
		this.sub${bgMaple.mapleEntityUpper}Path = MapleStringUtil.trim(sub${bgMaple.mapleEntityUpper}Path);
	}
	
	/**
	 * 获取 是否有此${bgMaple.mapleName} 
	 * 
	 * @return boolean has${bgMaple.mapleCodeUpper}
	 */
	public boolean isHas${bgMaple.mapleCodeUpper}() {
		return this.has${bgMaple.mapleCodeUpper};
	}
	
	/**
	 * 设置 是否有此${bgMaple.mapleName}
	 * 
	 * @param boolean has${bgMaple.mapleCodeUpper}
	 */
	public void setHas${bgMaple.mapleCodeUpper}(boolean has${bgMaple.mapleCodeUpper}) {
		this.has${bgMaple.mapleCodeUpper} = has${bgMaple.mapleCodeUpper};
	}
	
	/**
	 * 设置 修改时间
	 * 
	 * @param Date lastModifyTime
	 */
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	
	/**
	 * 获取 修改时间
	 * 
	 * @return Date lastModifyTime
	 */
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}	
		
	public void setLastModifyTimeStr(String lastModifyTimeStr) throws Exception{
		lastModifyTimeStr = MapleStringUtil.trim(lastModifyTimeStr);
		if(!lastModifyTimeStr.equals("")){
			try{
				setLastModifyTime(MapleDateUtil.parseDate(lastModifyTimeStr));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String getLastModifyTimeStr(){
		return MapleDateUtil.getFormatedDateString(getLastModifyTime());
	}
	</#if>
	
	/**************************custom prop end**********************************/
	
	/**************************table prop satrt*********************************/
	
	/** ${bgMaple.mapleName} 主键id */
	@NotBlank(message="${bgMaple.mapleName} 主键id 不能为空", groups={ValidationEdit.class})
	private String ${bgMaple.mapleCode}Id;
	
	<#if bgMaple.mapleType == "02">
	/** 上级 id */
	@NotBlank(message="上级 id 不能为空", groups={ValidationAdd.class})
	private String parentId;
	
	<#elseif bgMaple.mapleType == "04">
	/** ${bgMaple.mapleName ?replace('详情','')} id */
	@NotBlank(message="${bgMaple.mapleName ?replace('详情','')}id 不能为空", groups={ValidationAdd.class})
	private String ${bgMaple.mapleCode ?replace('Detail','')}Id;
	
	</#if>
	<#list bgMapleDetailList as bgMapleDetail>
	/** ${bgMapleDetail.mapleDetailName} */
		<#if bgMapleDetail.mapleDetailType == '01' || bgMapleDetail.mapleDetailType == '05'>
		<#if bgMapleDetail.mapleDetailCode = bgMaple.mapleCode+"Code">
	@Pattern(regexp = "^[a-z][a-zA-Z0-9_]*$", message="${bgMapleDetail.mapleDetailName} 需以小写字母开头的字母数字", groups={ValidationAdd.class, ValidationEdit.class}) 
		<#elseif bgMapleDetail.isEdit = "01">
	@NotBlank(message="${bgMapleDetail.mapleDetailName} 不能为空", groups={ValidationAdd.class, ValidationEdit.class})
		</#if>
	private String ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '02'>
		<#if bgMapleDetail.isEdit = "01">
	@Pattern(regexp = "^[1-9]\d*$", message="${bgMapleDetail.mapleDetailName} 需是数字", groups={ValidationAdd.class, ValidationEdit.class}) 
		</#if>
	private int ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '03'>
	private Date ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '04'>
		<#if bgMapleDetail.isEdit = "01">
	@Pattern(regexp = "^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$", message="${bgMapleDetail.mapleDetailName} 最多为两位小数", groups={ValidationAdd.class, ValidationEdit.class})
		</#if>
	private double ${bgMapleDetail.mapleDetailCode};
		</#if>
		
	</#list>
	
	
	/**
	 * 设置${bgMaple.mapleName} 主键id
	 * 
	 * @param String ${bgMaple.mapleCode}Id
	 */
	public void set${bgMaple.mapleCodeUpper}Id(String ${bgMaple.mapleCode}Id) {
		this.${bgMaple.mapleCode}Id = MapleStringUtil.trim(${bgMaple.mapleCode}Id);
	}
	
	/**
	 * 获取${bgMaple.mapleName} 主键id
	 * 
	 * @return String ${bgMaple.mapleCode}Id
	 */
	public String get${bgMaple.mapleCodeUpper}Id() {
		return this.${bgMaple.mapleCode}Id;
	}
	
	<#if bgMaple.mapleType == "02">
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
	
	<#elseif bgMaple.mapleType == "04">	
	/**
	 * 设置 ${bgMaple.mapleName ?replace('详情','')} id 
	 * 
	 * @param String ${bgMaple.mapleCode ?replace('Detail','')}Id
	 */
	public void set${bgMaple.mapleCodeUpper ?replace('Detail','')}Id(String ${bgMaple.mapleCode ?replace('Detail','')}Id) {
		this.${bgMaple.mapleCode ?replace('Detail','')}Id = MapleStringUtil.trim(${bgMaple.mapleCode ?replace('Detail','')}Id);
	}
	
	/**
	 * 获取 ${bgMaple.mapleName ?replace('详情','')} id 
	 * 
	 * @return String ${bgMaple.mapleCode ?replace('Detail','')}Id
	 */
	public String get${bgMaple.mapleCodeUpper ?replace('Detail','')}Id() {
		return this.${bgMaple.mapleCode ?replace('Detail','')}Id;
	}
	
	</#if>	
	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailType == '01' || bgMapleDetail.mapleDetailType == '05'>
	/**
	 * 设置 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @param String ${bgMapleDetail.mapleDetailCode}
	 */
	public void set${bgMapleDetail.mapleDetailCodeUpper}(String ${bgMapleDetail.mapleDetailCode}) {
		this.${bgMapleDetail.mapleDetailCode} = MapleStringUtil.trim(${bgMapleDetail.mapleDetailCode});
	}
	
	/**
	 * 获取 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @return String ${bgMapleDetail.mapleDetailCode}
	 */
	public String get${bgMapleDetail.mapleDetailCodeUpper}() {
		return this.${bgMapleDetail.mapleDetailCode};
	}
		<#elseif bgMapleDetail.mapleDetailType == '02'>
	/**
	 * 设置 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @param int ${bgMapleDetail.mapleDetailCode}
	 */
	public void set${bgMapleDetail.mapleDetailCodeUpper}(int ${bgMapleDetail.mapleDetailCode}) {
		this.${bgMapleDetail.mapleDetailCode} = ${bgMapleDetail.mapleDetailCode};
	}
	
	/**
	 * 获取 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @return int ${bgMapleDetail.mapleDetailCode}
	 */
	public int get${bgMapleDetail.mapleDetailCodeUpper}() {
		return this.${bgMapleDetail.mapleDetailCode};
	}
		<#elseif bgMapleDetail.mapleDetailType == '03'>
	/**
	 * 设置 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @param Date ${bgMapleDetail.mapleDetailCode}
	 */
	public void set${bgMapleDetail.mapleDetailCodeUpper}(Date ${bgMapleDetail.mapleDetailCode}) {
		this.${bgMapleDetail.mapleDetailCode} = ${bgMapleDetail.mapleDetailCode};
	}
	
	/**
	 * 获取 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @return Date ${bgMapleDetail.mapleDetailCode}
	 */
	public Date get${bgMapleDetail.mapleDetailCodeUpper}() {
		return this.${bgMapleDetail.mapleDetailCode};
	}	
		
	public void set${bgMapleDetail.mapleDetailCodeUpper}Str(String ${bgMapleDetail.mapleDetailCode}Str) throws Exception{
		${bgMapleDetail.mapleDetailCode}Str = MapleStringUtil.trim(${bgMapleDetail.mapleDetailCode}Str);
		if(!${bgMapleDetail.mapleDetailCode}Str.equals("")){
			try{
				set${bgMapleDetail.mapleDetailCodeUpper}(MapleDateUtil.parseDateStr(${bgMapleDetail.mapleDetailCode}Str));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String get${bgMapleDetail.mapleDetailCodeUpper}Str(){
		return MapleDateUtil.formatDate(get${bgMapleDetail.mapleDetailCodeUpper}());
	}	
		<#elseif bgMapleDetail.mapleDetailType == '04'>
	/**
	 * 设置 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @param double ${bgMapleDetail.mapleDetailCode}
	 */
	public void set${bgMapleDetail.mapleDetailCodeUpper}(double ${bgMapleDetail.mapleDetailCode}) {
		this.${bgMapleDetail.mapleDetailCode} = ${bgMapleDetail.mapleDetailCode};
	}
	
	/**
	 * 获取 ${bgMapleDetail.mapleDetailName}
	 * 
	 * @return double ${bgMapleDetail.mapleDetailCode}
	 */
	public double get${bgMapleDetail.mapleDetailCodeUpper}() {
		return this.${bgMapleDetail.mapleDetailCode};
	}
	
	public void set${bgMapleDetail.mapleDetailCodeUpper}Str(String ${bgMapleDetail.mapleDetailCode}Str){
		brateStr = com.cvicse.util.MapleStringUtil.trim(${bgMapleDetail.mapleDetailCode}Str);
		if(!${bgMapleDetail.mapleDetailCode}Str.equals("")){
			set${bgMapleDetail.mapleDetailCodeUpper}(DecimalUtil.strToDouble(${bgMapleDetail.mapleDetailCode}Str));
		}
	}

	public String get${bgMapleDetail.mapleDetailCodeUpper}Str(){
		return DecimalUtil.doubleToStr(get${bgMapleDetail.mapleDetailCodeUpper});
	}	
		</#if>
	
	</#list>
	
	/**************************table prop  end  *********************************/
}