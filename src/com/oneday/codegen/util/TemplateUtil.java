package com.oneday.codegen.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.template]    
 * 类名称:    [TemplateBuilder]  
 * 类描述:    [代码模板工具类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class TemplateUtil {
	private static TemplateUtil tb;
	private static Configuration cfg;
	
	private String templateFileDir;
	private String outputFileDir;
    
    private TemplateUtil() throws Exception{
    	String classDir = PathUtil.getClassPath();
    	this.templateFileDir = classDir + "template";
    	this.outputFileDir = classDir + "output";
    	
		cfg = new Configuration();
    	
    	System.out.println("模板路径："+templateFileDir);
    	File dir = new File(templateFileDir);
        cfg.setDirectoryForTemplateLoading(dir);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
    	
    }
    
    public static TemplateUtil getInstance()  throws Exception{
    	if(tb == null){
    		tb = new TemplateUtil();
    	}
    	return tb;
    	
    }
    
    public void gen(Map<String, Object> data, String templateFileName, String outputFileName) throws Exception{
    	Template template = cfg.getTemplate(templateFileName);
    	String outputFileUrl = outputFileDir + "/" + outputFileName;
    	System.out.println("生成文件地址："+ outputFileUrl) ;
    	File file = new File(outputFileUrl.substring(0,outputFileUrl.lastIndexOf("/")));
    	file.mkdirs();
    	OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outputFileUrl),"UTF-8");
    	BufferedWriter writer=new BufferedWriter(write);
    	//Writer writer = new FileWriter(outputFileUrl);
    	template.process(data, writer);
    }
    
    public String genString(Map<String, Object> data, String strTemplate) throws Exception{
    	String retValue = null;
		Reader reader = new StringReader(strTemplate);
		Template objTemplate = new Template("Template", reader, new Configuration());

		Writer out = new StringWriter();
		objTemplate.process(data, out);
		out.flush();
		retValue = out.toString();
		out.close();

		return retValue;
		
    }
    
    public static void main(String[] args) throws Exception {
    	/*Map<String, Object> data = new HashMap<String, Object>();
    	Map<String, Object> user = new HashMap<String, Object>();
    	user.put("id", "1");
    	user.put("name", "oneday");
    	
    	data.put("user", user);
    	data.put("title", "test");
    	
    	tb.getInstance().gen(data, "test.ftl", "test/2.java");*/
    	
    	Map<String, Object> data = new HashMap<String, Object>();
    	Map<String, Object> user = new HashMap<String, Object>();
    	user.put("id", "1");
    	user.put("name", "oneday");
    	
    	data.put("user", user);
    	data.put("title", "test");
    	
    	
    }
}
