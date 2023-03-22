package org.example.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.CaseSummaryDTO;
import org.example.dto.FormulaDTO;
import org.example.entity.*;
import org.example.service.IFormulaService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.dto.VariableDTO;

import org.example.service.IVariableService;

/**
 * 变量表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("variable")
@Api(description = "变量表相关接口")
public class VariableController {

	private IVariableService variableService;

	private IFormulaService formulaService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入variable")
	public R<Variable> detail(VariableDTO dto) {
		Variable detail = variableService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 变量表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入variable")
	public R<IPage<Variable>> page(VariableDTO dto) {
		IPage<Variable> pages = variableService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 变量表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入variable")
	public R<List<Variable>> list(VariableDTO dto) {
		List<Variable> list = variableService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 变量表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入variable")
	public R save(@RequestBody VariableDTO dto) {
		return R.data(variableService.save(dto));
	}

	/**
	 * 修改 变量表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入variable")
	public R update(@RequestBody VariableDTO dto) {
		return R.data(variableService.updateById(dto));
	}


	/**
	 * 修改 变量表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入variable")
	public R updateList(@RequestBody List<VariableDTO> list) {
		List<Variable> oldList = variableService.list(new VariableDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		variableService.deleteLogic(oldList.stream().map(Variable::getVariableId).collect(Collectors.toList()));
		list.forEach(variableDTO -> variableService.save(variableDTO));
		return R.data(true);
	}

	/**
	 * 删除 变量表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(variableService.deleteLogic(Func.toIntList(ids)));
	}

	@PostMapping("/matrix")
	@ApiOperation(value = "变量关系表", notes = "传入variable")
	public VariableMatrix matrix(@RequestBody VariableDTO dto) {
		List<Formula> list = formulaService.list(new FormulaDTO()
				.setProjectId(dto.getProjectId())
				.setUnitId(dto.getUnitId())
				.setModelId(dto.getModelId())
		);
		List<String> left = list.stream().map(Formula::getFormulaLeft).collect(Collectors.toList());
		List<String> right = list.stream().map(Formula::getFormulaRight).collect(Collectors.toList());
		VariableMatrix matrix = new VariableMatrix();

		Map<String,String> variableMatrixList = new HashMap<>();
		for (int i = 0; i < left.size()+1; i++) {
			variableMatrixList.put(String.valueOf(i),left.get(i));
		}
		Map<String,String> variableMatrixData = new HashMap<>();
		for (int i = 0; i < left.size()+1; i++) {
			variableMatrixData.put(String.valueOf(i),left.get(i));
		}

//		matrix.setVariableMatrixData(variableMatrixData);
//		matrix.setVariableMatrixList(variableMatrixList);
		String json = "{\n" +
				"        \"variableMatrixList\": {\"1\": \"\", \"2\": \"F1\", \"3\": \"LIC1\", \"4\": \"L1\", \"5\": \"P1\", \"6\": \"T1\", \"7\": \"TIC2\", \"8\": \"F2\", \"9\": \"LIC2\", \"10\": \"V1\", \"11\": \"PIC1\"},\n" +
				"        \"variableMatrixData\": [\n" +
				"            {\"1\": \"F1\", \"2\": \"\", \"3\": \"\", \"4\": \"+\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"LIC1\", \"2\": \"+\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"L1\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"P1\", \"2\": \"+\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"T1\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"TIC2\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"+\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"F2\", \"2\": \"\", \"3\": \"\", \"4\": \"-\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"LIC2\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"V1\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"\",\"6\":\"\",\"7\":\"\", \"8\": \"+\", \"9\": \"\",\"10\":\"\",\"11\":\"\"},\n" +
				"            {\"1\": \"PIC1\", \"2\": \"\", \"3\": \"\", \"4\": \"\", \"5\": \"+\",\"6\":\"\",\"7\":\"\", \"8\": \"\", \"9\": \"\",\"10\":\"\",\"11\":\"\"}\n" +
				"        ]\n" +
				"    }";
		return JSON.parseObject(json, VariableMatrix.class);
	}


}
