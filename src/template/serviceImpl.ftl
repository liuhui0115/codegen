package ${servicePackage}.impl;

import java.util.List;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cis.scp.sys.constant.SystemConstant;
import com.abel.utils.GenId;
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
		${entityName?uncap_first}.setId(GenId.id(${entityName}.class));
		${entityName?uncap_first}.setCreateTime(new Date());
		${entityName?uncap_first}.setCreateById(0L);
		${entityName?uncap_first}.setIsDeleted(SystemConstant.IS_DELETED_NO);
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
	public ${entityName}Vo get(Long id) throws Exception{
		return ${entityName?uncap_first}Dao.get(id);
	}
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
    @Override
	public void mod(${entityName} ${entityName?uncap_first}) throws Exception{
		${entityName?uncap_first}.setUpdateById(0L);
		${entityName?uncap_first}.setUpdateTime(new Date());
		${entityName?uncap_first}Dao.mod(${entityName?uncap_first});
	}
	
	/**
	 * 删除
	 * @param id
	 */
    @Override
	public void del(Long id) throws Exception{
		${entityName?uncap_first}Dao.del(id);
	}
	
}
