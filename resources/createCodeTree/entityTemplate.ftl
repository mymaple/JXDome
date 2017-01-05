package com.jx.${bgMaple.entityPackage}.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.MapleStringUtil;

public class ${bgMaple.mapleEntityUpper} implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	<#if bgMaple.mapleType == '02'>
	/** 指标 */
	private String target;
	
	/** 子列表 */
	private List<${bgMaple.mapleEntityUpper}> sub${bgMaple.mapleEntityUpper}List;
	
	/** 子列表 路径*/
	private String sub${bgMaple.mapleEntityUpper}ListPath;
	
	</#if>
	
	
	
	<#if bgMaple.mapleType == '02'>
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
	 * @return String sub${bgMaple.mapleEntityUpper}ListPath
	 */
	public String getSub${bgMaple.mapleEntityUpper}ListPath() {
		return this.sub${bgMaple.mapleEntityUpper}ListPath;
	}
	
	/**
	 * 设置 子列表 路径
	 * 
	 * @param String sub${bgMaple.mapleEntityUpper}ListPath
	 */
	public void setSub${bgMaple.mapleEntityUpper}ListPath(String sub${bgMaple.mapleEntityUpper}ListPath) {
		this.sub${bgMaple.mapleEntityUpper}ListPath = MapleStringUtil.trim(sub${bgMaple.mapleEntityUpper}ListPath);
	}
	
	</#if>
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** ${bgMaple.mapleName} 主键id */
	private String ${bgMaple.mapleCode}Id;
	
	<#if bgMaple.mapleType == '02'>
	/** 上级 id */
	private String parentId;
	</#if>
	
	<#if bgMaple.mapleType == '04'>
	/** ${bgMaple.mapleName?replace("详细","")} 主键id */
	private String ${bgMaple.mapleCode?replace("Detail","")}Id;
	</#if>
	
	<#list bgMapleDetailList as bgMapleDetail>
	/** ${bgMapleDetail.mapleDetailName} */
		<#if bgMapleDetail.mapleDetailType == '01'>
	private String ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '02'>
	private int ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '03'>
	private Date ${bgMapleDetail.mapleDetailCode};
		<#elseif bgMapleDetail.mapleDetailType == '04'>
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
	
	<#if bgMaple.mapleType == '02'>
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
	</#if>
	
	<#if bgMaple.mapleType == '04'>
	/**
	 * 设置${bgMaple.mapleName?replace("详细","")} 主键id
	 * 
	 * @param String ${bgMaple.mapleCode?replace("Detail","")}Id
	 */
	public void set${bgMaple.mapleCodeUpper?replace("Detail","")}Id(String ${bgMaple.mapleCode?replace("Detail","")}Id) {
		this.${bgMaple.mapleCode?replace("Detail","")}Id = MapleStringUtil.trim(${bgMaple.mapleCode?replace("Detail","")}Id);
	}
	
	/**
	 * 获取${bgMaple.mapleName?replace("详细","")} 主键id
	 * 
	 * @return String ${bgMaple.mapleCode?replace("Detail","")}Id
	 */
	public String get${bgMaple.mapleCodeUpper?replace("Detail","")}Id() {
		return this.${bgMaple.mapleCode?replace("Detail","")}Id;
	}
	</#if>
	
	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailType == '01'>
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
				set${bgMapleDetail.mapleDetailCodeUpper}(MapleDateUtil.parseDate(${bgMapleDetail.mapleDetailCode}Str));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String get${bgMapleDetail.mapleDetailCodeUpper}Str(){
		return MapleDateUtil.getFormatedDateString(get${bgMapleDetail.mapleDetailCodeUpper}());
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
	

	
	public ${bgMaple.mapleEntityUpper}(){
		init();
	}
	
	public void init() {
	
	<#list bgMapleDetailList as bgMapleDetail>
		<#if bgMapleDetail.mapleDetailType == '01'>
		set${bgMapleDetail.mapleDetailCodeUpper}("");
		<#elseif bgMapleDetail.mapleDetailType == '02'>
		set${bgMapleDetail.mapleDetailCodeUpper}(0);
		<#elseif bgMapleDetail.mapleDetailType == '03'>
		try {
			set${bgMapleDetail.mapleDetailCodeUpper}Str("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		<#elseif bgMapleDetail.mapleDetailType == '04'>
		set${bgMapleDetail.mapleDetailCodeUpper}(0.00);
		</#if>
	</#list>
	
	}
	/**************************table prop  end  *********************************/
}