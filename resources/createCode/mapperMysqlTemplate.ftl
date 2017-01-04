<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bgMaple.mapleEntityUpper}Mapper">

	<sql id="${bgMaple.mapleCode}Columns"><#list bgMapleDetailList as bgMapleDetail>${bgMapleDetail.mapleDetailCode}<#if bgMapleDetail_has_next>,</#if></#list></sql>
	<sql id="${bgMaple.mapleCode}Table">${bgMaple.tableCode}</sql>
	
	<resultMap type="${bgMaple.mapleEntityLower}" id="${bgMaple.mapleEntityLower}ResultMap">
			<#list bgMapleDetailList as bgMapleDetail>
		<<#if bgMapleDetail.isKey == '01'>id<#else>result</#if> column="${bgMapleDetail.mapleDetailCode}" property="${bgMapleDetail.mapleDetailCode}"/>
			</#list>
	</resultMap>
	
	
	<!-- ****************************custom * start*********************************** -->
	
	
	<!-- ****************************custom * end  *********************************** -->
	
	
	<!-- ****************************common * satrt*********************************** -->
	<!-- 新增 -->
	<insert id="add" parameterType="${bgMaple.mapleEntityLower}">
		insert into 
	<include refid="${bgMaple.mapleCode}Table"/>
		( 
	<include refid="${bgMaple.mapleCode}Columns"/>
		) values (
			<#list bgMapleDetailList as bgMapleDetail>${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 新增 -->
	<insert id="addByPd" parameterType="pd">
		insert into 
	<include refid="${bgMaple.mapleCode}Table"/>
		( 
	<include refid="${bgMaple.mapleCode}Columns"/>
		) values (
			<#list bgMapleDetailList as bgMapleDetail>${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next>,</#if></#list>
		)
	</insert>
	
	<!-- 修改 -->
	<update id="edit" parameterType="${bgMaple.mapleEntityLower}">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00'>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next>,</#if>
			</#if>
			</#list>
		where 
			<#list bgMapleDetailKeyList as bgMapleDetail>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next> and </#if>
			</#list>
	</update>
	
	<!-- 修改 -->
	<update id="editByPd" parameterType="pd">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00'>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next>,</#if>
			</#if>
			</#list>
		where 
			<#list bgMapleDetailKeyList as bgMapleDetail>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next> and </#if>
			</#list>
	</update>
	
	<!-- 删除 -->
	<delete id="deleteByPd" parameterType="pd">
		delete from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 
			<#list bgMapleDetailKeyList as bgMapleDetail>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next> and </#if>
			</#list>
	</delete>
	
	<!-- 批量删除 -->
	<delete id="batchDeleteByIds" parameterType="String">
		delete from 
 <include refid="${bgMaple.mapleCode}Table"/>
		where 
			${bgMaple.mapleCode}Id in 
        <foreach item="id" index="index" collection="array" open="(" separator="," close=")">
            ${r"#{"}id${r"}"}
        </foreach>
	</delete>
	
	<!-- 通过id获取(类)数据 -->
	<select id="findByPd" parameterType="pd" resultType="${bgMaple.mapleEntityLower}">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 
			<#list bgMapleDetailKeyList as bgMapleDetail>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next> and </#if>
			</#list>
	</select>
	
	<!-- 通过pd获取(PageData)数据  -->
	<select id="findPdByPd" parameterType="pd" resultType="pd">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 
			<#list bgMapleDetailKeyList as bgMapleDetail>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}<#if bgMapleDetail_has_next> and </#if>
			</#list>
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="listByPd" parameterType="pd" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listPage" parameterType="bgPage" resultType="pd">
			select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
	</select>
	
	<!-- ****************************common * end  ********************************** -->
	
</mapper>