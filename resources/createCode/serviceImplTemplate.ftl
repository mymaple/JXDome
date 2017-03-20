package com.jx.${bgMaple.entityPackage}.service.Impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.${bgMaple.controllerPackage}.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.config.shiro.ShiroSessionUtil;
import com.jx.common.util.MapleDateUtil;
import com.jx.common.util.UuidUtil;
import com.jx.${bgMaple.entityPackage}.entity.${bgMaple.mapleEntityUpper};
import com.jx.${bgMaple.entityPackage}.service.${bgMaple.mapleEntityUpper}Service;

@Service("${bgMaple.mapleEntityLower}Service")
public class ${bgMaple.mapleEntityUpper}ServiceImpl implements ${bgMaple.mapleEntityUpper}Service{

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	<#if bgMaple.mapleType = "02">
	
	/**
	 * 根据parentId 获取所有直接fu
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	public void getParentList(List<${bgMaple.mapleEntityUpper}> parentList, String pId) throws Exception {
		if("0".equals(pId)) return;
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = this.findById(pId);
		${bgMaple.mapleEntityLower}.setSub${bgMaple.mapleEntityUpper}Path("background/${bgMaple.mapleCode}/list.do?pId="+pId);
		parentList.add(0, ${bgMaple.mapleEntityLower});
		this.getParentList(parentList, ${bgMaple.mapleEntityLower}.getParentId());
	}
	
	/**
	 * 根据parentId 获取所有直接子
	 * @param String parentId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> listByParentId(String parentId) throws Exception {
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listByParentId", parentId);
	}
	
	/**
	 * 获取所有子列表(递归处理)
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return
	 * @throws Exception
	 */
	public List<${bgMaple.mapleEntityUpper}> listInRank(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception {
		List<${bgMaple.mapleEntityUpper}> ${bgMaple.mapleEntityLower}List = this.listByParentId(${bgMaple.mapleCode}Id);
		for(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} : ${bgMaple.mapleEntityLower}List){
			${bgMaple.mapleEntityLower}.setSub${bgMaple.mapleEntityUpper}Path("background/${bgMaple.mapleCode}/list.do?pId="+${bgMaple.mapleEntityLower}.get${bgMaple.mapleCodeUpper}Id());
			${bgMaple.mapleEntityLower}.setSub${bgMaple.mapleEntityUpper}List(this.listInRank(${bgMaple.mapleEntityLower}.get${bgMaple.mapleCodeUpper}Id()<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>));
			${bgMaple.mapleEntityLower}.setTarget("treeFrame");
		}
		return ${bgMaple.mapleEntityLower}List;
	}
	
