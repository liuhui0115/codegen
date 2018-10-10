package com.oneday.codegen.gen;

import com.oneday.codegen.pojo.Config;
import com.oneday.codegen.util.TemplateUtil;
/**
 * 
 * 项目名称:  [codegen]
 * 包名:     [com.oneday.codegen.gen]    
 * 类名称:    [ServiceGen]  
 * 类描述:    [业务层代码生成类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class ServiceGen {
	/**
	 * 生成biz接口
	 */
	public static String genService(Config config) throws Exception{
		System.out.println(config.getServicePackage());
		String fileName = config.getServicePackage().replaceAll("\\.", "/") 
				+ "/" + config.getEntityName() + "Service.java";
		System.out.println("service文件路径：" + fileName);
		TemplateUtil.getInstance().gen(config.transToMap(), config.getTemplateMap().get("service"), fileName);
		
		return null;
	}

	/**
	 * 生成biz接口实现
	 */
	public static String genServiceImpl(Config config) throws Exception{
		System.out.println(config.getServicePackage()+".impl");
		String fileName = (config.getServicePackage()+".impl").replaceAll("\\.", "/") 
				+ "/" + config.getEntityName() + "ServiceImpl.java";
		System.out.println("serviceImpl文件路径：" + fileName);
		TemplateUtil.getInstance().gen(config.transToMap(), config.getTemplateMap().get("serviceImpl"), fileName);
		
		return null;
	}
}
