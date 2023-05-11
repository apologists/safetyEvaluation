package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.RiskGradeDTO;
import org.example.entity.RiskGrade;
import org.example.utils.ExcelUtils;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import org.example.entity.CaseSummary;
import org.example.dto.CaseSummaryDTO;

import org.example.service.ICaseSummaryService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 案例库表 控制器
 *
 * @author AI
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("caseSummary")
@Api(description = "案例库表相关接口")
public class CaseSummaryController {

	private ICaseSummaryService caseSummaryService;

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
	 * 分页 案例库表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入caseSummary")
	public R<IPage<CaseSummary>> page(CaseSummaryDTO dto) {
		IPage<CaseSummary> pages = caseSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 案例库表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入caseSummary")
	public R<List<CaseSummary>> list(CaseSummaryDTO dto) throws IllegalAccessException {
		dto.setProcessType(dto.getProcessType() != null ? dto.getProcessType().trim() : null);
		dto.setOperationProcessType(dto.getOperationProcessType() != null ? dto.getOperationProcessType().trim() : null);
		dto.setEquipmentType(dto.getEquipmentType() != null ? dto.getEquipmentType().trim() : null);
		dto.setEquipmentMaterialType(dto.getEquipmentMaterialType() != null ? dto.getEquipmentMaterialType().trim() : null);
		dto.setPressure(dto.getPressure() != null ? dto.getPressure().trim() : null);
		dto.setRateFlow(dto.getRateFlow() != null ? dto.getRateFlow().trim() : null);
		dto.setMatter(dto.getMatter() != null ? dto.getMatter().trim() : null);
		dto.setDeviation(dto.getDeviation() != null ? dto.getDeviation().trim() : null);
		dto.setCause(dto.getCause() != null ? dto.getCause().trim() : null);
		dto.setConsequence(dto.getConsequence() != null ? dto.getConsequence().trim() : null);
		dto.setMeasure(dto.getMeasure() != null ? dto.getMeasure().trim() : null);
		List<CaseSummary> list = caseSummaryService.list(new CaseSummaryDTO().setProjectId(dto.getProjectId()).setUnitId(dto.getUnitId()).setModelId(dto.getModelId()));
		Double rateFlowMax = null;
		if (dto.getRateFlow() != null) {
			rateFlowMax = Collections.max(list.stream().map(CaseSummary::getRateFlow).map(Double::valueOf).collect(Collectors.toList()));
		}
		Double pressureMax = null;
		if (dto.getPressure() != null) {
			pressureMax = Collections.max(list.stream().map(CaseSummary::getPressure).map(Double::valueOf).collect(Collectors.toList()));
		}
		Double temperatureMax = null;
		if (dto.getTemperature() != null) {
			temperatureMax = Collections.max(list.stream().map(CaseSummary::getTemperature).map(Double::valueOf).collect(Collectors.toList()));
		}
		if(list != null && !list.isEmpty()) {
			Double finalRateFlowMax = rateFlowMax;
			Double finalTemperatureMax = temperatureMax;
			Double finalPressureMax = pressureMax;
			list.forEach(caseSummary -> {
				Field[] fields = dto.getClass().getDeclaredFields();
				List<Double> list1 = new ArrayList<>();
				for (Field field : fields) {
					field.setAccessible(true);
					String value = null;

					String name = field.getName();
					if (name.equals("rateFlow") && dto.getRateFlow() != null) {
						try {
							value = field.get(dto).toString();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						list1.add(Double.parseDouble(dto.getRateFlowNum()) * (Double.parseDouble(caseSummary.getRateFlow()) / finalRateFlowMax));

					}
					if (name.equals("temperature") && dto.getTemperature() != null) {
						try {
							value = field.get(dto).toString();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						list1.add(Double.parseDouble(dto.getTemperatureNum()) *(Double.parseDouble(caseSummary.getTemperature()) / finalTemperatureMax));
					}
					if (name.equals("pressure") && dto.getPressure() != null) {
						try {
							value = field.get(dto).toString();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
						list1.add(Double.parseDouble(dto.getPressureNum()) * (Double.parseDouble(caseSummary.getPressure()) / finalPressureMax));
					}
//					try {
//						if (name.equals("rateFlow") && dto.getRateFlow() != null && caseSummary.getRateFlow().equals(field.get(dto).toString())) {
//							list1.add(Double.parseDouble(dto.getRateFlowNum()));
//						}
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
//					try {
//						if (name.equals("temperature") && dto.getTemperature() != null && caseSummary.getTemperature().equals(field.get(dto).toString())) {
//							list1.add(Double.parseDouble(dto.getTemperatureNum()));
//						}
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
//					try {
//						if (name.equals("pressure") && dto.getPressure() != null && caseSummary.getPressure().equals(field.get(dto).toString())) {
//							list1.add(Double.parseDouble(dto.getPressureNum()));
//						}
//					} catch (IllegalAccessException e) {
//						e.printStackTrace();
//					}
					try {
						if (name.equals("cause") && dto.getCause() != null && caseSummary.getCause().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getCauseNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("consequence") && dto.getConsequence() != null && caseSummary.getConsequence().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getConsequenceNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("deviation") &&  dto.getDeviation() != null && caseSummary.getDeviation().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getDeviationNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("equipmentMaterialType") &&  dto.getEquipmentMaterialType() != null && caseSummary.getEquipmentMaterialType().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getEquipmentMaterialTypeNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("matter") &&  dto.getMatter() != null && caseSummary.getMatter().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getMatterNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("measure") &&  dto.getMeasure() != null && caseSummary.getMeasure().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getMeasureNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("equipmentType") &&  dto.getEquipmentType() != null && caseSummary.getEquipmentType().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getEquipmentTypeNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("operationProcessType") &&  dto.getOperationProcessType() != null && caseSummary.getOperationProcessType().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getOperationProcessTypeNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					try {
						if (name.equals("processType") &&  dto.getProcessType() != null && caseSummary.getProcessType().equals(field.get(dto).toString())) {
							list1.add(Double.parseDouble(dto.getProcessTypeNum()));
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				final double[] count = {0};
				list1.forEach(x -> {
					count[0] = x + count[0];
				});
				caseSummary.setSimilarity(String.valueOf(count[0]));
			});
			list.sort(Comparator.comparing(CaseSummary::getSimilarity).reversed());
		}
		return R.data(list);
	}

	/**
	 * 新增 案例库表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入caseSummary")
	public R save(@RequestBody CaseSummaryDTO dto) {
		Random r = new Random();
		double n = 1 ;
		while(true){
			n = r.nextDouble();
			if(n < 0.7){
				continue;
			}else{
				break;
			}
		}
		double finalN = n;
		dto.setSimilarity(String.valueOf(n));
		return R.data(caseSummaryService.save(dto));
	}

	/**
	 * 修改 案例库表
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入caseSummary")
	public R update(@RequestBody CaseSummaryDTO dto) {
		caseSummaryService.updateById(dto);
		return R.data(caseSummaryService.updateById(dto));
	}


	/**
	 * 修改 案例库表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入caseSummary")
	public R updateList(@RequestBody List<CaseSummaryDTO> list) {
		List<CaseSummary> oldList = caseSummaryService.list(new CaseSummaryDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		if (!oldList.isEmpty()) {
			caseSummaryService.deleteLogic(oldList.stream().map(CaseSummary::getCaseId).collect(Collectors.toList()));
		}
		return R.data(true);
	}

	/**
	 * 删除 案例库表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(caseSummaryService.deleteLogic(Func.toIntList(ids)));
	}

	@GetMapping("/export")
	@ApiOperation(value = "案例导出")
	public void export(HttpServletResponse response) {
		// 表头数据
		List<Object> head = Arrays.asList("编号","工艺类型","操作过程类别","设备类型","设备材质类型","压力",
				"温度","流量","工艺材质","偏差","原因","结果","措施");
		// 将数据汇总
		List<List<Object>> sheetDataList = new ArrayList<>();
		List<Object> list = new ArrayList<>();
		sheetDataList.add(head);
		// 导出数据
		ExcelUtils.export(response,"案例表", sheetDataList);
	}

	@PostMapping("/import")
	@ApiOperation(value = "案例导入")
	public void importUser(@RequestPart("file") MultipartFile file) throws Exception {
		List<CaseSummaryDTO> list = ExcelUtils.readMultipartFile(file,CaseSummaryDTO.class);
		list.forEach(caseDTO -> caseSummaryService.save(caseDTO));
	}

}
