package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.utils.ExcelUtils;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.example.entity.Cause;
import org.example.dto.CauseDTO;

import org.example.service.ICauseService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 原因节点表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("cause")
@Api(description = "原因节点表相关接口")
public class CauseController {

	private ICauseService causeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入cause")
	public R<Cause> detail(CauseDTO dto) {
		Cause detail = causeService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 原因节点表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入cause")
	public R<IPage<Cause>> page(CauseDTO dto) {
		IPage<Cause> pages = causeService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 原因节点表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入cause")
	public R<List<Cause>> list(CauseDTO dto) {
		List<Cause> list = causeService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 原因节点表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入cause")
	public R save(@RequestBody CauseDTO dto) {
		return R.data(causeService.save(dto));
	}

	/**
	 * 修改 原因节点表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入cause")
	public R update(@RequestBody CauseDTO dto) {
		return R.data(causeService.updateById(dto));
	}

	/**
	 * 删除 原因节点表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(causeService.deleteLogic(Func.toIntList(ids)));
	}

}
