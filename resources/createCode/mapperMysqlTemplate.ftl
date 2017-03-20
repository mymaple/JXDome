<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
	"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${bgMaple.mapleEntityUpper}Mapper">

	<sql id="${bgMaple.mapleCode}Columns">${bgMaple.mapleCode}Id<#if bgMaple.mapleType = "02">,parentId<#elseif bgMaple.mapleType = "04">,${bgMaple.mapleCode ?replace('Detail','')}Id</#if><#list bgMapleDetailList as bgMapleDetail>,${bgMapleDetail.mapleDetailCode}</#list>,orderNum,effective,createUserId,createTime,modifyUserId,modifyTime</sql>
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
		<#if bgMaple.mapleType = "02">
		<result column="parentId" property="parentId"/>
		<#elseif bgMaple.mapleType = "04">
		<result column="${bgMaple.mapleCode ?replace('Detail','')}Id" property="${bgMaple.mapleCode ?replace('Detail','')}Id"/>
		</#if>
	</resultMap>
	
	
	<!-- ****************************custom * start*********************************** -->
	
	<#if bgMaple.mapleType = "02">
	<!-- 获取(类)List数据  -->
	<select id="listByParentId" parameterType="String" resultType="${bgMaple.mapleEntityLower}">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where parentId = ${r"#{parentId}"} 
		order by (orderNum+0) DESC
	</select>
	<#elseif bgMaple.mapleType = "04">
	<!-- 获取(类)List数据  -->
	<select id="listByParentId" parameterType="String" resultType="${bgMaple.mapleEntityLower}">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where ${bgMaple.mapleCode ?replace('Detail','')}Id = ${r"#{"}${bgMaple.mapleCode ?replace('Detail','')}Id${r"}"} 
		order by (orderNum+0) DESC
	</select>
	</#if>
	
	<!-- ****************************custom * end  *********************************** -->
	
	
	<!-- ****************************common * satrt*********************************** -->
	<!-- 新增 -->
	<insert id="add" parameterType="${bgMaple.mapleEntityLower}">
		insert into 
	<include refid="${bgMaple.mapleCode}Table"/>
		( 
	<include refid="${bgMaple.mapleCode}Columns"/>
		) values (
			${r"#{"}${bgMaple.mapleCode}Id${r"}"}<#if bgMaple.mapleType = "02">,${r"#{parentId}"}<#elseif bgMaple.mapleType = "04">,${r"#{"}${bgMaple.mapleCode ?replace('Detail','')}Id${r"}"}</#if><#list bgMapleDetailList as bgMapleDetail>,${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"}</#list>,${r"#{orderNum}"},${r"#{effective}"},${r"#{createUserId}"},${r"#{createTime}"},${r"#{modifyUserId}"},${r"#{modifyTime}"}
		)
	</insert>
	
	<!-- 修改 -->
	<update id="edit" parameterType="${bgMaple.mapleEntityLower}">
		update
	<include refid="${bgMaple.mapleCode}Table"/>
		set 
			<#list bgMapleDetailList as bgMapleDetail>
			<#if bgMapleDetail.isKey == '00' && bgMapleDetail.isEdit == '01'>
			${bgMapleDetail.mapleDetailCode} = ${r"#{"}${bgMapleDetail.mapleDetailCode}${r"}"},
			</#if>
			</#list>
			orderNum = ${r"#{orderNum}"},
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
	
	<!-- 更改有效性 -->
	<update id="changeStatus" parameterType="comReceiveAddress">
		update
	<include refid="receiveAddressTable"/>
		set 
			${bgMaple.mapleCode}Status = ${r"#{"}${bgMaple.mapleCodeUpper}Status${r"}"} ,
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			receiveAddressId = ${r"#{"}receiveAddressId${r"}"} 
			and effective = ${r"#{"}oldValue${r"}"}
	</update>
	
	<!-- 更改有效性 -->
	<update id="changeEffective" parameterType="comReceiveAddress">
		update
	<include refid="receiveAddressTable"/>
		set 
			effective = ${r"#{"}effective${r"}"} ,
			modifyUserId = ${r"#{"}modifyUserId${r"}"},
			modifyTime = ${r"#{"}modifyTime${r"}"}
		where 
			receiveAddressId = ${r"#{"}receiveAddressId${r"}"} 
			and effective = ${r"#{"}oldValue${r"}"}
	</update>
	
	<!-- 删除 -->
	<delete id="deleteById" parameterType="String">
		delete from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where ${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
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
	<select id="findById" parameterType="String" resultType="${bgMaple.mapleEntityLower}">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where ${bgMaple.mapleCode}Id = ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="listAll" resultType="${bgMaple.mapleEntityLower}">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		order by (orderNum+0) DESC
	</select>
	
	<!-- 获取(类)List数据  -->
	<select id="otherHaveCode" parameterType="${bgMaple.mapleEntityLower}" resultMap="${bgMaple.mapleEntityLower}ResultMap">
		select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where ${bgMaple.mapleCode}Id != ${r"#{"}${bgMaple.mapleCode}Id${r"}"} 
			<#if bgMaple.mapleType = "02">
			 and parentId = ${r"#{"}parentId${r"}"} 
			<#elseif bgMaple.mapleType = "04">
			and ${bgMaple.mapleCode ?replace('Detail','')}Id = ${r"#{"}${bgMaple.mapleCode ?replace('Detail','')}Id${r"}"}
			</#if>
			 and ${bgMaple.mapleCode}Code = ${r"#{"}${bgMaple.mapleCode}Code${r"}"}
		order by (orderNum+0) DESC
	</select>
	
	<!-- 通过id获取(PageData)数据  -->
	<select id="listPage" parameterType="bgPage" resultType="pd">
			select 
	<include refid="${bgMaple.mapleCode}Columns"/>
		from 
	<include refid="${bgMaple.mapleCode}Table"/>
		where 1=1
			<#if bgMaple.mapleType = "02">
			<if test="pd.pId != null and pd.pId != ''"><!-- 角色检索 -->
			and parentId= ${r"#{pd.pId}"}
			</if>
			<#elseif bgMaple.mapleType = "04">
			and ${bgMaple.mapleCode ?replace('Detail','')}Id = ${r"#{pd."}${bgMaple.mapleCode ?replace('Detail','')}Id${r"}"}
			</#if>
		<if test="pd.keywords!= null and pd.keywords != ''"><!-- 关键词检索 -->
			and
				(
				${bgMaple.mapleCode}Code LIKE CONCAT(CONCAT('%', ${r"#{pd.keywords})"},'%')
				or
				${bgMaple.mapleCode}Name LIKE CONCAT(CONCAT('%', ${r"#{pd.keywords})"},'%')
				)
		</if>
		order by (orderNum+0) DESC
	</select>
	
	<!-- ****************************common * end  ********************************** -->
	
</mapper>