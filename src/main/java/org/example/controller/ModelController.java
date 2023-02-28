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
import org.example.entity.Model;
import org.example.dto.ModelDTO;

import org.example.service.IModelService;

/**
 * 模型 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("model")
@Api(description = "模型相关接口")
public class ModelController {

	private IModelService modelService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入model")
	public R<Model> detail(ModelDTO dto) {
		Model detail = modelService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 模型
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入model")
	public R<IPage<Model>> page(ModelDTO dto) {
		IPage<Model> pages = modelService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 模型
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入model")
	public R<List<Model>> list(ModelDTO dto) {
		List<Model> list = modelService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 模型
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入model")
	public R save(@RequestBody ModelDTO dto) {
		return R.data(modelService.save(dto));
	}

	/**
	 * 修改 模型
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入model")
	public R update(@RequestBody ModelDTO dto) {
		return R.data(modelService.updateById(dto));
	}

	/**
	 * 删除 模型
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(modelService.deleteLogic(Func.toIntList(ids)));
	}

}
