package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.HazopDTO;
import org.example.service.IHazopService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.CaseSummary;
import org.example.dto.CaseSummaryDTO;

import org.example.service.ICaseSummaryService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-10-05
 */
@RestController
@AllArgsConstructor
@RequestMapping("caseSummary")
@Api(description = "相关接口")
public class CaseSummaryController {

	private ICaseSummaryService caseSummaryService;

	private IHazopService hazopService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入caseSummary")
	public R<CaseSummary> detail(CaseSummaryDTO dto) {
		CaseSummary detail = caseSummaryService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入caseSummary")
	public R<IPage<CaseSummary>> page(CaseSummaryDTO dto) {
		IPage<CaseSummary> pages = caseSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入caseSummary")
	public R<List<CaseSummary>> list(CaseSummaryDTO dto) {
		List<CaseSummary> list = caseSummaryService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入caseSummary")
	public R save(CaseSummaryDTO dto) {
		return R.data(caseSummaryService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入caseSummary")
	public R update(CaseSummaryDTO dto) {
		return R.data(caseSummaryService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String caseId) {
		return R.data(caseSummaryService.deleteLogic(Func.toIntList(caseId)));
	}

	/**
	 * 删除
	 */
	@GetMapping("/saveHazop")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R saveHazop(String caseId,Integer projectId) {
		CaseSummaryDTO dto = new CaseSummaryDTO();
		dto.setCaseId(Integer.valueOf(caseId));
		CaseSummary one = caseSummaryService.getOne(dto);
		HazopDTO hazopDTO = new HazopDTO();
		hazopDTO.setProjectId(projectId);
		hazopDTO.setAbnormalCauses(one.getAbnormalCauses());
		hazopDTO.setPullOffNode(one.getPullOffNode());
		hazopDTO.setAdverseOutComes(one.getAdverseOutComes());
		hazopDTO.setExistingMeasures(one.getExistingMeasures());
		hazopDTO.setRelationShips(one.getRelationShips());
		hazopDTO.setRiskGrade(one.getRiskGrade());
		hazopDTO.setSuggestedActions(one.getSuggestedActions());
		hazopDTO.setRiskSeverity(one.getRiskSeverity());
		return R.data(hazopService.save(hazopDTO));
	}

}
