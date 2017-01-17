package com.jx.${bgMaple.entityPackage}.service;

import java.util.List;

import com.jx.${bgMaple.controllerPackage}.config.BgPage;
import com.jx.common.config.PageData;
import com.jx.${bgMaple.entityPackage}.entity.${bgMaple.mapleEntityUpper};

public interface ${bgMaple.mapleEntityUpper}Service {

	
	/****************************custom * start***********************************/

	<#if bgMaple.mapleType = "02">
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<${bgMaple.mapleEntityUpper}> parentList, String pId) throws Exception ;
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> listByParentId(String parentId) throws Exception ;
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> listInRank(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception ;
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception ;
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception ;
	
	<#elseif bgMaple.mapleType = "04">
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMapleDetail> listBy${bgMaple.mapleCodeUpper ?replace('Detail','')}Id(String ${bgMaple.mapleCode ?replace('Detail','')}Id) throws Exception ;
	
	</#if>
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void add(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception ;
	
	/**
	 * 修改 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void edit(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception ;
	
	/**
	 * 更改
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void change(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception ;

	/**
	 * 删除 
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @throws Exception
	 */
	public void deleteById(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception ;
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception ;
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception ;

	/**
	 * 通过id获取(类)数据
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return ${bgMaple.mapleEntityUpper}
	 * @throws Exception
	 */
	public ${bgMaple.mapleEntityUpper} findById(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception ;
	
	/**
	 * 通过pd获取(${bgMaple.mapleEntityUpper})数据 
	 * @param PageData pd
	 * @return ${bgMaple.mapleEntityUpper}
	 * @throws Exception
	 */
	public ${bgMaple.mapleEntityUpper} findByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> listByPd(PageData pd) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> otherHave(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception ;
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> otherHaveCode(String ${bgMaple.mapleCode}Id, String ${bgMaple.mapleCode}Code) throws Exception ;
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listPage(BgPage bgPage) throws Exception ;
	
	/****************************common * end***********************************/
}