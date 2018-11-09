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
	 * @param ${entityName?uncap_first}
	 */
	public void add(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception;
	
	/**
	 * 分页查询
	 * @param page
     * @return
	 */
	public PageInfo<${entityName}> list(PageQueryVo page) throws ServiceException, Exception;
	
	/**
	 * 详情
	 * @param id
     * @return
	 */
	public ${entityName} get(String id) throws ServiceException, Exception;
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
	public void mod(${entityName} ${entityName?uncap_first}) throws ServiceException, Exception;
	
	/**
	 * 删除
	 * @param id
	 */
	public void del(String id) throws ServiceException, Exception;
	
}
