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
import org.example.entity.ProjectSummary;
import org.example.dto.ProjectSummaryDTO;

import org.example.service.IProjectSummaryService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("projectSummary")
@Api(description = "相关接口")
public class ProjectSummaryController {

	private IProjectSummaryService projectSummaryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入projectSummary")
	public R<ProjectSummary> detail(ProjectSummaryDTO dto) {
		ProjectSummary detail = projectSummaryService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入projectSummary")
	public R<IPage<ProjectSummary>> page(ProjectSummaryDTO dto) {
		IPage<ProjectSummary> pages = projectSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入projectSummary")
	public R<List<ProjectSummary>> list(ProjectSummaryDTO dto) {
		List<ProjectSummary> list = projectSummaryService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入projectSummary")
	public R save(ProjectSummaryDTO dto) {
		return R.data(projectSummaryService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入projectSummary")
	public R update(ProjectSummaryDTO dto) {
		return R.data(projectSummaryService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String projectId) {
		return R.data(projectSummaryService.deleteLogic(Func.toIntList(projectId)));
	}

}
