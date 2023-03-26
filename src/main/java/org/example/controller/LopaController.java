package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.ProjectDTO;
import org.example.dto.UnitDTO;
import org.example.entity.*;
import org.example.service.IProjectService;
import org.example.service.IUnitService;
import org.example.utils.Func;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.example.dto.LopaDTO;

import org.example.service.ILopaService;

import javax.annotation.Resource;

/**
 * lopa分析 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("lopa")
@Api(description = "lopa分析相关接口")
public class LopaController {

	@Resource
	@Lazy
	private IProjectService projectService;
	@Resource
	@Lazy
	private IUnitService unitService;

	private ILopaService lopaService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入lopa")
	public R<LopaSummary> detail(LopaDTO dto) {
		LopaSummary one = lopaService.getOne(dto);
		return R.data(one);
	}

	/**
	 * 分页 lopa分析
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入lopa")
	public R<IPage<Lopa>> page(LopaDTO dto) {
		IPage<Lopa> pages = lopaService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 lopa分析
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入lopa")
	public R<List<Lopa>> list(LopaDTO dto) {
		List<Lopa> list = lopaService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 lopa分析
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入lopa")
	public R save(@RequestBody LopaDTO dto) {
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
		return R.data(lopaService.save(dto));
	}

	/**
	 * 修改 lopa分析
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入lopa")
	public R update(@RequestBody LopaDTO dto) {
		return R.data(lopaService.updateById(dto));
	}

	/**
	 * 批量修改 lopa分析
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入lopa")
	public R updateList(@RequestBody LopaSummary dto) {
		List<LopaFoot> footList = dto.getFootList();
		Map<Integer, LopaFoot> footMap = footList.stream().collect(
				Collectors.toMap(LopaFoot::getLopaId, Function.identity(), (key1, key2) -> key2));
		List<LopaHead> headList = dto.getHeadList();
		Map<Integer, LopaHead> headMap = headList.stream().collect(
				Collectors.toMap(LopaHead::getLopaId, Function.identity(), (key1, key2) -> key2));
		Set<Integer> lopaIds = footList.stream().map(LopaFoot::getLopaId).collect(Collectors.toSet());
		headList.forEach(f->{
			lopaIds.add(f.getLopaId());
		});
		if (!lopaIds.isEmpty()) {
			lopaService.deleteLogic(new ArrayList(lopaIds));
		}
		lopaIds.forEach(f->{
			LopaDTO lopaDTO = new LopaDTO()
					.setProjectId(dto.getProjectId())
					.setUnitId(dto.getUnitId());

			if(footMap.containsKey(f)){
				lopaDTO.setLopaId(footMap.get(f).getLopaId());
				lopaDTO.setLopaDesc(footMap.get(f).getLopaDesc());
				lopaDTO.setLopaGrade(footMap.get(f).getLopaGrade());
				lopaDTO.setProtect(footMap.get(f).getProtect());
				lopaDTO.setProtectDesc(footMap.get(f).getProtectDesc());
				lopaDTO.setReviseCondition(footMap.get(f).getReviseCondition());
				lopaDTO.setLopaType(footMap.get(f).getLopaType());
				lopaDTO.setConclusion(footMap.get(f).getConclusion());
				lopaDTO.setTolerate(footMap.get(f).getTolerate());
				lopaDTO.setReviseRate(footMap.get(f).getReviseRate());
			}
			if(headMap.containsKey(f)){
				lopaDTO.setLopaId(headMap.get(f).getLopaId());
				lopaDTO.setSceneDesc(headMap.get(f).getSceneDesc());
				lopaDTO.setEventVate(headMap.get(f).getEventVate());
				lopaDTO.setConditon(headMap.get(f).getConditon());
				lopaDTO.setEventIpl(headMap.get(f).getEventIpl());
				lopaDTO.setUnitId(headMap.get(f).getUnitId());
				lopaDTO.setProjectId(headMap.get(f).getProjectId());
			}
			Random random = new Random();
			int number = random.nextInt(10);
			if(number < 7){
				lopaDTO.setConclusion("SIL=1,则风险可接受");
			}else if(number == 7){
				lopaDTO.setConclusion("SIL=2,则风险可接受");
			}else if(number == 8){
				lopaDTO.setConclusion("SIL=3,则风险不可接受");
			}else {
				lopaDTO.setConclusion("SIL=4,则风险不可接受");
			}
			lopaService.save(lopaDTO);
		});
		return R.data(true);
	}

	/**
	 * 删除 lopa分析
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(lopaService.deleteLogic(Func.toIntList(ids)));
	}

}
