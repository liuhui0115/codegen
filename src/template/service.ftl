package ${servicePackage};

import ${dtoPackage}.${entityName}Dto;
import com.github.pagehelper.PageInfo;

import ${entityPackage}.${entityName};
import ${voPackage}.${entityName}Vo;

/**
 * 业务逻辑
 * @author ${author}
 * @Date ${createTime}
 */
public interface ${entityName}Service {
	/**
	 * 新增
	 * @param ${entityName?uncap_first}
	 */
	public void add(${entityName} ${entityName?uncap_first}) throws Exception;
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
	public PageInfo<${entityName}> list(${entityName}Dto page) throws Exception;
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
	public ${entityName} get(String id) throws Exception;
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
	public void mod(${entityName} ${entityName?uncap_first}) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 */
	public void del(String id) throws Exception;
	
}
