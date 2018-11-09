package ${daoPackage};

import java.util.List;
import org.springframework.stereotype.Repository;
import com.abel.base.model.condition.PageQueryVo;

import ${entityPackage}.${entityName};

/**
 * 数据访问对象
 * @author ${author}
 * @Date ${createTime}
 */
@Repository
public interface ${entityName}Mapper {
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
	List<${entityName}> list(PageQueryVo page);
	
	/**
	 * 详情
     * @param id
     * @return
	 */
	${entityName} get(String id);
	
	/**
	 * 修改
	 * @param ${entityName?uncap_first}
	 */
	void mod(${entityName} ${entityName?uncap_first});
	
	/**
	 * 删除
     * @param id
	 */
	void del(String id);
	
}
