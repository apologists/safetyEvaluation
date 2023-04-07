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

import java.util.*;
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
		if (!oldList.isEmpty()) {
			variableService.deleteLogic(oldList.stream().map(Variable::getVariableId).collect(Collectors.toList()));
		}
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
	public R<VariableMatrix> matrix(@RequestBody VariableDTO dto) {
		List<Formula> list = formulaService.list(new FormulaDTO()
				.setProjectId(dto.getProjectId())
				.setUnitId(dto.getUnitId())
				.setModelId(dto.getModelId())
		);

		VariableMatrix matrix = new VariableMatrix();

		Map<String,String> variableMatrixList = new HashMap<>();
		List<Map<String,String>> variableMatrixData = new ArrayList<>();
		final int[] i = {1};
		variableMatrixList.put(String.valueOf(0),"");
		list.forEach(formula -> {
			Map<String,List<String>> matrixMap = new HashMap<>();
			List<String> left = new ArrayList<>();
			List<String> right = new ArrayList<>();
			Collections.addAll(left,formula.getFormulaLeft().split("-|\\+"));
			Collections.addAll(right,formula.getFormulaRight().split("-|\\+"));
			if(left.get(0).equals("")){
				left.remove(0);
			}
			if(right.get(0).equals(""))
			{
				right.remove(0);
			}
			left.forEach(r->{
				matrixMap.put(r,right);
			});
			for (int j = 0; j < left.size(); j++) {
				variableMatrixList.put(String.valueOf(i[0]),left.get(j));
				i[0]++;
			}

			for (int j = 0; j < right.size(); j++) {
				Map<String,String> map = new HashMap<>();
				String s = right.get(j);
				int index = formula.getFormulaRight().indexOf(s);
				boolean f = index > 0 && formula.getFormulaRight().charAt(index - 1) == '-';
				boolean t = (index == 0 || index > 0 && formula.getFormulaRight().charAt(index - 1) == '+');
				variableMatrixList.forEach((k,v) ->{
					List<String> strings = matrixMap.get(v);

					if(k.equals("0")){
						map.put(k,s);
					} else if(strings != null&&strings.contains(s)){
						if(f){
							map.put(k,"-");
						}
						if(t){
							map.put(k,"+");
						}
					}else {
						map.put(k,"");
					}
				});
				variableMatrixData.add(map);
			}

		});

		matrix.setVariableMatrixData(variableMatrixData);
		matrix.setVariableMatrixList(variableMatrixList);
		return R.data(matrix);
	}


}
