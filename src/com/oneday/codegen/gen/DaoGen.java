package com.oneday.codegen.gen;

import java.util.List;
import java.util.Map;

import com.oneday.codegen.pojo.Column;
import com.oneday.codegen.pojo.Config;
import com.oneday.codegen.util.TemplateUtil;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.gen]    
 * 类名称:    [DaoGen]  
 * 类描述:    [dao代码生成类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class DaoGen {
	/**
	 * 生成实体类
	 */
	public static String genModel(Config config) throws Exception{
		String fileName = config.getEntityPackage().replaceAll("\\.", "/") 
				+ "/" + config.getEntityName() + ".java";
		System.out.println("entity文件路径：" + fileName);
		
		Map<String, Object> map = config.transToMap();
		TemplateUtil.getInstance().gen(config.transToMap(), config.getTemplateMap().get("entity"), fileName);
		
		return null;
	}


	/**
	 * 生成dao
	 */
	public static String genDao(Config config) throws Exception{
		System.out.println(config.getDaoPackage());
		String fileName = config.getDaoPackage().replaceAll("\\.", "/") 
				+ "/" + config.getEntityName() + config.getTemplateMap().get("daoSuffix") + ".java";
		System.out.println("dao文件路径：" + fileName);
		TemplateUtil.getInstance().gen(config.transToMap(),config.getTemplateMap().get("dao"), fileName);
		
		return null;
	}

	/**
	 * 生成sql映射文件
	 */
	public static String genSql(Config config) throws Exception{
		//生成sql代码
		String fileName = config.getDaoPackage().replaceAll("\\.", "/");
		if("true".equalsIgnoreCase(config.getTemplateMap().get("showSqlFolder"))){
			fileName = fileName + "/" + config.getDataBaseType();
		}
		fileName = fileName + "/" + config.getEntityName() + config.getTemplateMap().get("daoSuffix") + ".xml";
		System.out.println("sql文件路径：" + fileName);
		
		/*
		 * 因为freemarker有自己的特殊符号<#，${
		 * 所以mysql的sql配置文件将#{，${换成%{，^{来表示
		 * 因此在转成代码的时候要将其还原
		 */
		Map<String, Object> map = config.transToMap();
		map.put("SYMBOL_POUND", "#");
		map.put("SYMBOL_DOLLAR", "$");
		TemplateUtil.getInstance().gen(map, config.getTemplateMap().get("sql"), fileName);
		
		
		return null;
	}
}
