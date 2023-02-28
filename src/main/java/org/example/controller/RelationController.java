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
import org.example.entity.Relation;
import org.example.dto.RelationDTO;

import org.example.service.IRelationService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-20
 */
@RestController
@AllArgsConstructor
@RequestMapping("relation")
@Api(description = "相关接口")
public class RelationController {

	private IRelationService relationService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入relation")
	public R<Relation> detail(RelationDTO dto) {
		Relation detail = relationService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入relation")
	public R<IPage<Relation>> page(RelationDTO dto) {
		IPage<Relation> pages = relationService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入relation")
	public R<List<Relation>> list(RelationDTO dto) {
		List<Relation> list = relationService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入relation")
	public R save(RelationDTO dto) {
		return R.data(relationService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入relation")
	public R update(RelationDTO dto) {
		return R.data(relationService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String frequencyId) {
		return R.data(relationService.deleteLogic(Func.toIntList(frequencyId)));
	}

}
