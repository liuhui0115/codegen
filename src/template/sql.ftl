<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--
	描述: 数据访问语句-${tableComment}
	作者: ${author}
	时间: ${createTime}
 -->
<mapper namespace="${daoPackage}.${entityName}Mapper">

	<resultMap type="${voPackage}.${entityName}Vo" id="baseResultMap">
		<#if columnList?exists> 
		<#list columnList as item> 
		<result property="${item.attribute}" column="${item.column}" />
		</#list> 
		</#if>
	</resultMap>
	
	<sql id="baseWhereClause">
		<where>
			<!--
			1 = 1
			<if test="params['exampleName'] != null and  params['exampleName'] != ''">
				and example_name = ${SYMBOL_POUND}{params.exampleName}
			</if>
			-->
		</where>
	</sql>
	<sql id="baseColumnList">
		${columns}
	</sql>
	
	<!-- 新增 -->
	<insert id="add" parameterType="${entityPackage}.${entityName}">
		insert into 
			${tableName}(${columns}) 
		values(
			<#if columnList?exists> 
			<#list columnList as item>
			${SYMBOL_POUND}{${item.attribute},jdbcType=${item.jdbcType}} <#if item_has_next>,</#if>
			</#list> 
			</#if>
		)
	</insert>
	
	<!-- 列表 -->
	<select id="list" parameterType="${dtoPackage}.${entityName}Dto" resultMap="baseResultMap">
		select
		<include refid="baseColumnList" />
		from ${tableName}
		where is_deleted = 0
	</select>
	
	<!-- 详情 -->
	<select id="get" parameterType="java.lang.Long" resultMap="baseResultMap">
		select 
		<include refid="baseColumnList"/>
		from ${tableName} t where is_deleted = 0 and ${columnId} = ${SYMBOL_POUND}{${attributeId}}
	</select>
	
	<!-- 修改 -->
	<update id="mod" parameterType="${entityPackage}.${entityName}">
		update ${tableName} set ${columnId} = ${SYMBOL_POUND}{${attributeId}}
		<#if columnList?exists> 
		<#list columnList as item>
		<#if item.column != columnId && item.column != "is_deleted">
			, ${item.column} = ${SYMBOL_POUND}{${item.attribute}}
		</#if>
		</#list> 
		</#if>
		where ${columnId} = ${SYMBOL_POUND}{${attributeId}}
	</update> 
	
	<!-- 删除 -->
	<update id="del" parameterType="java.lang.Long">
	    update ${tableName} set is_deleted = 1 where ${columnId} = ${SYMBOL_POUND}{${attributeId}}
	</update>
</mapper>