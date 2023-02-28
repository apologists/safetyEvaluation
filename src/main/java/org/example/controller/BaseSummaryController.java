package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.entity.MatrixSummary;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.BaseSummary;
import org.example.dto.BaseSummaryDTO;

import org.example.service.IBaseSummaryService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("baseSummary")
@Api(description = "相关接口")
public class BaseSummaryController {

	private IBaseSummaryService baseSummaryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入baseSummary")
	public R<BaseSummary> detail(BaseSummaryDTO dto) {
		BaseSummary detail = baseSummaryService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入baseSummary")
	public R<IPage<BaseSummary>> page(BaseSummaryDTO dto) {
		IPage<BaseSummary> pages = baseSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入baseSummary")
	public R<BaseSummary> list(BaseSummaryDTO dto) {
		List<BaseSummary> list = baseSummaryService.list(dto);
		return R.data(list.isEmpty() ? new BaseSummary() : list.get(0));
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入baseSummary")
	public R save(BaseSummaryDTO dto) {
		return R.data(baseSummaryService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入baseSummary")
	public R update(BaseSummaryDTO dto) {
		return R.data(baseSummaryService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String baseSummaryId) {
		return R.data(baseSummaryService.deleteLogic(Func.toIntList(baseSummaryId)));
	}

}
