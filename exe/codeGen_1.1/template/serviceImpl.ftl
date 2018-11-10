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

import ${daoPackage}.${entityName}Mapper;
import ${servicePackage}.${entityName}Service;
import ${entityPackage}.${entityName};
import ${dtoPackage}.${entityName}Dto;
import ${voPackage}.${entityName}Vo;

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
	@Override
	public void add(${entityName} ${entityName?uncap_first}) throws Exception{
		String id = UUID.randomUUID().toString();
		${entityName?uncap_first}.set${attributeId?cap_first}(id);
		${entityName?uncap_first}Dao.add(${entityName?uncap_first});
	}
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
    @Override
	public PageInfo<${entityName}Vo> list(${entityName}Dto page) throws Exception{
	    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    	List<${entityName}Vo> list = ${entityName?uncap_first}Dao.list(page);
    	PageInfo<${entityName}Vo> pageInfo = new PageInfo<${entityName}Vo>(list);
        return pageInfo;
    }
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
    @Override
	public ${entityName}Vo get(String id) throws Exception{
		return ${entityName?uncap_first}Dao.get(id);
	}
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
    @Override
	public void mod(${entityName} ${entityName?uncap_first}) throws Exception{
		${entityName?uncap_first}Dao.mod(${entityName?uncap_first});
	}
	
	/**
	 * 删除
	 * @param id
	 */
    @Override
	public void del(String id) throws Exception{
		${entityName?uncap_first}Dao.del(id);
	}
	
}