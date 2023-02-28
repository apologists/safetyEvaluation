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
import org.example.entity.Consequence;
import org.example.dto.ConsequenceDTO;

import org.example.service.IConsequenceService;

/**
 * 后果节点表 控制器
 *
 * @author AI
 * @since 2023-03-01
 */
@RestController
@AllArgsConstructor
@RequestMapping("consequence")
@Api(description = "后果节点表相关接口")
public class ConsequenceController {

	private IConsequenceService consequenceService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入consequence")
	public R<Consequence> detail(ConsequenceDTO dto) {
		Consequence detail = consequenceService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 后果节点表
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入consequence")
	public R<IPage<Consequence>> page(ConsequenceDTO dto) {
		IPage<Consequence> pages = consequenceService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 后果节点表
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入consequence")
	public R<List<Consequence>> list(ConsequenceDTO dto) {
		List<Consequence> list = consequenceService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 后果节点表
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入consequence")
	public R save(@RequestBody ConsequenceDTO dto) {
		return R.data(consequenceService.save(dto));
	}

	/**
	 * 修改 后果节点表
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入consequence")
	public R update(@RequestBody ConsequenceDTO dto) {
		return R.data(consequenceService.updateById(dto));
	}

	/**
	 * 删除 后果节点表
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(consequenceService.deleteLogic(Func.toIntList(ids)));
	}

}
