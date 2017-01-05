package com.jx.${bgMaple.entityPackage}.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.${bgMaple.controllerPackage}.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.${bgMaple.entityPackage}.entity.${bgMaple.mapleEntityUpper};

@Service("${bgMaple.mapleEntityLower}Service")
public class ${bgMaple.mapleEntityUpper}Service {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void add(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		dao.add("${bgMaple.mapleEntityUpper}Mapper.add", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 修改 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void edit(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		dao.edit("${bgMaple.mapleEntityUpper}Mapper.edit", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 更改
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void change(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		dao.edit("${bgMaple.mapleEntityUpper}Mapper.change", ${bgMaple.mapleEntityLower});
	}

	/**
	 * 删除 
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @throws Exception
	 */
	public void deleteById(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception {
		PageData pd = new PageData();
		pd.put("${bgMaple.mapleCode}Id",${bgMaple.mapleCode}Id);
	<#list bgMapleDetailList as bgMapleDetail>
	<#if bgMapleDetail.isKey == "01">
		pd.put("${bgMapleDetail.mapleDetailCode}",${bgMapleDetail.mapleDetailCode});
	</#if>
	</#list>
		this.deleteByPd(pd);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("${bgMaple.mapleEntityUpper}Mapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("${bgMaple.mapleEntityUpper}Mapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return ${bgMaple.mapleEntityUpper}
	 * @throws Exception
	 */
	public ${bgMaple.mapleEntityUpper} findById(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception {
		PageData pd = new PageData();
		pd.put("${bgMaple.mapleCode}Id",${bgMaple.mapleCode}Id);
	<#list bgMapleDetailList as bgMapleDetail>
	<#if bgMapleDetail.isKey == "01">
		pd.put("${bgMapleDetail.mapleDetailCode}",${bgMapleDetail.mapleDetailCode});
	</#if>
	</#list>
		return this.findByPd(pd);
	}
	
	/**
	 * 通过pd获取(${bgMaple.mapleEntityUpper})数据 
	 * @param PageData pd
	 * @return ${bgMaple.mapleEntityUpper}
	 * @throws Exception
	 */
	public ${bgMaple.mapleEntityUpper} findByPd(PageData pd) throws Exception {
		return (${bgMaple.mapleEntityUpper}) dao.findForObject("${bgMaple.mapleEntityUpper}Mapper.findByPd", pd);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception {
		PageData pd = new PageData();
	pd.put("${bgMaple.mapleCode}Id",${bgMaple.mapleCode}Id);
	<#list bgMapleDetailList as bgMapleDetail>
	<#if bgMapleDetail.isKey == "01">
		pd.put("${bgMapleDetail.mapleDetailCode}",${bgMapleDetail.mapleDetailCode});
	</#if>
	</#list>
		return this.findPdByPd(pd);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("${bgMaple.mapleEntityUpper}Mapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> listByPd(PageData pd) throws Exception {
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> have(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.have", ${bgMaple.mapleEntityLower);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listPage", pd);
	}
	
	/****************************common * end***********************************/
}