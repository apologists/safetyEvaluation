package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.Variable;
import org.example.dto.VariableDTO;

import org.example.service.IVariableService;

/**
 * 变量表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("variable")
@Api(description = "变量表相关接口")
public class VariableController {

	private IVariableService variableService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入variable")
	public R<Variable> detail(VariableDTO dto) {
		Variable detail = variableService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 变量表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入variable")
	public R<IPage<Variable>> page(VariableDTO dto) {
		IPage<Variable> pages = variableService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 变量表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入variable")
	public R<List<Variable>> list(VariableDTO dto) {
		List<Variable> list = variableService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 变量表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入variable")
	public R save(@RequestBody VariableDTO dto) {
		return R.data(variableService.save(dto));
	}

	/**
	 * 修改 变量表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入variable")
	public R update(@RequestBody VariableDTO dto) {
		return R.data(variableService.updateById(dto));
	}

	/**
	 * 删除 变量表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(variableService.deleteLogic(Func.toIntList(ids)));
	}

}
