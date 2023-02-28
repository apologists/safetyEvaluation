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
import org.example.entity.AdverseOutComes;
import org.example.dto.AdverseOutComesDTO;

import org.example.service.IAdverseOutComesService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("adverseOutComes")
@Api(description = "相关接口")
public class AdverseOutComesController {

	private IAdverseOutComesService adverseOutComesService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入adverseOutComes")
	public R<AdverseOutComes> detail(AdverseOutComesDTO dto) {
		AdverseOutComes detail = adverseOutComesService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入adverseOutComes")
	public R<IPage<AdverseOutComes>> page(AdverseOutComesDTO dto) {
		IPage<AdverseOutComes> pages = adverseOutComesService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入adverseOutComes")
	public R<List<AdverseOutComes>> list(AdverseOutComesDTO dto) {
		List<AdverseOutComes> list = adverseOutComesService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入adverseOutComes")
	public R save(AdverseOutComesDTO dto) {
		return R.data(adverseOutComesService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入adverseOutComes")
	public R update(@RequestBody AdverseOutComesDTO dto) {
		return R.data(adverseOutComesService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String adverseOutComesId) {
		return R.data(adverseOutComesService.deleteLogic(Func.toIntList(adverseOutComesId)));
	}

}
