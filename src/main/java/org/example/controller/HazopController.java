package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.*;
import org.example.entity.*;
import org.example.service.*;
import org.example.service.impl.UnitServiceImpl;
import org.example.utils.BeanCopyUtils;
import org.example.utils.Func;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

/**
 *  控制器
 *
 * @author AI
 * @since 2023-02-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("hazop")
@Api(description = "相关接口")
public class HazopController {

	@Resource
	@Lazy
	private IProjectService projectService;
	@Resource
	@Lazy
	private IUnitService unitService;

	private IHazopService hazopService;

	@Resource
	private IRiskGradeService gradeService;

	@Resource
	private CaseSummaryController caseSummaryService;

	@Resource
	private IModelService modelService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入hazop")
	public R<Hazop> detail(HazopDTO dto) {
		Hazop detail = hazopService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入hazop")
	public R<IPage<Hazop>> page(HazopDTO dto) {
		IPage<Hazop> pages = hazopService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入hazop")
	public R<List<Hazop>> list(HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R save(@RequestBody HazopDTO dto) {
		ProjectDTO projectDTO = new ProjectDTO();
		projectService.save(projectDTO);
		List<Project> list = projectService.list(projectDTO);
		int projectMax = 0;
		for (Project project : list) {
			projectMax = project.getProjectId() > projectMax ? project.getProjectId() : projectMax;
		}

		UnitDTO unitDTO = new UnitDTO().setProjectId(projectMax);
		unitService.save(unitDTO);
		List<Unit> UnitList = unitService.list(unitDTO);
		int unitMax = 0;
		for (Unit unit : UnitList) {
			unitMax = unit.getProjectId() > unitMax ? unit.getProjectId() : unitMax;
		}
		dto.setProjectId(projectMax);
		dto.setUnitId(unitMax);
		return R.data(hazopService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入hazop")
	public R update(@RequestBody HazopDTO dto) {
		return R.data(hazopService.updateById(dto));
	}

	/**
	 * 批量修改
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入hazop")
	public R updateList(@RequestBody List<HazopDTO> list) {
		List<Hazop> oldList = hazopService.list(new HazopDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		if (!oldList.isEmpty()) {
			hazopService.deleteLogic(oldList.stream().map(Hazop::getHazopId).collect(Collectors.toList()));
		}
		List<RiskGrade> riskGradeList = gradeService.list(new RiskGradeDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		Map<Integer, List<RiskGrade>> map = riskGradeList.stream().collect(
				Collectors.groupingBy(RiskGrade::getFrequencyNum, HashMap::new,
						Collectors.collectingAndThen(Collectors.toList(),
								c -> c.stream().sorted(Comparator.comparing(RiskGrade::getRiskConsequenceId)).collect(Collectors.toList())
						)));

		Map<Integer, Map<Integer,RiskGrade>> sortMap = new HashMap<>();
		map.forEach((integer, riskGrades) -> {
			Map<Integer,RiskGrade> riskGradeMap = riskGrades.stream().collect(Collectors.toMap(RiskGrade::getRiskConsequenceNum, Function.identity()));
			sortMap.put(integer,riskGradeMap);
		});
		List<Model> modelList = modelService.list(new ModelDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);

		list.forEach(hazopDTO ->{
			RiskGrade riskGrade1 = sortMap.get(Integer.parseInt(hazopDTO.getRiskLi())).get(Integer.parseInt(hazopDTO.getRiskSi()));
			hazopDTO.setHazopColor1(Integer.parseInt(riskGrade1.getColour()));

			RiskGrade riskGrade2 = sortMap.get(Integer.parseInt(hazopDTO.getRiskL())).get(Integer.parseInt(hazopDTO.getRiskS()));
			hazopDTO.setHazopColor2(Integer.parseInt(riskGrade2.getColour()));

			caseSummaryService.save(new CaseSummaryDTO()
					.setProjectId(hazopDTO.getProjectId())
					.setUnitId(hazopDTO.getUnitId())
					.setCause(hazopDTO.getCause())
					.setConsequence(hazopDTO.getConsequence())
					.setMeasure(hazopDTO.getMeasure())
					.setPressure(modelList.get(0).getPressure())
					.setProcessType(modelList.get(0).getProcessType())
					.setOperationProcessType(modelList.get(0).getOperationProcessType())
					.setEquipmentType(modelList.get(0).getEquipmentType())
					.setEquipmentMaterialType(modelList.get(0).getEquimentMaterialType())
					.setTemperature(modelList.get(0).getTemperature())
					.setRateFlow(modelList.get(0).getRateFlow())
					.setMatter(modelList.get(0).getMatter()));
			hazopService.save(hazopDTO);
		});

		return R.data(true);
	}

	/**
	 * 删除 
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(hazopService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 表格1
	 */
	@PostMapping("/options1")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options1(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor1));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}


	/**
	 * 表格2
	 */
	@PostMapping("/options2")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options2(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor2));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

	/**
	 * 表格3
	 */
	@PostMapping("/options3")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options3(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor1));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

	/**
	 * 表格4
	 */
	@PostMapping("/options4")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options4(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor2));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

}
