package com.jx.${objectModuleNL}.entity;

import java.io.Serializable;
import java.util.Date;

import com.jx.common.util.DateUtil;
import com.jx.common.util.StringUtil;

public class ${objectModuleEU}${objectNameU} implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**************************custom prop satrt********************************/
	
	
	/**************************custom prop end**********************************/
	
	
	
	/**************************table prop satrt*********************************/
	
	/** ${tableName} 主键id */
	private int ${objectNameL}Id;
	
	<#list fieldList as var>
	/** ${var[2]} */
		<#if var[3] == 'propType_String'>
	private String ${var[1]};
		<#elseif var[3] == 'propType_Int'>
	private int ${var[1]};
		<#elseif var[3] == 'propType_Date'>
	private Date ${var[1]};
		<#elseif var[3] == 'propType_Double'>
	private double ${var[1]};
		</#if>
		
	</#list>
	
	
	/**
	 * 设置 ${tableName} 主键id
	 * 
	 * @param int ${objectNameL}Id
	 */
	public void set${objectNameU}Id(int ${objectNameL}Id) {
		this.${objectNameL}Id = ${objectNameL}Id;
	}
	
	/**
	 * 获取 ${tableName} 主键id
	 * 
	 * @return int ${objectNameL}Id
	 */
	public int get${objectNameU}Id() {
		return this.${objectNameL}Id;
	}
	
	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param String ${var[1]}
	 */
	public void set${var[0]}(String ${var[1]}) {
		this.${var[1]} = StringUtil.trim(${var[1]});
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return String ${var[1]}
	 */
	public String get${var[0]}() {
		return this.${var[1]};
	}
		<#elseif var[3] == 'propType_Int'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param int ${var[1]}
	 */
	public void set${var[0]}(int ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return int ${var[1]}
	 */
	public int get${var[0]}() {
		return this.${var[1]};
	}
		<#elseif var[3] == 'propType_Date'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param Date ${var[1]}
	 */
	public void set${var[0]}(Date ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return Date ${var[1]}
	 */
	public Date get${var[0]}() {
		return this.${var[1]};
	}	
		
	public void set${var[0]}Str(String ${var[1]}Str) throws Exception{
		${var[1]}Str = StringUtil.trim(${var[1]}Str);
		if(!${var[1]}Str.equals("")){
			try{
				set${var[0]}(DateUtil.parseDate(${var[1]}Str));
			}catch(java.text.ParseException e){
				throw new Exception(e);
			}
		}
	}

	public String get${var[0]}Str(){
		return DateUtil.getFormatedDateString(get${var[0]}());
	}	
		<#elseif var[3] == 'propType_Double'>
	/**
	 * 设置 ${var[2]}
	 * 
	 * @param double ${var[1]}
	 */
	public void set${var[0]}(double ${var[1]}) {
		this.${var[1]} = ${var[1]};
	}
	
	/**
	 * 获取 ${var[2]}
	 * 
	 * @return double ${var[1]}
	 */
	public double get${var[0]}() {
		return this.${var[1]};
	}
	
	public void set${var[0]}Str(String ${var[1]}Str){
		brateStr = com.cvicse.util.StringUtil.trim(${var[1]}Str);
		if(!${var[1]}Str.equals("")){
			set${var[0]}(DecimalUtil.strToDouble(${var[1]}Str));
		}
	}

	public String get${var[0]}Str(){
		return DecimalUtil.doubleToStr(get${var[0]});
	}	
		</#if>
	
	</#list>
	
	public ${objectModuleEU}${objectNameU}(){
		init();
	}
	
	public void init() {
		set${objectNameU}Id(0);
	
	<#list fieldList as var>
		<#if var[3] == 'propType_String'>
		set${var[0]}("");
		<#elseif var[3] == 'propType_Int'>
		set${var[0]}(0);
		<#elseif var[3] == 'propType_Date'>
		try {
			set${var[0]}Str("1900-01-01");
		} catch (Exception e) {
			e.printStackTrace();
		}
		<#elseif var[3] == 'propType_Double'>
		set${var[0]}(0.00);
		</#if>
	</#list>
	}
	/**************************table prop  end  *********************************/
}