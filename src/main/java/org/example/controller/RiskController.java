package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.R;
import org.example.dto.RiskDTO;
import org.example.entity.Risk;
import org.example.service.IRiskService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("risk")
@Api(description = "相关接口")
@Slf4j
public class RiskController {

	private IRiskService riskService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入risk")
	public R<Risk> detail(RiskDTO dto) {
		Risk detail = riskService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入risk")
	public R<IPage<Risk>> page(RiskDTO dto) {
		IPage<Risk> pages = riskService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入risk")
	public R<List<Risk>> list(RiskDTO dto) {
		List<Risk> list = riskService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入risk")
	public R save(RiskDTO dto) {
		return R.data(riskService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入risk")
	public R update(RiskDTO dto) {
		return R.data(riskService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "riskId")
	public R remove(String riskId) {
		return R.data(riskService.deleteLogic(Func.toIntList(riskId)));
	}


}
