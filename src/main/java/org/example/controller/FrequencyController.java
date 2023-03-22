package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.LopaDTO;
import org.example.entity.Lopa;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;
import java.util.stream.Collectors;

import org.example.entity.Frequency;
import org.example.dto.FrequencyDTO;

import org.example.service.IFrequencyService;

/**
 * 频率说明表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("frequency")
@Api(description = "频率说明表相关接口")
public class FrequencyController {

	private IFrequencyService frequencyService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入frequency")
	public R<Frequency> detail(FrequencyDTO dto) {
		Frequency detail = frequencyService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 频率说明表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入frequency")
	public R<IPage<Frequency>> page(FrequencyDTO dto) {
		IPage<Frequency> pages = frequencyService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 频率说明表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入frequency")
	public R<List<Frequency>> list(FrequencyDTO dto) {
		List<Frequency> list = frequencyService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 频率说明表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入frequency")
	public R save(@RequestBody FrequencyDTO dto) {
		return R.data(frequencyService.save(dto));
	}

	/**
	 * 修改 频率说明表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入frequency")
	public R update(@RequestBody FrequencyDTO dto) {
		return R.data(frequencyService.updateById(dto));
	}


	/**
	 * 修改 频率说明表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入frequency")
	public R updateList(@RequestBody List<FrequencyDTO> list) {
		List<Frequency> oldList = frequencyService.list(new FrequencyDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		frequencyService.deleteLogic(oldList.stream().map(Frequency::getFrequencyId).collect(Collectors.toList()));
		list.forEach(frequencyDTO -> frequencyService.save(frequencyDTO));
		return R.data(true);
	}

	/**
	 * 删除 频率说明表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(frequencyService.deleteLogic(Func.toIntList(ids)));
	}

}
