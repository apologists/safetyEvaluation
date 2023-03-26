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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
	public R<List<CaseSummary>> list(CaseSummaryDTO dto) {
		List<CaseSummary> list = caseSummaryService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 案例库表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入caseSummary")
	public R save(@RequestBody CaseSummaryDTO dto) {
		return R.data(caseSummaryService.save(dto));
	}

	/**
	 * 修改 案例库表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入caseSummary")
	public R update(@RequestBody CaseSummaryDTO dto) {
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
		Random random = new Random();
		double v = random.nextDouble();
		list.forEach(caseSummaryDTO -> caseSummaryService.save(caseSummaryDTO.setSimilarity(String.valueOf(v))));
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
