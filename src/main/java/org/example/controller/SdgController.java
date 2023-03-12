package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.entity.SdgSummary;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.Sdg;
import org.example.dto.SdgDTO;

import org.example.service.ISdgService;

/**
 * sdg拉偏表 控制器
 *
 * @author AI
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("sdg")
@Api(description = "sdg拉偏表相关接口")
public class SdgController {

	private ISdgService sdgService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入sdg")
	public R<SdgSummary> detail(SdgDTO dto) {
		SdgSummary one = sdgService.getOne(dto);
		return R.data(one);
	}

	/**
	 * 分页 sdg拉偏表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入sdg")
	public R<IPage<Sdg>> page(SdgDTO dto) {
		IPage<Sdg> pages = sdgService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 sdg拉偏表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入sdg")
	public R<List<Sdg>> list(SdgDTO dto) {
		List<Sdg> list = sdgService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 sdg拉偏表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R save(@RequestBody SdgDTO dto) {
		return R.data(sdgService.save(dto));
	}

	/**
	 * 修改 sdg拉偏表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入sdg")
	public R update(@RequestBody SdgDTO dto) {
		return R.data(sdgService.updateById(dto));
	}

	/**
	 * 删除 sdg拉偏表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(sdgService.deleteLogic(Func.toIntList(ids)));
	}

}
