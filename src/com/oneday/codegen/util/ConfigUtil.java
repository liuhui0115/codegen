package com.oneday.codegen.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.oneday.codegen.pojo.Column;
import com.oneday.codegen.pojo.Config;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.gen]    
 * 类名称:    [SuperBeanHelper]  
 * 类描述:    [转换数据库字段]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class ConfigUtil<T> {
	private final Class<T> clazz;

	public ConfigUtil(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * 获取类属性名列表
	 */
	public static List<String> getFieldNameList(Object obj) {
		List<String> list = new ArrayList<String>();
		Field[] fs = obj.getClass().getDeclaredFields();
		for (Field f : fs) {
			list.add(f.getName());
		}
		return list;
	}

	/**
	 * 将数据库表的信息转换为Column对象列表（每一列对应一个Column对象）
	 */
	public static<T> List<T> convert(List<Map<String, Object>> res, Class<T> clazz) {
		List list = new ArrayList<String>();
		try {
			for (Map<String, Object> m : res) {
				Object tempClass = clazz.newInstance();
				List fieldNameList = getFieldNameList(tempClass);
				for (int i = 0; i < fieldNameList.size(); i++) {
					Field f = tempClass.getClass().getDeclaredField(
							((String) fieldNameList.get(i)).toString());
					f.setAccessible(true);
					try {
						if (f.getType().getName().equals("int")) {
							f.set(tempClass, Integer.valueOf(Integer.parseInt(m
									.get(((String) fieldNameList.get(i))
											.toString().trim()).toString())));
						} else if (f.getType().getName()
								.equals("java.util.Date")) {
							SimpleDateFormat formatter = new SimpleDateFormat(
									"yyyy-MM-dd HH:mm:ss");
							Date d = formatter.parse(m.get(
									((String) fieldNameList.get(i)).toString()
											.trim()).toString());
							f.set(tempClass, d);
						} else {
							f.set(tempClass, m.get(((String) fieldNameList
									.get(i)).toString().trim()));
						}
					} catch (Exception e) {
						//e.printStackTrace();
						//System.out.println(e.getMessage());
					}
				}
				list.add(tempClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 设置数据库相关参数（oracle数据库）
	 */
	public static Config buildConfigOracle(Config config) throws Exception{
		//配置数据库
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(config.getDataBaseDrive());
		bds.setUrl(config.getDataBaseConnectStr());
		bds.setUsername(config.getDataBaseName());
		bds.setPassword(config.getDataBasePassword());
		JdbcTemplate jdbcTemplate = new JdbcTemplate(bds);
		
		//获取字段列表
		String sql = "select  t1.COLUMN_NAME as field, t1.DATA_TYPE ||'('||t1.DATA_precision || ','||t1.DATA_SCALE||')' as type,t2.COMMENTS as comments,T1.NULLABLE NULLABLE,t1.CHAR_LENGTH MAXLENGTH"
		        + " from user_tab_columns t1 left join user_col_comments t2 on t1.TABLE_NAME = t2.TABLE_NAME and t1.COLUMN_NAME = t2.COLUMN_NAME"
				+ " where t1.TABLE_NAME = '" + config.getTableName().toUpperCase() + "'";
		
		//System.out.println("查询sql为：" + sql);
		//System.out.println("查询数据库表的列数为：" + jdbcTemplate.queryForList(sql).size());
		List<Column> columnList = ConfigUtil.convert(jdbcTemplate.queryForList(sql), Column.class);
		//设置数据库相关属性
		String columns = null;
		String attributes = null;
		
		System.out.println("name---------type-------commonts");
		for(Column c : columnList){
			//获取所有字段，逗号分隔
			columns = columns==null?c.getField():columns + "," + c.getField(); 
			//获取所有属性，逗号分隔
			attributes = attributes==null?c.getAttribute():attributes + "," + c.getAttribute();
			//设置字段描述
			c.setComments(c.getComments()==null?"请添加注释":c.getComments());
			//设置属性类型（java）
			System.out.println(c.getField() + "    " + c.getType()+ "    " + c.getComments());
			String atrributeType = c.getType().equals("NUMBER(,)") || c.getType().equals("NUMBER")? "int" : c
					.getType().indexOf("NUMBER(") == 0 && c.getType().indexOf(",0)") == 8 ? "int" : c
					.getType().indexOf("NUMBER(") == 0 && c.getType().indexOf(",0)") == 9 ? "long" : c
					.getType().substring(0, 3).equals("NUM") ? "double" : c
					.getType().equals("DATE(,)") ? "Date"
					: "String";
			c.setAttributeType(atrributeType);
			//将字段名转换为属性名，例如字段名为login_name，则属性名为loginName
			String[] fieldArr = c.getField().toLowerCase().split("_");
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < fieldArr.length; i++ ){
				if(i == 0){
					sb.append(fieldArr[i]);
				}else{
					sb.append(fieldArr[i].substring(0,1).toUpperCase() + fieldArr[i].substring(1));
				}
			}
			c.setAttribute(sb.toString());
		}
		config.setColumnId(columnList.get(0).getField()); //设置ID字段
		config.setAttributeId(columnList.get(0).getAttribute()); //设置ID属性
		config.setColumns(columns); //设置所有字段，逗号分隔
		config.setAttributes(attributes); //设置所有属性，逗号分隔
		config.setColumnList(columnList); //设置字段列表
		
		
		return config;
	}
	
	/**
	 * 设置数据库相关参数（mysql数据库）
	 */
	public static Config buildConfigMysql(Config config) throws Exception{
		//配置数据库
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(config.getDataBaseDrive());
		bds.setUrl(config.getDataBaseConnectStr());
		bds.setUsername(config.getDataBaseName());
		bds.setPassword(config.getDataBasePassword());
		JdbcTemplate jdbcTemplate = new JdbcTemplate(bds);
		
		String sqlTable = "Select TABLE_COMMENT from INFORMATION_SCHEMA.TABLES"
				+ " Where table_name = '" + config.getTableName() + "' and table_schema='"+config.getDataBaseConnectStr().substring(config.getDataBaseConnectStr().lastIndexOf("/") + 1)+"';";
		String tableComment = jdbcTemplate.queryForObject(sqlTable, String.class);
		config.setTableComment(tableComment);
		
		//获取字段列表
		//String sql = "describe " + config.getTableName();
		String sql = "Select COLUMN_NAME FIELD, COLUMN_TYPE TYPE, COLUMN_COMMENT COMMENTS,IS_NULLABLE NULLABLE,CHARACTER_MAXIMUM_LENGTH MAXLENGTH"
		+ " from INFORMATION_SCHEMA.COLUMNS"
		+ " Where table_name = '" + config.getTableName() + "' and table_schema='"+config.getDataBaseConnectStr().substring(config.getDataBaseConnectStr().lastIndexOf("/") + 1)+"';";
		
		System.out.println("查询sql为：" + sql);
		System.out.println("查询数据库表的列数为：" + jdbcTemplate.queryForList(sql).size());
		List<Column> columnList = ConfigUtil.convert(jdbcTemplate.queryForList(sql), Column.class);
		System.out.println(columnList.size());
		//设置数据库相关属性
		String columns = null;
		String attributes = null;
		for(Column c : columnList){
			//获取所有字段，逗号分隔
			columns = columns==null?c.getField():columns + "," + c.getField(); 
			//获取所有属性，逗号分隔
			attributes = attributes==null?c.getAttribute():attributes + "," + c.getAttribute();
			//System.out.println(c.getType());
			//设置属性类型（java）
			String atrributeType = c.getType().indexOf("bigint") >= 0 ? "Long" : c
					.getType().indexOf("int") >= 0 ? "Integer" : c
					.getType().indexOf("decimal") >= 0 ? "BigDecimal" : c
					.getType().indexOf("varchar") >= 0 ? "String" : c
					.getType().indexOf("date") >= 0 ? "Date" : c
					.getType().indexOf("timestamp") >= 0 ? "Date" : 
					(c.getType().length() >= 8 && c.getType().substring(0, 8).equals("datetime")) ? "Date"
					: "String";
			c.setAttributeType(atrributeType);
			if("YES".equalsIgnoreCase(c.getNullAble())){
				c.setNullAble("Y");
			}else{
				c.setNullAble("N");
			}
			
		}
		config.setColumnId(columnList.get(0).getField()); //设置ID字段
		config.setAttributeId(columnList.get(0).getAttribute()); //设置ID属性
		config.setColumns(columns); //设置所有字段，逗号分隔
		config.setAttributes(attributes); //设置所有属性，逗号分隔
		config.setColumnList(columnList); //设置字段列表
		
		
		return config;
	}
}