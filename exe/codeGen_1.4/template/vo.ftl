package ${voPackage};


import io.swagger.annotations.ApiModel;
import lombok.Data;
import com.abel.base.model.vo.IBaseVo;
import ${entityPackage}.${entityName};

/**
 * 数据展示对象 - ${tableComment}
 * @author ${author}
 * @Date ${createTime}
 */
@Data
@ApiModel("${tableComment}")
public class ${entityName}Vo extends ${entityName} implements IBaseVo{
}
