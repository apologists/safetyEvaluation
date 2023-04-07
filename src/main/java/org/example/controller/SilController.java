package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.ProjectDTO;
import org.example.dto.UnitDTO;
import org.example.dto.VariableDTO;
import org.example.entity.Project;
import org.example.entity.Unit;
import org.example.entity.Variable;
import org.example.service.IProjectService;
import org.example.service.IUnitService;
import org.example.utils.ExcelUtils;
import org.example.utils.Func;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.stream.Collectors;

import org.example.entity.Sil;
import org.example.dto.SilDTO;

import org.example.service.ISilService;

import javax.annotation.Resource;

/**
 * sil验算 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("sil")
@Api(description = "sil验算相关接口")
public class SilController {

	private ISilService silService;

	@Resource
	@Lazy
	private IProjectService projectService;
	@Resource
	@Lazy
	private IUnitService unitService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入sil")
	public R<Sil> detail(SilDTO dto) {
		Sil detail = silService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 sil验算
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入sil")
	public R<IPage<Sil>> page(SilDTO dto) {
		IPage<Sil> pages = silService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 sil验算
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入sil")
	public R<List<Sil>> list(SilDTO dto) {
		List<Sil> list = silService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 sil验算
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入sil")
	public R save(@RequestBody SilDTO dto) {
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
		return R.data(silService.save(dto));
	}

	/**
	 * 修改 sil验算q
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入sil")
	public R update(@RequestBody SilDTO dto) {
		return R.data(silService.updateById(dto));
	}

	/**
	 * 修改 变量表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入sil")
	public R updateList(@RequestBody List<SilDTO> list) {
		Map<String,List<Double>> map = new HashMap<>();
		double i2 = 0.1;
		double i3 = 0.01;
		double i4 = 0.001;
		double i5 = 0.0001;
		double i1 = 1;
		List<Double> d1 = new ArrayList<>();
		d1.add(i1);
		d1.add(i2);
		map.put("SIL=1",d1);
		List<Double> d2 = new ArrayList<>();
		d2.add(i2);
		d2.add(i3);
		map.put("SIL=2",d2);
		List<Double> d3 = new ArrayList<>();
		d3.add(i3);
		d3.add(i4);
		map.put("SIL=3",d3);
		List<Double> d4 = new ArrayList<>();
		d4.add(i4);
		d4.add(i5);
		map.put("SIL=4",d4);
		List<Sil> oldList = silService.list(new SilDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		if (!oldList.isEmpty()) {
			silService.deleteLogic(oldList.stream().map(Sil::getSilId).collect(Collectors.toList()));
		}
		List<Double> doubleList = new ArrayList<>();
		list.forEach(dto ->{
			String structure = dto.getStructure();
			double var1 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar1()));
			double var2 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar2()));
			double var3 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar3()));
			double var4 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar4()));
			double var5 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar5()));
			double var6 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar6()));
			double var7 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar7()));
			double var8 = Double.parseDouble(ExcelUtils.getBigDecimal(dto.getVar8()));
			double i = ((1 - var2) * var4 + (1 - var1) * var5) * ((1 - var2) * var4 + (1 - var1) * var5);

			double j = (var5 / var3) * (var6 / 2 + var7) + ((var4 / var3) * var7);
			double k = (var5 / var3) * (var6 / 3 + var7 + var7) + (var5 / var3) * var7;
			double n = var2 * var4 * var7 + var1 * var5 * (var6 / 2 + var7);
			Double l1;
			Double l2;
			Double l3;
			Double l4;
			if (structure.equals("2oo3")) {
				l3 = 6*((1-var2)*var4+(1-var1)*var5)*((1-var2)*var4+(1-var1)*var5)*((var5/var3)*(var7/2+var8)+(var4/var3)*var8)*((var5/var3)*(var7/3+var8)+(var5/var3)*var8)+var2*var4*var8+var1*var5*((var7/2)+var8);
				doubleList.add(l3);
			}
			if (structure.equals("1oo1")) {
				l1 = var5*(var7/2+var8)+var4*var8;
				doubleList.add(l1);
			}
			if (structure.equals("1oo2")) {
				l2 = 2*((1-var1)*var5*((1-var1)*var5)+(1-var3)*var4+var6)*((var5/var3)*(var7/2+var8)+(var4/var3)*var8)*((var5/var3)*(var7/3+var8)+(var5/var3)*var8)+var2*var4*var8+var1*var5*((var7/2)+var8);
				doubleList.add(l2);
			}
			if (structure.equals("2oo2")) {
				l4 = 2*(var5*(var7/2+var8)+(var4*var8));
				doubleList.add(l4);
			}
		});
		final Double[] count = {new Double(0)};
		doubleList.forEach(aDouble -> {
			count[0] = count[0] + aDouble;
		});
		list.forEach(silDTO -> {
			map.forEach((k,v) -> {
				if(v.get(0) >= count[0] && v.get(1) < count[0]){
					silDTO.setSilGrade(k);
				}
			});
			if(count[0]>0.1 || count[0] < 0.00001){
				silDTO.setSilGrade("A");
			}
			silDTO.setSystemPfd(String.valueOf(count[0]));
			silService.save(silDTO);
		});

		return R.data(true);
	}

	/**
	 * 删除 sil验算
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(silService.deleteLogic(Func.toIntList(ids)));
	}

}
