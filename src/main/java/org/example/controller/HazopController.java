package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.LopaDTO;
import org.example.entity.Lopa;
import org.example.entity.Options3;
import org.example.service.ILopaService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.example.entity.Hazop;
import org.example.dto.HazopDTO;

import org.example.service.IHazopService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("hazop")
@Api(description = "相关接口")
public class HazopController {

	private IHazopService hazopService;

	private ILopaService lopaService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入hazop")
	public R<Hazop> detail(HazopDTO dto) {
		Hazop detail = hazopService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入hazop")
	public R<IPage<Hazop>> page(HazopDTO dto) {
		IPage<Hazop> pages = hazopService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入hazop")
	public R<List<Hazop>> list(HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		return R.data(list);
	}

	/**
	 * 不分页
	 */
	@GetMapping("/options3")
	@ApiOperation(value = "不分页", notes = "传入hazop")
	public R<Options3> options3(HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Options3 options3 = new Options3();
		List<String> collect = list.stream().map(Hazop::getPullOffNode).collect(Collectors.toList());
		options3.setLabels(collect);
		List<Integer> collect1 = list.stream().map(Hazop::getHazopId).collect(Collectors.toList());
		options3.setData(collect1);
		return R.data(options3);
	}

	/**
	 * 不分页
	 */
	@GetMapping("/options2")
	@ApiOperation(value = "不分页", notes = "传入hazop")
	public R<Options3> options2(HazopDTO dto) {

		LopaDTO lopaDTO = new LopaDTO();
		lopaDTO.setProjectId(dto.getProjectId());
		List<Lopa> list = lopaService.list(lopaDTO);
		Options3 options3 = new Options3();
		List<String> collect = list.stream().distinct().map(Lopa::getSilGrade).collect(Collectors.toList());
		options3.setLabels(collect);
		Map<String, Long> collectMap = list.stream().collect(
				Collectors.groupingBy(Lopa::getSilGrade, Collectors.counting()));
		List<Integer> collect1 = new ArrayList<>();
		collect.forEach((v)->{
			collect1.add(Integer.parseInt(String.valueOf(collectMap.get(v))));
		});
		options3.setData(collect1);
		return R.data(options3);
	}

	/**
	 * 不分页
	 */
	@GetMapping("/options1")
	@ApiOperation(value = "不分页", notes = "传入hazop")
	public R<Options3> options1(HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Options3 options3 = new Options3();
		List<Integer> collect1 = new ArrayList<>();
		Integer a = list.size();
		Integer b = list.size();
		Integer c = 0;
		Integer d = 0;
		Integer e = 0;
		Integer f = 0;
		Integer g = 0;
		for (Hazop h:list) {
			if(h.getDeviation() != null) c++;
			if(h.getAbnormalCauses() != null) d++;
			if(h.getAdverseOutComes() != null) e++;
			if(h.getExistingMeasures() != null) f++;
			if(h.getSuggestedActions() != null) g++;
		}
		collect1.add(a);
		collect1.add(b);
		collect1.add(c);
		collect1.add(d);
		collect1.add(e);
		collect1.add(f);
		collect1.add(g);
		options3.setData(collect1);
		return R.data(options3);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R save(HazopDTO dto) {
		return R.data(hazopService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入hazop")
	public R update(HazopDTO dto) {
		return R.data(hazopService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String hazopId) {
		return R.data(hazopService.deleteLogic(Func.toIntList(hazopId)));
	}

}
