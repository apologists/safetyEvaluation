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
import org.example.entity.AbnormalCauses;
import org.example.dto.AbnormalCausesDTO;

import org.example.service.IAbnormalCausesService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("abnormalCauses")
@Api(description = "相关接口")
public class AbnormalCausesController {

	private IAbnormalCausesService abnormalCausesService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入abnormalCauses")
	public R<AbnormalCauses> detail(AbnormalCausesDTO dto) {
		AbnormalCauses detail = abnormalCausesService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入abnormalCauses")
	public R<IPage<AbnormalCauses>> page(AbnormalCausesDTO dto) {
		IPage<AbnormalCauses> pages = abnormalCausesService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入abnormalCauses")
	public R<List<AbnormalCauses>> list(AbnormalCausesDTO dto) {
		List<AbnormalCauses> list = abnormalCausesService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入abnormalCauses")
	public R save( AbnormalCausesDTO dto) {
		return R.data(abnormalCausesService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入abnormalCauses")
	public R update(AbnormalCausesDTO dto) {
		return R.data(abnormalCausesService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String abnormalCausesId) {
		return R.data(abnormalCausesService.deleteLogic(Func.toIntList(abnormalCausesId)));
	}

}
