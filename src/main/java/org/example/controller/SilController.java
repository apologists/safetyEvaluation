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
import org.example.utils.Func;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
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
		List<Sil> oldList = silService.list(new SilDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		silService.deleteLogic(oldList.stream().map(Sil::getSilId).collect(Collectors.toList()));
		list.forEach(silDTO -> silService.save(silDTO));
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
