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
import org.example.entity.Formula;
import org.example.dto.FormulaDTO;

import org.example.service.IFormulaService;

/**
 * 变量公式表 控制器
 *
 * @author AI
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("formula")
@Api(description = "变量公式表相关接口")
public class FormulaController {

	private IFormulaService formulaService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入formula")
	public R<Formula> detail(FormulaDTO dto) {
		Formula detail = formulaService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 变量公式表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入formula")
	public R<IPage<Formula>> page(FormulaDTO dto) {
		IPage<Formula> pages = formulaService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 变量公式表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入formula")
	public R<List<Formula>> list(FormulaDTO dto) {
		List<Formula> list = formulaService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 变量公式表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入formula")
	public R save(@RequestBody FormulaDTO dto) {
		return R.data(formulaService.save(dto));
	}

	/**
	 * 修改 变量公式表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入formula")
	public R update(@RequestBody List<FormulaDTO> dto) {
		return R.data(formulaService.updateById(dto));
	}


	/**
	 * 修改 变量公式表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入formula")
	public R updateList(@RequestBody List<FormulaDTO> dto) {
		return R.data(formulaService.updateById(dto));
	}

	/**
	 * 删除 变量公式表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(formulaService.deleteLogic(Func.toIntList(ids)));
	}

}
