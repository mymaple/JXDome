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
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
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