package ${daoPackage};

import java.util.List;
import org.springframework.stereotype.Repository;
import com.abel.base.model.condition.PageQueryVo;

import ${entityPackage}.${entityName};

/**
 * 包名:     ${daoPackage}   
 * 类名:     ${entityName}Mapper 
 * 描述:     数据访问对象
 * 作者:     ${author}
 * 时间:     ${createTime}
 */
@Repository
public interface ${entityName}Mapper {
	/**
	 * 新增
	 */
	public void add(${entityName} ${entityName?uncap_first});

	/**
	 * 分页查询
	 */
	public List<${entityName}> list(PageQueryVo page);
	
	/**
	 * 详情
	 */
	public ${entityName} get(String id);
	
	/**
	 * 修改
	 */
	public void mod(${entityName} ${entityName?uncap_first});
	
	/**
	 * 删除
	 */
	public void del(String id);
	
}
