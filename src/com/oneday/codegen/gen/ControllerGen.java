package com.oneday.codegen.gen;

import com.oneday.codegen.pojo.Config;
import com.oneday.codegen.util.TemplateUtil;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.gen]    
 * 类名称:    [ControllerGen]  
 * 类描述:    [controller代码生成类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class ControllerGen {
	public static String genBaseController(Config config) throws Exception{
		System.out.println(config.getControllerPackage());
		String fileName = config.getControllerPackage().replaceAll("\\.", "/") 
				+ "/" + config.getEntityName() + "Controller.java";
		System.out.println("service文件路径：" + fileName);
		TemplateUtil.getInstance().gen(config.transToMap(), config.getTemplateMap().get("controller"), fileName);
		
		return null;
	}

}
