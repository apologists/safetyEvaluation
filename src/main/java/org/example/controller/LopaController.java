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
import org.example.entity.Lopa;
import org.example.dto.LopaDTO;

import org.example.service.ILopaService;

/**
 * lopa分析 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("lopa")
@Api(description = "lopa分析相关接口")
public class LopaController {

	private ILopaService lopaService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入lopa")
	public R<Lopa> detail(LopaDTO dto) {
		Lopa detail = lopaService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 lopa分析
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入lopa")
	public R<IPage<Lopa>> page(LopaDTO dto) {
		IPage<Lopa> pages = lopaService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 lopa分析
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入lopa")
	public R<List<Lopa>> list(LopaDTO dto) {
		List<Lopa> list = lopaService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 lopa分析
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入lopa")
	public R save(@RequestBody LopaDTO dto) {
		return R.data(lopaService.save(dto));
	}

	/**
	 * 修改 lopa分析
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入lopa")
	public R update(@RequestBody LopaDTO dto) {
		return R.data(lopaService.updateById(dto));
	}

	/**
	 * 删除 lopa分析
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(lopaService.deleteLogic(Func.toIntList(ids)));
	}

}
