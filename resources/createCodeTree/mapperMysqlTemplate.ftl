<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bgMaple.mapleEntityUpper}Mapper">

	<sql id="${bgMaple.mapleCode}Columns">${bgMaple.mapleCode}Id,parentId<#list bgMapleDetailList as bgMapleDetail>,${bgMapleDetail.mapleDetailCode}</#list></sql>
	<sql id="${bgMaple.mapleCode}Table">${bgMaple.tableCode}</sql>
	
	<resultMap type="${bgMaple.mapleEntityLower}" id="${bgMaple.mapleEntityLower}ResultMap">
		<id column="${bgMaple.mapleCode}Id" property="${bgMaple.mapleCode}Id"/>
			<#list bgMapleDetailList as bgMapleDetail>
		<<#if bgMapleDetail.isKey == '01'>id<#else>result</#if> column="${bgMapleDetail.mapleDetailCode}" property="${bgMapleDetail.mapleDetailCode}"/>
			</#list>
		<result column="parentId" property="parentId"/>
	</resultMap>
	
	
	<!-- ****************************custom * start*********************************** -->
	
	<!-- 根据parentId 获取所有直接子菜单 -->
	<select id="listByParentId" parameterType="String" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select  
<include refid="${bgMaple.mapleCode}Columns"/>
		from 
<include refid="${bgMaple.mapleCode}Table"/>		
		where parentId = ${r"#{"}parentId${r"}"} 
		order by (orderNum+0) 
	</select>
	
	<!-- ****************************custom * end  *********************************** -->
	
	
	<!-- ****************************common * satrt*********************************** -->
	<!-- 新增 -->
	<insert id="add" parameterType="${bgMaple.mapleEntityLower}">
		insert into 
	<include refid="${bgMaple.mapleCode}Table"/>
		( 
	<include refid="${bgMaple.mapleCode}Columns"/>
		) values (
			${r"#{"}${bgMaple.mapleCode}Id${r"}"},${r"#{"}parentId${r"}"}<#list bgMapleDetailList as bgMapleDetail>,${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}</#list>
		)
	</insert>
	
	<!-- 新增 -->
	<insert id="addByPd" parameterType="pd">
		insert into 
	<include refid="${bgMaple.mapleCode}Table"/>
		( 
	<include refid="${bgMaple.mapleCode}Columns"/>
		) values (
			${r"#{"}${bgMaple.mapleCode}Id${r"}"},${r"#{"}parentId${r"}"}<#list bgMapleDetailList as bgMapleDetail>,${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}</#list>
		)
	</insert>
	
	<!-- 修改 -->
	<update id="edit" parameterType="${bgMaple.mapleEntityLower}">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00' && bgMapleDetail.isEdit == '01'>
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"},
			</if>
			</#if>
			</#list>
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</update>
	
	<!-- 修改 -->
	<update id="editByPd" parameterType="pd">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00' && bgMapleDetail.isEdit == '01'>
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"},
			</if>
			</#if>
			</#list>
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</update>
	
	<!-- 改变 -->
	<update id="change" parameterType="${bgMaple.mapleEntityLower}">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<if test="parentId!=null and parentId!=''">
			parentId = ${r"#{"}parentId${r"}"},
			</if>
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00' 
			&& bgMapleDetail.mapleDetailCode != 'createUserId'
			&& bgMapleDetail.mapleDetailCode != 'createTime'
			&& bgMapleDetail.mapleDetailCode != 'modifyUserId'
			&& bgMapleDetail.mapleDetailCode != 'modifyTime'
			>
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"},
			</if>
			</#if>
			</#list>
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</update>
	
	<!-- 删除 -->
	<delete id="deleteByPd" parameterType="pd">
		delete from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</delete>
	
	<!-- 批量删除 -->
	<delete id="batchDeleteByIds" parameterType="String">
		delete from 
 <include refid="${bgMaple.mapleCode}Table"/>
		where 
			CONCAT(${bgMaple.mapleCode}Id<#list bgMapleDetailList as bgMapleDetail><#if bgMapleDetail.isKey == "01">,'-',${bgMapleDetail.mapleDetailCode}</#if></#list>) in 
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
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</select>
	
	<!-- 通过pd获取(PageData)数据  -->
	<select id="findPdByPd" parameterType="pd" resultType="pd">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="listByPd" parameterType="pd" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
		order by (orderNum+0) 
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="has" parameterType="${bgMaple.mapleEntityLower}" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
			<if test="${bgMaple.mapleCode}Id!=null and ${bgMaple.mapleCode}Id!=''">
			 and ${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			</if>
			<if test="parentId!=null and parentId!=''">
			 and parentId = ${r"#{"}parentId${r"}"} 
			</if>
			<#list bgMapleDetailList as bgMapleDetail> 
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</if>
			</#list>
		order by (orderNum+0) 
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listPage" parameterType="bgPage" resultType="pd">
			select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
		<if test="pd.parentId != null and pd.parentId != ''"><!-- 角色检索 -->
			and parentId= ${r"#{pd.parentId}"}
		</if>
		<if test="pd.keywords!= null and pd.keywords != ''"><!-- 关键词检索 -->
			and
				(
				${bgMaple.mapleCode}Code LIKE CONCAT(CONCAT('%', ${r"#{pd.keywords})"},'%')
				or
				${bgMaple.mapleCode}Name LIKE CONCAT(CONCAT('%', ${r"#{pd.keywords})"},'%')
				)
		</if>
		order by (orderNum+0)
	</select>
	
	<!-- ****************************common * end  ********************************** -->
	
</mapper>