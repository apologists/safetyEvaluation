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
import org.example.entity.Case;
import org.example.dto.CaseDTO;

import org.example.service.ICaseService;

/**
 * 案例库表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("case")
@Api(description = "案例库表相关接口")
public class CaseController {

	private ICaseService caseService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入case")
	public R<Case> detail(CaseDTO dto) {
		Case detail = caseService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 案例库表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入case")
	public R<IPage<Case>> page(CaseDTO dto) {
		IPage<Case> pages = caseService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 案例库表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入case")
	public R<List<Case>> list(CaseDTO dto) {
		List<Case> list = caseService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 案例库表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入case")
	public R save(@RequestBody CaseDTO dto) {
		return R.data(caseService.save(dto));
	}

	/**
	 * 修改 案例库表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入case")
	public R update(@RequestBody CaseDTO dto) {
		return R.data(caseService.updateById(dto));
	}

	/**
	 * 删除 案例库表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(caseService.deleteLogic(Func.toIntList(ids)));
	}

}
