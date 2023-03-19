package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.SilDTO;
import org.example.entity.*;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.dto.SdgDTO;

import org.example.service.ISdgService;

/**
 * sdg拉偏表 控制器
 *
 * @author AI
 * @since 2023-03-11
 */
@RestController
@AllArgsConstructor
@RequestMapping("sdg")
@Api(description = "sdg拉偏表相关接口")
public class SdgController {

	private ISdgService sdgService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入sdg")
	public R<SdgSummary> detail(SdgDTO dto) {
		SdgSummary one = sdgService.getOne(dto);
		return R.data(one);
	}

	/**
	 * 分页 sdg拉偏表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入sdg")
	public R<IPage<Sdg>> page(SdgDTO dto) {
		IPage<Sdg> pages = sdgService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 sdg拉偏表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入sdg")
	public R<List<Sdg>> list(SdgDTO dto) {
		List<Sdg> list = sdgService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 sdg拉偏表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R save(@RequestBody SdgDTO dto) {
		return R.data(sdgService.save(dto));
	}

	/**
	 * 修改 sdg拉偏表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入sdg")
	public R update(@RequestBody SdgDTO dto) {
		return R.data(sdgService.updateById(dto));
	}


	/**
	 * 修改 变量表
	 */
	@PostMapping("/updateList")
	@ApiOperation(value = "修改", notes = "传入sil")
	public R updateList(@RequestBody List<SdgDTO> list) {
		List<Sdg> oldList = sdgService.list(new SdgDTO()
				.setProjectId(list.get(0).getProjectId())
				.setUnitId(list.get(0).getUnitId())
		);
		sdgService.deleteLogic(oldList.stream().map(Sdg::getSdgId).collect(Collectors.toList()));
		list.forEach(sdgDTO -> sdgService.updateById(sdgDTO));
		return R.data(true);
	}

	/**
	 * 删除 sdg拉偏表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(sdgService.deleteLogic(Func.toIntList(ids)));
	}

	/**
	 * sdg图
	 */
	@GetMapping("/sdgOptions")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R<SDGOptions> sdgOptions(@RequestBody(required=false) SdgDTO dto) {
		String json = "{\n" +
				"        \"nodes\": [\n" +
				"            {\"id\": \"TIC2\", \"label\": \"TIC2\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"P1\", \"label\": \"P1\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"PIC\", \"label\": \"PIC\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F1\", \"label\": \"F1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T1\", \"label\": \"T1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"L1\", \"label\": \"L1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F2\", \"label\": \"F2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC2\", \"label\": \"LIC2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC1\", \"label\": \"LIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V1\", \"label\": \"V1\",\"borderWidth\": 2, \"shape\": \"circle\"}\n" +
				"        ],\n" +
				"        \"edges\": [\n" +
				"            {\"from\": \"TIC2\", \"to\": \"P1\",\"dashes\": false},\n" +
				"            {\"from\": \"P1\", \"to\": \"F1\", \"dashes\": false },\n" +
				"            {\"from\": \"PIC\", \"to\": \"P1\", \"dashes\": false},\n" +
				"            {\"from\": \"F1\", \"to\": \"T1\", \"dashes\": true},\n" +
				"            {\"from\": \"F1\", \"to\": \"L1\", \"dashes\": false},\n" +
				"            {\"from\": \"LIC1\", \"to\": \"F1\",\"dashes\": false},\n" +
				"            {\"from\": \"F2\", \"to\": \"L1\",\"dashes\": true},\n" +
				"            {\"from\": \"LIC2\", \"to\": \"F2\",\"dashes\": false},\n" +
				"            {\"from\": \"V1\", \"to\": \"F2\",\"dashes\": false}\n" +
				"        ]\n" +
				"    }";
		SDGOptions sdgOptions = JSON.parseObject(json, SDGOptions.class);
		return R.data(sdgOptions);
	}

