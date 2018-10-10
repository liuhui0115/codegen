package com.oneday.codegen.util;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.util]    
 * 类名称:    [PathUtil]  
 * 类描述:    [路径工具类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class PathUtil {
	public static String getClassPath(){
		try{
			return Thread.currentThread().getContextClassLoader().getResource("").getPath().toString();
		}catch(Exception ex){
			return System.getProperty("user.dir")+"\\";
		}
	}
}
