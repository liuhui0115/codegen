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
	void add(${entityName} ${entityName?uncap_first});
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
	PageInfo<${entityName}Vo> list(${entityName}Dto page);
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
	${entityName}Vo get(Long id);
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
	void mod(${entityName} ${entityName?uncap_first});
	
	/**
	 * 删除
	 * @param id
	 */
	void del(Long id);
	
}
