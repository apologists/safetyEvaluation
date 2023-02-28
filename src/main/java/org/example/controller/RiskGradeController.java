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
import org.example.entity.RiskGrade;
import org.example.dto.RiskGradeDTO;

import org.example.service.IRiskGradeService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-23
 */
@RestController
@AllArgsConstructor
@RequestMapping("riskGrade")
@Api(description = "相关接口")
public class RiskGradeController {

	private IRiskGradeService riskGradeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入riskGrade")
	public R<RiskGrade> detail(RiskGradeDTO dto) {
		RiskGrade detail = riskGradeService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入riskGrade")
	public R<IPage<RiskGrade>> page(RiskGradeDTO dto) {
		IPage<RiskGrade> pages = riskGradeService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入riskGrade")
	public R<List<RiskGrade>> list(RiskGradeDTO dto) {
		List<RiskGrade> list = riskGradeService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入riskGrade")
	public R save(RiskGradeDTO dto) {
		return R.data(riskGradeService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入riskGrade")
	public R update(RiskGradeDTO dto) {
		return R.data(riskGradeService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String riskGradeId) {
		return R.data(riskGradeService.deleteLogic(Func.toIntList(riskGradeId)));
	}

}
