<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bgMaple.mapleEntityUpper}Mapper">

	<sql id="${bgMaple.mapleCode}Columns">${bgMaple.mapleCode}Id,parentId<#list bgMapleDetailList as bgMapleDetail>,${bgMapleDetail.mapleDetailCode}</#list>,orderNum,effective,createUserId,createTime,modifyUserId,modifyTime</sql>
	<sql id="${bgMaple.mapleCode}Table">${bgMaple.tableCode}</sql>
	
	<resultMap type="${bgMaple.mapleEntityLower}" id="${bgMaple.mapleEntityLower}ResultMap">
		<id column="${bgMaple.mapleCode}Id" property="${bgMaple.mapleCode}Id"/>
			<#list bgMapleDetailList as bgMapleDetail>
		<<#if bgMapleDetail.isKey == '01'>id<#else>result</#if> column="${bgMapleDetail.mapleDetailCode}" property="${bgMapleDetail.mapleDetailCode}"/>
			</#list>
		<result column="orderNum" property="orderNum"/>
		<result column="effective" property="effective"/>
		<result column="createUserId" property="createUserId"/>
		<result column="createTime" property="createTime"/>
		<result column="modifyUserId" property="modifyUserId"/>
		<result column="modifyTime" property="modifyTime"/>
		<result column="parentId" property="parentId"/>
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
			${r"#{"}${bgMaple.mapleCode}Id${r"}"},${r"#{"}parentId${r"}"}<#list bgMapleDetailList as bgMapleDetail>,${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}</#list>,${r"#{orderNum}"},${r"#{effective}"},${r"#{createUserId}"},${r"#{createTime}"},${r"#{modifyUserId}"},${r"#{modifyTime}"}
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
			<if test="orderNum!=null and orderNum!=''">
			orderNum = ${r"#{orderNum}"},
			</if>
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
			and modifyTime = ${r"#{"}lastModifyTime${r"}"}
	</update>
	
	<!-- 改变 -->
	<update id="change" parameterType="${bgMaple.mapleEntityLower}">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00'>
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"},
			</if>
			</#if>
			</#list>
			<if test="orderNum!=null and orderNum!=''">
			orderNum = ${r"#{orderNum}"},
			</if>
			<if test="effective!=null and effective!=''">
			effective = ${r"#{effective}"},
			</if>
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#list bgMapleDetailList as bgMapleDetail> 
			<#if bgMapleDetail.isKey == "01">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</#if>
			</#list>
			and modifyTime = ${r"#{"}lastModifyTime${r"}"}
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
	
	<!-- 获取(类)List数据  -->
	<select id="listByPd" parameterType="pd" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
			<if test="parentId!=null and parentId!=''">
			 and parentId = ${r"#{"}parentId${r"}"} 
			</if>
		order by (orderNum+0) 
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="otherHave" parameterType="${bgMaple.mapleEntityLower}" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
			<if test="${bgMaple.mapleCode}Id!=null and ${bgMaple.mapleCode}Id!=''">
			 and ${bgMaple.mapleCode}Id != ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			</if>
			<if test="parentId!=null and parentId!=''">
			 and parentId = ${r"#{"}parentId${r"}"} 
			</if>
			<#list bgMapleDetailList as bgMapleDetail> 
			<if test="${bgMapleDetail.mapleDetailCode}!=null and ${bgMapleDetail.mapleDetailCode}!=''">
			 and ${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}
			</if>
			</#list>
			<if test="orderNum!=null and orderNum!=''">
			 and orderNum = ${r"#{orderNum}"}
			</if>
			<if test="effective!=null and effective!=''">
			 and effective = ${r"#{effective}"}
			</if>
			<if test="createUserId!=null and createUserId!=''">
			 and createUserId = ${r"#{createUserId}"}
			</if>
			<if test="createTime!=null and createTime!=''">
			 and createTime = ${r"#{createTime}"}
			</if>
			<if test="modifyUserId!=null and modifyUserId!=''">
			 and modifyUserId = ${r"#{modifyUserId}"}
			</if>
			<if test="modifyTime!=null and modifyTime!=''">
			 and modifyTime = ${r"#{modifyTime}"}
			</if>
		order by (orderNum+0) 
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listPage" parameterType="bgPage" resultType="pd">
			select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
		<if test="pd.pId != null and pd.pId != ''"><!-- 角色检索 -->
			and parentId= ${r"#{pd.pId}"}
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