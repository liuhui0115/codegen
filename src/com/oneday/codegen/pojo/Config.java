package com.oneday.codegen.pojo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.pojo]    
 * 类名称:    [Config]  
 * 类描述:    [xml配置映射类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class Config {
	private String columns; //列以逗号分隔
	private String columnId; //列id
	private String attributes; //属性以逗号分隔
	private String attributeId; //属性id
	
	private String dataBaseDrive;
	private String dataBaseConnectStr;
	private String dataBaseName;
	private String dataBasePassword;
	private String dataBaseType;

	private String tableName;
	private String entityName;
	
	
	private String entityPackage;
	private String daoPackage;
	private String servicePackage;
	private String controllerPackage;
	
	private String author;
	private String createTime;
	
	List<Column> columnList; //字段对象列表
	private String tableComment;//表描述
	
	
	private Map<String, String> templateMap = new HashMap<String, String>();
	
	/** 个性化配置 */
	private Map<String, Object> paramMap = new HashMap<String, Object>();
	
	/**
	 * 将配置属性转换为map对象，供模板使用
	 */
	public Map<String, Object> transToMap() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		
		Field[] fields = this.getClass().getDeclaredFields();
		//System.out.println("-----------------------------字段信息输出-----------------------------");
		for(Field f : fields){
			try{
			String name = f.getName();
			
			String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
			Method m = this.getClass().getMethod(methodName);
			Object value = m.invoke(this);
			
			//System.out.println("name:"+name+",value:"+value);
			
			map.put(name, value);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		//System.out.println("------------------------------------------------------------------------");
		
		return map;
	}
	
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}
	public String getDataBaseDrive() {
		return dataBaseDrive;
	}
	public void setDataBaseDrive(String dataBaseDrive) {
		this.dataBaseDrive = dataBaseDrive;
	}
	public String getDataBaseConnectStr() {
		return dataBaseConnectStr;
	}
	public void setDataBaseConnectStr(String dataBaseConnectStr) {
		this.dataBaseConnectStr = dataBaseConnectStr;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getDataBasePassword() {
		return dataBasePassword;
	}
	public void setDataBasePassword(String dataBasePassword) {
		this.dataBasePassword = dataBasePassword;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getDaoPackage() {
		return daoPackage;
	}
	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}
	public String getControllerPackage() {
		return controllerPackage;
	}
	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	public void setColumnList(List<Column> columnList) {
		this.columnList = columnList;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

	public Map<String, String> getTemplateMap() {
		return templateMap;
	}

	public void setTemplateMap(Map<String, String> templateMap) {
		this.templateMap = templateMap;
	}


	public String getEntityPackage() {
		return entityPackage;
	}

	public void setEntityPackage(String entityPackage) {
		this.entityPackage = entityPackage;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getDataBaseType() {
		return dataBaseType;
	}

	public void setDataBaseType(String dataBaseType) {
		this.dataBaseType = dataBaseType;
	}
}