	/**
	 * 删除所有子列表(递归处理)
	 * @param String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>
	 * @return
	 * @throws Exception
	 */
	public void deleteInRank(String ${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, String ${bgMapleDetail.mapleDetailCode}</#if></#list>) throws Exception {
		this.deleteById(${bgMaple.mapleCode}Id);
		List<${bgMaple.mapleEntityUpper}> ${bgMaple.mapleEntityLower}List = this.listByParentId(${bgMaple.mapleCode}Id);
		for(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} : ${bgMaple.mapleEntityLower}List){
			this.deleteInRank(${bgMaple.mapleEntityLower}.get${bgMaple.mapleCodeUpper}Id()<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">, ${bgMapleDetail.mapleDetailCode}</#if></#list>);
		}
	}
	
	/**
	 * 批量删除所有子列表(递归处理)
	 * @param String dictId
	 * @return
	 * @throws Exception
	 */
	public void batchDeleteInRank(String[] ids) throws Exception {
		for(String id : ids){
			this.deleteInRank(id);
		}
	}
	<#elseif bgMaple.mapleType = "04">
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> listBy${bgMaple.mapleCodeUpper ?replace('Detail','')}Id(String ${bgMaple.mapleCode ?replace('Detail','')}Id) throws Exception {
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listBy${bgMaple.mapleCodeUpper ?replace('Detail','')}Id", ${bgMaple.mapleCode ?replace('Detail','')}Id);
	}
	</#if>
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void add(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		
		Date nowTime = new Date();
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(UuidUtil.get32UUID());
		<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isEdit == '00'>
			<#if bgMapleDetail.mapleDetailType == '01' || bgMapleDetail.mapleDetailType == '05'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>""</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '02'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '03'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>nowTime</#if>);
			<#elseif bgMapleDetail.mapleDetailType == '04'>
		${bgMaple.mapleEntityLower}.set${bgMapleDetail.mapleDetailCodeUpper}(<#if bgMapleDetail.defaultValue != ''>${bgMapleDetail.defaultValue}<#else>0.00</#if>);
			</#if>
			</#if>
		</#list>
		${bgMaple.mapleEntityLower}.setEffective("01");
		${bgMaple.mapleEntityLower}.setCreateUserId(ShiroSessionUtil.getUserId());
		${bgMaple.mapleEntityLower}.setCreateTime(nowTime);
		${bgMaple.mapleEntityLower}.setModifyUserId(ShiroSessionUtil.getUserId());
		${bgMaple.mapleEntityLower}.setModifyTime(nowTime);
		
		dao.add("${bgMaple.mapleEntityUpper}Mapper.add", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 修改 
	 * @param ${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}
	 * @throws Exception
	 */
	public void edit(${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower}) throws Exception {
		Date nowTime = new Date();
		${bgMaple.mapleEntityLower}.setModifyUserId(ShiroSessionUtil.getUserId());
		${bgMaple.mapleEntityLower}.setModifyTime(nowTime);
	
		dao.update("${bgMaple.mapleEntityUpper}Mapper.edit", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 更改状态 flag 00
	 * @param String flag, String ${bgMaple.mapleCode}Id
	 * @throws Exception
	 */
	public void changeStatus(String flag, String ${bgMaple.mapleCode}Id) throws Exception {
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
		if("00".equals(flag)){
			${bgMaple.mapleEntityLower}.setOldValue("01");
		}else if("01".equals(flag)){
			${bgMaple.mapleEntityLower}.setOldValue("00");
		}else{
			${bgMaple.mapleEntityLower}.setOldValue("flag");
		}
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Status(flag);
		
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(${bgMaple.mapleCode}Id);
		Date nowTime = new Date();
		${bgMaple.mapleEntityLower}.setModifyUserId(ShiroSessionUtil.getUserId());
		${bgMaple.mapleEntityLower}.setModifyTime(nowTime);
		dao.update("${bgMaple.mapleEntityUpper}Mapper.changeStatus", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 更改有效性 flag 00:使失效;01：使生效
	 * @param String flag, String ${bgMaple.mapleCode}Id
	 * @throws Exception
	 */
	public void changeEffective(String flag, String ${bgMaple.mapleCode}Id) throws Exception {
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
		if("00".equals(flag)){
			${bgMaple.mapleEntityLower}.setOldValue("01");
		}else if("01".equals(flag)){
			${bgMaple.mapleEntityLower}.setOldValue("00");
		}else{
			${bgMaple.mapleEntityLower}.setOldValue("flag");
		}
		${bgMaple.mapleEntityLower}.setEffective(flag);
		
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(${bgMaple.mapleCode}Id);
		Date nowTime = new Date();
		${bgMaple.mapleEntityLower}.setModifyUserId(ShiroSessionUtil.getUserId());
		${bgMaple.mapleEntityLower}.setModifyTime(nowTime);
		dao.update("${bgMaple.mapleEntityUpper}Mapper.changeEffective", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 删除 
	 * @param String ${bgMaple.mapleCode}Id
	 * @throws Exception
	 */
	public void deleteById(String ${bgMaple.mapleCode}Id) throws Exception {
		dao.delete("${bgMaple.mapleEntityUpper}Mapper.deleteById", ${bgMaple.mapleCode}Id);
	}
	
	/**
	 * 批量删除 
	 * @param String[] ids
	 * @throws Exception
	 */
	public void batchDeleteByIds(String[] ids) throws Exception {
		dao.delete("${bgMaple.mapleEntityUpper}Mapper.batchDeleteByIds", ids);
	}
	
	/**
	 * 通过id获取(类)数据
	 * @param String ${bgMaple.mapleCode}Id
	 * @return ${bgMaple.mapleEntityUpper}
	 * @throws Exception
	 */
	public ${bgMaple.mapleEntityUpper} findById(String ${bgMaple.mapleCode}Id) throws Exception {
		return (${bgMaple.mapleEntityUpper}) dao.findForObject("${bgMaple.mapleEntityUpper}Mapper.findById", ${bgMaple.mapleCode}Id);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> listAll() throws Exception {
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listAll", null);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<${bgMaple.mapleEntityUpper}> otherHaveCode(String ${bgMaple.mapleCode}Id, String ${bgMaple.mapleCode}Code) throws Exception {
		${bgMaple.mapleEntityUpper} ${bgMaple.mapleEntityLower} = new ${bgMaple.mapleEntityUpper}();
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Id(${bgMaple.mapleCode}Id);
		${bgMaple.mapleEntityLower}.set${bgMaple.mapleCodeUpper}Code(${bgMaple.mapleCode}Code);
		return (List<${bgMaple.mapleEntityUpper}>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.otherHaveCode", ${bgMaple.mapleEntityLower});
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param BgPage BgPage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PageData> listPage(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("${bgMaple.mapleEntityUpper}Mapper.listPage", bgPage);
	}
	
	/****************************common * end***********************************/
}