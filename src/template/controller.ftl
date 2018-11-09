package ${controllerPackage};

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abel.base.controller.BaseController;
import com.abel.exception.ServiceException;
import com.abel.base.model.ApiResult;
import com.abel.base.model.condition.PageQueryVo;
import com.abel.constants.BaseConstant;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import ${entityPackage}.${entityName};
import ${dtoPackage}.${entityName}Dto;
import ${servicePackage}.${entityName}Service;

/**
 * 业务调用对象
 * @author ${author}
 * @Date ${createTime}
 */
@RestController
@Slf4j
@Api(tags = "${tableComment}")
@RequestMapping("/${entityName?uncap_first}")
public class ${entityName}Controller extends BaseController{
	/**
	 * service
	 */
	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;

	@ApiOperation(value = "新增", httpMethod = BaseConstant.API_POST_METHOD)
	@PostMapping(value={"/add"})
	public ApiResult<String> add(${entityName} ${entityName?uncap_first}){
		try{
			${entityName?uncap_first}Service.add(${entityName?uncap_first});
		}catch (ServiceException e) {
			log.error(e.getMessage(), e);
			return fail(e.getMessage());
		}catch (Exception e) {
			log.error("新增记录出错", e);
			return fail();
		}
		
		return success("新增成功");
	}
	
	@ApiOperation(value = "分页查询", httpMethod = BaseConstant.API_POST_METHOD)
	@RequestMapping(value = "/list")
	public ApiResult<PageInfo<${entityName}Vo>> list(${entityName}Dto page){
		PageInfo<${entityName}Vo> rePage = null;
		try{
			rePage = ${entityName?uncap_first}Service.list(page);
		}catch (ServiceException e) {
			log.error(e.getMessage(), e);
			return fail(e.getMessage());
		}catch (Exception e) {
			log.error("查询列表出错", e);
			return fail();
		}
		return success(rePage);
	}
	
	@ApiOperation(value = "详情", httpMethod = BaseConstant.API_GET_METHOD)
	@RequestMapping(value = "/get")
	public ApiResult<${entityName}Vo> get(String id){
		try {
			${entityName}Vo data = ${entityName?uncap_first}Service.get(id);
			return success(data);
		}catch (ServiceException e) {
			log.error(e.getMessage(), e);
			return fail(e.getMessage());
		}catch (Exception e) {
			log.error("获取记录出错", e);
			return fail();
		}
	}
	
	@ApiOperation(value = "修改", httpMethod = BaseConstant.API_POST_METHOD)
	@RequestMapping(value = "/mod")
	public ApiResult<String> mod(${entityName} ${entityName?uncap_first}) {
		try {
			${entityName?uncap_first}Service.mod(${entityName?uncap_first});
		}catch (ServiceException e) {
			log.error(e.getMessage(), e);
			return fail(e.getMessage());
		}catch (Exception e) {
			log.error("更新记录出错", e);
			return fail();
		}
		return success("更新成功");
	}
	
	@ApiOperation(value = "删除", httpMethod = BaseConstant.API_POST_METHOD)
	@RequestMapping(value = "/del")
	public ApiResult<String> del(String ${attributeId}) {
		try {
			${entityName?uncap_first}Service.del(${attributeId});
		}catch (ServiceException e) {
			log.error(e.getMessage(), e);
			return fail(e.getMessage());
		}catch (Exception e) {
			log.error("删除记录出错", e);
			return fail();
		}
		return success("删除成功");
	}
	
}
