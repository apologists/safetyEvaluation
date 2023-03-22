package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.FrequencyDTO;
import org.example.entity.Frequency;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.stream.Collectors;

import org.example.entity.RiskConsequence;
import org.example.dto.RiskConsequenceDTO;

import org.example.service.IRiskConsequenceService;

/**
 * 风险后果说明表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("riskConsequence")
@Api(description = "风险后果说明表相关接口")
public class RiskConsequenceController {

	private IRiskConsequenceService riskConsequenceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入riskConsequence")
	public R<RiskConsequence> detail(RiskConsequenceDTO dto) {
		RiskConsequence detail = riskConsequenceService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 风险后果说明表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入riskConsequence")
	public R<IPage<RiskConsequence>> page(RiskConsequenceDTO dto) {
		IPage<RiskConsequence> pages = riskConsequenceService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 风险后果说明表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入riskConsequence")
	public R<List<RiskConsequence>> list(RiskConsequenceDTO dto) {
		List<RiskConsequence> list = riskConsequenceService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 风险后果说明表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入riskConsequence")
	public R save(@RequestBody RiskConsequenceDTO dto) {
		return R.data(riskConsequenceService.save(dto));
	}

	/**
	 * 修改 风险后果说明表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入riskConsequence")
	public R update(@RequestBody RiskConsequenceDTO dto) {
		return R.data(riskConsequenceService.updateById(dto));
	}


	/**
	 * 修改 风险后果说明表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入riskConsequence")
	public R updateList(@RequestBody List<RiskConsequenceDTO> list) {
		List<RiskConsequence> oldList = riskConsequenceService.list(new RiskConsequenceDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		riskConsequenceService.deleteLogic(oldList.stream().map(RiskConsequence::getRiskConsequenceId).collect(Collectors.toList()));
		list.forEach(riskConsequenceDTO -> riskConsequenceService.save(riskConsequenceDTO));
		return R.data(true);
	}


	/**
	 * 删除 风险后果说明表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(riskConsequenceService.deleteLogic(Func.toIntList(ids)));
	}

}
