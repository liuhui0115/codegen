package ${dtoPackage};


import java.util.Date;
import org.apache.commons.lang.StringUtils;
import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.cis.scp.sys.dto.BasePageQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据模型
 * @author ${author}
 * @Date ${createTime}
 */
@Data
@Component
@ApiModel("${tableComment}")
public class ${entityName}Dto extends BasePageQuery{
	<#if columnList?exists> 
	<#list columnList as item> 
	
	<#if item.attributeType == 'Date'>
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	</#if>
	@ApiModelProperty("${item.comments}")
	private ${item.attributeType} ${item.attribute};
	
	</#list> 
	</#if>
}
