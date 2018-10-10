package com.oneday.codegen.pojo;

/**
 * 
 * 项目名称:  [codegen]
 * 包名:      [com.oneday.codegen.gen]    
 * 类名称:    [Table]  
 * 类描述:    [数据库字段映射类]
 * 创建人:    [liuhui]   
 * 创建时间:  [2018年10月09日 下午6:00:47]
 */
public class Column {
	private String Field;
	private String Type;
	private String Null;
	private String Key;
	private String Default;
	private String Extra;
	private String Attribute;
	private String Column;
	
	private int maxlength;
	private String nullAble;
	


	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}

	public int getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}


	private String Comments;

	/** 属性类型java */
	private String AttributeType;
	
	/** 属性类型mybatis */
	private String JdbcType;

	public String getField() {
		return this.Field;
	}

	public void setField(String field) {
		this.Field = field;
	}

	public String getType() {
		return this.Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	public String getNull() {
		return this.Null;
	}

	public void setNull(String null1) {
		this.Null = null1;
	}

	public String getKey() {
		return this.Key;
	}

	public void setKey(String key) {
		this.Key = key;
	}

	public String getDefault() {
		return this.Default;
	}

	public void setDefault(String default1) {
		this.Default = default1;
	}

	public String getExtra() {
		return this.Extra;
	}

	public void setExtra(String extra) {
		this.Extra = extra;
	}

	public String getAttribute() {
		if(Attribute ==null && Field != null && Field.length() > 0){
			String[] arrs = Field.split("_");
			for(String sub : arrs){
				//如果分隔字段全部为大写字母，则转换为小写，支持mysql（因为mysql区分大小写，有时可以不用转换）
				if(sub.equals(sub.toUpperCase())){
					sub = sub.toLowerCase();
				}
				
				if(Attribute == null){
					Attribute = sub;
				}else{
					Attribute = Attribute + sub.substring(0,1).toUpperCase() + sub.substring(1);
				}
			}	
		}
		return Attribute;
	}

	public String getColumn() {
		Column = Field;
		return Column;
	}

	public void setColumn(String column) {
		Column = column;
	}

	public void setAttribute(String attribute) {
		Attribute = attribute;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}

	public String getAttributeType() {
		return AttributeType;
	}

	public void setAttributeType(String attributeType) {
		AttributeType = attributeType;
	}

	public String getJdbcType() {
		switch(this.AttributeType){
			case "int": return "INTEGER";
			case "long": return "INTEGER";
			case "double": return "NUMERIC";
			case "Date": return "TIMESTAMP";
			default: return "VARCHAR";
		}
	}

	public void setJdbcType(String jdbcType) {
		
		this.JdbcType = jdbcType;
	}
}
