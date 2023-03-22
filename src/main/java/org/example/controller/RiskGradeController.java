package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.FrequencyDTO;
import org.example.dto.RiskConsequenceDTO;
import org.example.entity.Frequency;
import org.example.entity.RiskConsequence;
import org.example.entity.RiskMatrix;
import org.example.service.IFrequencyService;
import org.example.service.IRiskConsequenceService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.stream.Collectors;

import org.example.entity.RiskGrade;
import org.example.dto.RiskGradeDTO;

import org.example.service.IRiskGradeService;

import javax.annotation.Resource;

/**
 * 风险等级说明 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("riskGrade")
@Api(description = "风险等级说明相关接口")
public class RiskGradeController {

	private IRiskGradeService riskGradeService;

	@Resource
	private IRiskConsequenceService riskConsequenceService;
	@Resource
	private IFrequencyService frequencyService;

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
	 * 分页 风险等级说明
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入riskGrade")
	public R<IPage<RiskGrade>> page(RiskGradeDTO dto) {
		IPage<RiskGrade> pages = riskGradeService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 风险等级说明
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入riskGrade")
	public R<List<RiskGrade>> list(RiskGradeDTO dto) {
		List<RiskGrade> list = riskGradeService.list(dto);

		return R.data(list);
	}

	/**
	 * 新增 风险等级说明
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入riskGrade")
	public R save(@RequestBody RiskGradeDTO dto) {
		return R.data(riskGradeService.save(dto));
	}

	/**
	 * 修改 风险等级说明
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入riskGrade")
	public R update(@RequestBody RiskGradeDTO dto) {
		return R.data(riskGradeService.updateById(dto));
	}


	/**
	 * 修改 风险等级说明
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入riskGrade")
	public R updateList(@RequestBody List<RiskGradeDTO> list) {
		List<RiskGrade> oldList = riskGradeService.list(new RiskGradeDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		riskGradeService.deleteLogic(oldList.stream().map(RiskGrade::getRiskGradeId).collect(Collectors.toList()));
		list.forEach(riskGradeDto -> riskGradeService.save(riskGradeDto));
		return R.data(true);
	}

	/**
	 * 删除 风险等级说明
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(riskGradeService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 风险矩阵
	 */
	@GetMapping("/riskMatrix")
	@ApiOperation(value = "风险矩阵", notes = "传入riskGrade")
	public R<RiskMatrix> riskMatrix(RiskGradeDTO dto) {
		RiskMatrix riskMatrix = new RiskMatrix();
		List<RiskConsequence> riskConsequenceList = riskConsequenceService.list(new RiskConsequenceDTO().setProjectId(dto.getProjectId()));
		List<Frequency> frequencyList = frequencyService.list(new FrequencyDTO().setProjectId(dto.getProjectId()));
		frequencyList.sort(Comparator.comparing(Frequency::getFrequencyId));
		Map<Integer, String> collect = frequencyList.stream().collect(Collectors.toMap(Frequency::getFrequencyId, Frequency::getFrequencyName));
		collect.put(0,"");
		List<Map<Integer,RiskGrade>> data = new ArrayList<>();
		riskConsequenceList.sort(Comparator.comparing(RiskConsequence::getRiskConsequenceId));
		for (RiskConsequence x : riskConsequenceList) {
			List<RiskGrade> list = riskGradeService.list(new RiskGradeDTO()
					.setProjectId(dto.getProjectId())
					.setRiskConsequenceId(x.getRiskConsequenceId()));
			list.sort(Comparator.comparing(RiskGrade::getGradeNum));
			Map<Integer, RiskGrade> map = list.stream().collect(Collectors.toMap(RiskGrade::getFrequencyId,RiskGrade -> RiskGrade));
			RiskGrade riskGrade = list.get(0);
			riskGrade.setActionAsk("flag");
			map.put(0,riskGrade);
			data.add(map);
		}
		riskMatrix.setData(data);
		riskMatrix.setTables(collect);
		return R.data(riskMatrix);
	}

}
