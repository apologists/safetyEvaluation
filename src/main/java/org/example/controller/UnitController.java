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
import org.example.entity.Unit;
import org.example.dto.UnitDTO;

import org.example.service.IUnitService;

/**
 * 单元表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("unit")
@Api(description = "单元表相关接口")
public class UnitController {

	private IUnitService unitService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入unit")
	public R<Unit> detail(UnitDTO dto) {
		Unit detail = unitService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 单元表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入unit")
	public R<IPage<Unit>> page(UnitDTO dto) {
		IPage<Unit> pages = unitService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 单元表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入unit")
	public R<List<Unit>> list(UnitDTO dto) {
		List<Unit> list = unitService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 单元表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入unit")
	public R save(@RequestBody UnitDTO dto) {
		return R.data(unitService.save(dto));
	}

	/**
	 * 修改 单元表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入unit")
	public R update(@RequestBody UnitDTO dto) {
		return R.data(unitService.updateById(dto));
	}

	/**
	 * 删除 单元表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(unitService.deleteLogic(Func.toIntList(ids)));
	}

}
