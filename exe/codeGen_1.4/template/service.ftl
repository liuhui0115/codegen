package ${servicePackage};

import ${dtoPackage}.${entityName}Dto;
import com.github.pagehelper.PageInfo;
import com.abel.base.model.dto.QueryDto;
import ${entityPackage}.${entityName};
import ${voPackage}.${entityName}Vo;

/**
 * 业务逻辑-${tableComment}
 * @author ${author}
 * @Date ${createTime}
 */
public interface ${entityName}Service {
	/**
	 * 新增
	 * @param ${entityName?uncap_first}Dto
	 */
	void add(${entityName}Dto ${entityName?uncap_first}Dto);
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
	PageInfo<${entityName}Vo> list(QueryDto<${entityName}Dto> page);
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
	${entityName}Vo get(Long id);
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}Dto
	 */
	void mod(${entityName}Dto ${entityName?uncap_first}Dto);
	
	/**
	 * 删除
	 * @param id
	 */
	void del(Long id);
	
}
