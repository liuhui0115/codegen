package ${servicePackage}.impl;

import java.util.List;
import java.util.Date;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abel.constants.BaseConstant;
import com.abel.utils.IdUtils;
import com.cis.scp.sys.utils.UserUtil;
import ${daoPackage}.${entityName}Mapper;
import ${servicePackage}.${entityName}Service;
import ${entityPackage}.${entityName};
import ${dtoPackage}.${entityName}Dto;
import ${voPackage}.${entityName}Vo;
import com.abel.base.model.dto.QueryDto;

/**
 * 业务逻辑实现-${tableComment}
 * @author ${author}
 * @Date ${createTime}
 */
@Service("${entityName?uncap_first}Service")
@Transactional(rollbackFor = Exception.class)
public class ${entityName}ServiceImpl implements ${entityName}Service{
	/**
	 * mapper
	 * @param ${entityName?uncap_first}
	 */
	@Autowired
	private ${entityName}Mapper ${entityName?uncap_first}Mapper;
	
	/**
	 * 新增
	 * @param ${entityName?uncap_first}Dto
	 */
	@Override
	public void add(${entityName}Dto ${entityName?uncap_first}Dto){
		${entityName?uncap_first}Dto.setId(IdUtils.genIdBySnowflake());
		${entityName?uncap_first}Dto.setCreateTime(new Date());
		${entityName?uncap_first}Dto.setCreateById(UserUtil.getUserId());
		${entityName?uncap_first}Dto.setIsDeleted(BaseConstant.IS_DELETED_NO);
		${entityName?uncap_first}Mapper.add(${entityName?uncap_first}Dto);
	}
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
    @Override
	public PageInfo<${entityName}Vo> list(QueryDto<${entityName}Dto> page){
	    PageHelper.startPage(page.getPageNum(), page.getPageSize());
    	List<${entityName}Vo> list = ${entityName?uncap_first}Mapper.list(page);
    	PageInfo<${entityName}Vo> pageInfo = new PageInfo<${entityName}Vo>(list);
        return pageInfo;
    }
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
    @Override
	public ${entityName}Vo get(Long id){
		return ${entityName?uncap_first}Mapper.get(id);
	}
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}Dto
	 */
    @Override
	public void mod(${entityName}Dto ${entityName?uncap_first}Dto){
		${entityName?uncap_first}Dto.setUpdateById(UserUtil.getUserId());
		${entityName?uncap_first}Dto.setUpdateTime(new Date());
		${entityName?uncap_first}Mapper.mod(${entityName?uncap_first}Dto);
	}
	
	/**
	 * 删除
	 * @param id
	 */
    @Override
	public void del(Long id){
		${entityName?uncap_first}Mapper.del(id);
	}
	
}
