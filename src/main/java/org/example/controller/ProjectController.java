package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import org.example.entity.Project;
import org.example.dto.ProjectDTO;

import org.example.service.IProjectService;

/**
 * 项目表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("project")
@Api(description = "项目表相关接口")
public class ProjectController {

	private IProjectService projectService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入project")
	public R<Project> detail(ProjectDTO dto) {
		Project detail = projectService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 项目表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入project")
	public R<IPage<Project>> page(ProjectDTO dto) {
		IPage<Project> pages = projectService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 项目表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入project")
	public R<List<Project>> list(ProjectDTO dto) {
		List<Project> list = projectService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 项目表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入project")
	public R save(@RequestBody ProjectDTO dto) {
		return R.data(projectService.save(dto));
	}

	/**
	 * 修改 项目表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入project")
	public R update(@RequestBody ProjectDTO dto) {
		return R.data(projectService.updateById(dto));
	}

	/**
	 * 删除 项目表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(projectService.deleteLogic(Func.toIntList(ids)));
	}

}
