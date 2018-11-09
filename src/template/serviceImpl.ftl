package ${servicePackage}.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abel.exception.ServiceException;
import com.abel.base.model.condition.PageQueryVo;

import ${daoPackage}.${entityName}Mapper;
import ${servicePackage}.${entityName}Service;
import ${entityPackage}.${entityName};

/**
 * 业务逻辑实现
 * @author ${author}
 * @Date ${createTime}
 */
@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl implements ${entityName}Service{
	/**
	 * dao
	 * @param ${entityName?uncap_first}
	 */
	@Autowired
	private ${entityName}Mapper ${entityName?uncap_first}Dao;
	
	/**
	 * 新增
	 * @param ${entityName?uncap_first}
	 */
	public void add(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception{
		String id = UUID.randomUUID().toString();
		${entityName?uncap_first}.set${attributeId?cap_first}(id);
		${entityName?uncap_first}Dao.add(${entityName?uncap_first});
	}
	
	/**
	 * 分页查询
	 * @param page
	 */
	public PageInfo<${entityName}> list(PageQueryVo page) throws ServiceException, Exception{
	    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    	List<${entityName}> list = ${entityName?uncap_first}Dao.list(page);
    	PageInfo<${entityName}> pageInfo = new PageInfo<${entityName}>(list);
        return pageInfo;
    }
	
	/**
	 * 详情
	 * @param id
	 */
	public ${entityName} get(String id) throws ServiceException, Exception{
		return ${entityName?uncap_first}Dao.get(id);
	}
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
	public void mod(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception{
		${entityName?uncap_first}Dao.mod(${entityName?uncap_first});
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void del(String id) throws ServiceException, Exception{
		${entityName?uncap_first}Dao.del(id);
	}
	
}
