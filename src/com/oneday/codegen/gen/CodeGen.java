package com.oneday.codegen.gen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.oneday.codegen.pojo.Config;
import com.oneday.codegen.util.ConfigUtil;
import com.oneday.codegen.util.PathUtil;
import com.oneday.codegen.util.TemplateUtil;

/**
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.gen]    
 * 类名称:    [CodeGen]  
 * 类描述:    [代码生成主类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class CodeGen {

	public void writeToFile(String Text, String dir, String fileName) {
		File fileDir = null;
		File file = null;
		FileWriter fw = null;
		try {
			dir = new File("").getAbsolutePath() + "\\" + dir;
			fileName = dir + fileName;
			fileDir = new File(dir);
			file = new File(fileName);
			if (!fileDir.exists())
				fileDir.mkdirs();
			fw = new FileWriter(file);
			fw.write(Text);
		} catch (IOException e1) {
			e1.printStackTrace();
			try {
				fw.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void writeToFileOnce(String Text, String dir, String fileName) {
		File fileDir = null;
		File file = null;
		FileWriter fw = null;
		try {
			dir = new File("").getAbsolutePath() + "\\" + dir;
			fileName = dir + fileName;
			fileDir = new File(dir);
			file = new File(fileName);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			if (!file.exists()) {
				fw = new FileWriter(file);
				fw.write(Text);
				fw.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
			try {
				fw.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		System.out.println("配置文件路径："+ PathUtil.getClassPath() + "config.xml");
		
		//解析配置文件
		Document document = null;
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("UTF-8");
		
		document = saxReader.read(PathUtil.getClassPath() + "config.xml");

		Element root = document.getRootElement();
		
		Config config = new Config();
		
		//jdbc
		config.setDataBaseDrive(root.element("dataBaseDrive").getTextTrim());
		config.setDataBaseConnectStr(root.element("dataBaseConnectStr").getTextTrim());
		config.setDataBaseName(root.element("dataBaseName").getTextTrim());
		config.setDataBasePassword(root.element("dataBasePassword").getTextTrim());
		//model
		config.setTableName(root.element("tableName").getTextTrim());
		config.setEntityName(root.element("modelName").getTextTrim());
		//package
		config.setEntityPackage(root.element("entityPackage") == null?null:root.element("entityPackage").getTextTrim());
		config.setVoPackage(root.element("voPackage") == null?null:root.element("voPackage").getTextTrim());
		config.setDtoPackage(root.element("dtoPackage") == null?null:root.element("dtoPackage").getTextTrim());
		config.setDaoPackage(root.element("daoPackage") == null?null:root.element("daoPackage").getTextTrim());
		config.setServicePackage(root.element("servicePackage") == null?null:root.element("servicePackage").getTextTrim());
		
		config.setControllerPackage(root.element("controllerPackage") == null?null:root.element("controllerPackage").getTextTrim());
		Map<String,String> templateMap = config.getTemplateMap();
		templateMap.put("service", root.element("serviceTemplate").getTextTrim());
		templateMap.put("serviceImpl", root.element("serviceImplTemplate").getTextTrim());
		templateMap.put("controller", root.element("controllerTemplate").getTextTrim());
		templateMap.put("dao", root.element("daoTemplate").getTextTrim());
		templateMap.put("entity", root.element("entityTemplate").getTextTrim());
		templateMap.put("vo", root.element("voTemplate").getTextTrim());
		templateMap.put("dto", root.element("dtoTemplate").getTextTrim());
		templateMap.put("sql", root.element("sqlTemplate").getTextTrim());
		templateMap.put("daoSuffix", root.element("daoSuffix").getTextTrim());
		templateMap.put("showSqlFolder", root.element("showSqlFolder").getTextTrim());
		
		//else
		config.setAuthor(root.element("author").getTextTrim());
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		config.setCreateTime(sdf.format(new Date())); //设置创建时间
		
		//通过数据库表相关配置组装config对象
		if(config.getDataBaseDrive().indexOf("oracle") >= 0){
			config.setDataBaseType("oracle");
			config = ConfigUtil.buildConfigOracle(config); //oracle数据库
		}else if(config.getDataBaseDrive().indexOf("mysql") >= 0){
			config.setDataBaseType("mysql");
			config = ConfigUtil.buildConfigMysql(config); //mysql数据库
		}else{
			throw new Exception("不支持该数据库类型！");
		}
																						
		/* 初始化个性化设置，分别为参数
		Element paramsElement = root.element("params");
		if(paramsElement != null){
			List<Element> paramElementList = paramsElement.elements("param");
			if(paramElementList != null && paramElementList.size() > 0){
				//执行任务
				for(Element p : paramElementList){
					String name = p.attributeValue("name"); //参数名
					String value = p.attributeValue("value"); //参数值
					config.getParamMap().put(name, value);
				}
			}
		}
		Map configMap = config.transToMap(); */
		/* 执行个性化任务
		Element tasksElement = root.element("tasks");
		if(tasksElement != null){
			List<Element> taskElementList = tasksElement.elements("task");
			if(taskElementList != null && taskElementList.size() > 0){
				//执行任务
				for(Element t : taskElementList){
					
					String templateFileName = t.attributeValue("templateFileName"); //模板名称
					String outFileName = t.attributeValue("outFileName"); //生成文件的路径名字
					
					outFileName = TemplateUtil.getInstance().genString(configMap,outFileName);
					
					System.out.println("执行任务，模板名称：" + templateFileName + "生成文件地址：" + outFileName);
					
					TemplateUtil.getInstance().gen(configMap, templateFileName, outFileName);
				}
			}
		} */
		
		
		//生成dao相关代码
		if(config.getEntityName() !=null && config.getDaoPackage() != null){
			DaoGen.genDao(config);
			DaoGen.genModel(config);
			DaoGen.genVo(config);
			DaoGen.genDto(config);
			DaoGen.genSql(config);
		}
		
		//生成service相关代码
		if(config.getServicePackage() != null){
			ServiceGen.genService(config);
			ServiceGen.genServiceImpl(config);
		}
		
		//生成controller相关代码
		if(config.getControllerPackage() != null){
			ControllerGen.genBaseController(config);
		}
		
		
	}
}