	/**
	 * sdg图(原因结果)
	 */
	@GetMapping("/sdgOptionsDetail")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R<SDGOptions> sdgOptionsDetail(@RequestBody(required=false) SdgDTO dto) {

		String json = "{\n" +
				"        \"nodes\": [\n" +
				"            {\"id\": \"TIC2\", \"label\": \"TIC2\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"P1\", \"label\": \"P1\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"PIC\", \"label\": \"PIC\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F1\", \"label\": \"F1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T1\", \"label\": \"T1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"L1\", \"label\": \"L1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F2\", \"label\": \"F2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC2\", \"label\": \"LIC2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC1\", \"label\": \"LIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V1\", \"label\": \"V1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T2\", \"label\": \"T2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F5\", \"label\": \"F5\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC4\", \"label\": \"LIC4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V4\", \"label\": \"V4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F4\", \"label\": \"F4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"TIC1\", \"label\": \"TIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"OC1\", \"label\": \"OC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F3\", \"label\": \"F3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V3\", \"label\": \"V3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V2\", \"label\": \"V2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC3\", \"label\": \"LIC3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"R1\", \"label\": \"R1\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R2\", \"label\": \"R2\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R3\", \"label\": \"R3\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R4\", \"label\": \"R4\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R5\", \"label\": \"R5\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R6\", \"label\": \"R6\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R7\", \"label\": \"R7\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R8\", \"label\": \"R8\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R9\", \"label\": \"R9\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R11\", \"label\": \"R11\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R12\", \"label\": \"R12\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R13\", \"label\": \"R13\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R14\", \"label\": \"R14\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R15\", \"label\": \"R15\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R16\", \"label\": \"R16\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"R17\", \"label\": \"R17\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C1\", \"label\": \"C1\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C2\", \"label\": \"C2\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C3\", \"label\": \"C3\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C4\", \"label\": \"C4\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C5\", \"label\": \"C5\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C6\", \"label\": \"C6\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"        ],\n" +
				"        \"edges\": [\n" +
				"            {\"from\": \"TIC2\", \"to\": \"P1\",\"dashes\": false},\n" +
				"            {\"from\": \"P1\", \"to\": \"F1\", \"dashes\": false },\n" +
				"            {\"from\": \"PIC\", \"to\": \"P1\", \"dashes\": false},\n" +
				"            {\"from\": \"F1\", \"to\": \"T1\", \"dashes\": true},\n" +
				"            {\"from\": \"F1\", \"to\": \"L1\", \"dashes\": false},\n" +
				"            {\"from\": \"LIC1\", \"to\": \"F1\",\"dashes\": false},\n" +
				"            {\"from\": \"F2\", \"to\": \"L1\",\"dashes\": true},\n" +
				"            {\"from\": \"LIC2\", \"to\": \"F2\",\"dashes\": false},\n" +
				"            {\"from\": \"V1\", \"to\": \"F2\",\"dashes\": false},\n" +
				"            {\"from\": \"F2\", \"to\": \"T2\",\"dashes\": false},\n" +
				"            {\"from\": \"F4\", \"to\": \"T2\", \"dashes\": false },\n" +
				"            {\"from\": \"TIC1\", \"to\": \"T2\", \"dashes\": false},\n" +
				"            {\"from\": \"F5\", \"to\": \"T2\", \"dashes\": true},\n" +
				"            {\"from\": \"F3\", \"to\": \"T2\", \"dashes\": false},\n" +
				"            {\"from\": \"LIC4\", \"to\": \"F5\",\"dashes\": false},\n" +
				"            {\"from\": \"LIC3\", \"to\": \"F3\",\"dashes\": true},\n" +
				"            {\"from\": \"V2\", \"to\": \"F3\",\"dashes\": false},\n" +
				"            {\"from\": \"V3\", \"to\": \"F3\",\"dashes\": false},\n" +
				"            {\"from\": \"V4\", \"to\": \"F3\",\"dashes\": false},\n" +
				"            {\"from\": \"F3\", \"to\": \"OC1\",\"dashes\": false},\n" +
				"            {\"from\": \"R1\", \"to\": \"TIC2\",\"dashes\": false},\n" +
				"            {\"from\": \"R2\", \"to\": \"F1\", \"dashes\": false },\n" +
				"            {\"from\": \"R3\", \"to\": \"P1\", \"dashes\": true},\n" +
				"            {\"from\": \"R4\", \"to\": \"LIC1\", \"dashes\": false},\n" +
				"            {\"from\": \"P1\", \"to\": \"C1\", \"dashes\": false},\n" +
				"            {\"from\": \"R5\", \"to\": \"LIC1\",\"dashes\": false},\n" +
				"            {\"from\": \"R6\", \"to\": \"LIC2\",\"dashes\": false},\n" +
				"            {\"from\": \"R7\", \"to\": \"V1\",\"dashes\": false},\n" +
				"            {\"from\": \"R8\", \"to\": \"TIC1\",\"dashes\": false},\n" +
				"            {\"from\": \"R9\", \"to\": \"F5\", \"dashes\": true },\n" +
				"            {\"from\": \"R10\", \"to\": \"L1C4\", \"dashes\": false},\n" +
				"            {\"from\": \"R11\", \"to\": \"V4\", \"dashes\": false},\n" +
				"            {\"from\": \"R12\", \"to\": \"V3\",\"dashes\": false},\n" +
				"            {\"from\": \"R13\", \"to\": \"V2\", \"dashes\": false },\n" +
				"            {\"from\": \"R14\", \"to\": \"LIC3\", \"dashes\": true},\n" +
				"            {\"from\": \"R15\", \"to\": \"F3\", \"dashes\": false},\n" +
				"            {\"from\": \"R16\", \"to\": \"TIC1\",\"dashes\": false},\n" +
				"            {\"from\": \"R17\", \"to\": \"F4\", \"dashes\": false },\n" +
				"            {\"from\": \"F2\", \"to\": \"C2\",\"dashes\": false},\n" +
				"            {\"from\": \"T1\", \"to\": \"C3\",\"dashes\": false},\n" +
				"            {\"from\": \"L1\", \"to\": \"C4\",\"dashes\": false},\n" +
				"            {\"from\": \"OC1\", \"to\": \"C5\",\"dashes\": false},\n" +
				"            {\"from\": \"T2\", \"to\": \"C6\",\"dashes\": false}\n" +
				"        ]\n" +
				"    }";
		SDGOptions sdgOptions = JSON.parseObject(json, SDGOptions.class);
		return R.data(sdgOptions);
	}

}
