package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.formula.functions.T;
import org.example.common.R;
import org.example.dto.ModelDTO;
import org.example.dto.ProjectDTO;
import org.example.dto.UnitDTO;
import org.example.dto.VariableDTO;
import org.example.entity.*;
import org.example.service.IModelService;
import org.example.service.IProjectService;
import org.example.service.IUnitService;
import org.example.service.IVariableService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	@Resource
	private IUnitService unitService;
	@Resource
	private IModelService modelService;
	@Resource
	private IVariableService variableService;

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

	/**
	 * 项目树
	 */
	@GetMapping("/projectSummary")
	@ApiOperation(value = "详情", notes = "传入project")
	public R<List<TreeNode>> projectSummary(ProjectDTO dto) {
		ArrayList<TreeNode> projectNodeList = new ArrayList<>();

		List<Project> list = projectService.list(dto);
		for (Project x : list) {
			TreeNode projectNode = new TreeNode();
			List<Unit> unitList = unitService.list(new UnitDTO().setProjectId(x.getProjectId()));
			List<TreeNode> unitNodeList = new ArrayList<>();
			for (Unit y : unitList) {
				List<Model> modelList = modelService.list(new ModelDTO()
						.setProjectId(y.getProjectId())
						.setUnitId(y.getUnitId()));
				List<TreeNode> modelNodeList = new ArrayList<>();
				for (Model m : modelList) {
					List<Variable> variableList = variableService.list(new VariableDTO()
							.setProjectId(dto.getProjectId())
							.setUnitId(y.getUnitId())
							.setModelId(m.getModelId()));

					List<TreeNode> variables = variableList.stream().map(variable ->
							new VariableNode()
									.setVariableId(variable.getVariableId())
									.setProjectId(x.getProjectId())
									.setUnitId(y.getUnitId())
									.setModelId(m.getModelId())
									.setId(x.getProjectId() + y.getUnitId() + m.getModelId() + "variable" + variable.getVariableId())
									.setType("variable")
									.setName(variable.getVariableNameCn())
					).collect(Collectors.toList());

					TreeNode modelNode = new ModelNode()
							.setModelId(m.getModelId())
							.setProjectId(x.getProjectId())
							.setUnitId(y.getUnitId())
							.setName(m.getModelName())
							.setId("model" + y.getUnitId() + m.getModelId())
							.setType("model")
							.setChildren(variables);
					modelNodeList.add(modelNode);
				}

				TreeNode unitNode = new UnitNode()
						.setUnitId(y.getUnitId())
						.setProjectId(x.getProjectId())
						.setName(y.getUnitName())
						.setId(dto.getProjectId()+"_unit_"+y.getUnitId())
						.setChildren(modelNodeList)
						.setType("unit");

				unitNodeList.add(unitNode);
			}

			projectNode.setId(String.valueOf(x.getProjectId()));
			projectNode.setName(x.getProjectName());
			projectNode.setChildren(unitNodeList);
			projectNode.setType("project");
			projectNodeList.add(projectNode);
		}

		return R.data(projectNodeList);
	}


}
