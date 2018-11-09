package ${servicePackage};

import com.abel.base.model.condition.PageQueryVo;
import com.abel.exception.ServiceException;
import com.github.pagehelper.PageInfo;

import ${entityPackage}.${entityName};

/**
 * 业务逻辑
 * @author ${author}
 * @Date ${createTime}
 */
public interface ${entityName}Service {
	/**
	 * 新增
	 */
	public void add(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception;
	
	/**
	 * 分页查询
	 */
	public PageInfo<${entityName}> list(PageQueryVo page) throws ServiceException, Exception;
	
	/**
	 * 详情
	 */
	public ${entityName} get(String id) throws ServiceException, Exception;
	
	/**
	 * 修改
	 */
	public void mod(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception;
	
	/**
	 * 删除
	 */
	public void del(String id) throws ServiceException, Exception;
	
}
