package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.entity.Options;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.stream.Collectors;

import org.example.entity.Hazop;
import org.example.dto.HazopDTO;

import org.example.service.IHazopService;

/**
 *  控制器
 *
 * @author AI
 * @since 2023-02-28
 */
@RestController
@AllArgsConstructor
@RequestMapping("hazop")
@Api(description = "相关接口")
public class HazopController {

	private IHazopService hazopService;

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
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R save(@RequestBody HazopDTO dto) {
		return R.data(hazopService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入hazop")
	public R update(@RequestBody HazopDTO dto) {
		return R.data(hazopService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(hazopService.deleteLogic(Func.toIntList(ids)));
	}


	/**
	 * 表格1
	 */
	@PostMapping("/options1")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options1(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor1));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}


	/**
	 * 表格2
	 */
	@PostMapping("/options2")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options2(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor2));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

	/**
	 * 表格3
	 */
	@PostMapping("/options3")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options3(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor1));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

	/**
	 * 表格4
	 */
	@PostMapping("/options4")
	@ApiOperation(value = "新增", notes = "传入hazop")
	public R<Options> options4(@RequestBody HazopDTO dto) {
		List<Hazop> list = hazopService.list(dto);
		Map<Integer, List<Hazop>> collect =
				list.stream().collect(Collectors.groupingBy(Hazop::getHazopColor2));
		Options options = new Options();
		List<Integer> data = new ArrayList<>();
		Set<Integer> strings = collect.keySet();
		List<String> labels = new ArrayList<>();
		options.setLabels(labels);
		if (strings.contains(1)) {
			labels.add("低");
		}
		if (strings.contains(2)) {
			labels.add("中");
		}
		if (strings.contains(3)) {
			labels.add("高");
		}
		if (strings.contains(4)) {
			labels.add("很高");
		}
		strings.forEach(x-> data.add(collect.get(x).size()));
		Collections.sort(data);
		options.setData(data);
		options.setLabels(labels);
		return R.data(options);
	}

}
