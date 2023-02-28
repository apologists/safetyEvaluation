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
import org.example.entity.SilSummary;
import org.example.dto.SilSummaryDTO;

import org.example.service.ISilSummaryService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-10-06
 */
@RestController
@AllArgsConstructor
@RequestMapping("silSummary")
@Api(description = "相关接口")
public class SilSummaryController {

	private ISilSummaryService silSummaryService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入silSummary")
	public R<SilSummary> detail(SilSummaryDTO dto) {
		SilSummary detail = silSummaryService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入silSummary")
	public R<IPage<SilSummary>> page(SilSummaryDTO dto) {
		IPage<SilSummary> pages = silSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入silSummary")
	public R<List<SilSummary>> list(SilSummaryDTO dto) {
		List<SilSummary> list = silSummaryService.list(dto);
		return R.data(list);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入silSummary")
	public R save(SilSummaryDTO dto) {
		return R.data(silSummaryService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R update(SilSummaryDTO dto) {
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 修改
	 */
	@PostMapping("/updateSil1")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R updateSil1(SilSummaryDTO dto) {
		double sum = (dto.getTi() * dto.getD1()) / 2;
		SilSummaryDTO summaryDTO = new SilSummaryDTO();
		summaryDTO.setSilSummaryId(dto.getSilSummaryId());
		SilSummary one = silSummaryService.getOne(summaryDTO);
		dto.setSilSum(sum+(one.getSilSum() == null ? 0:one.getSilSum()));
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 修改
	 */
	@PostMapping("/updateSil3")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R updateSil3(SilSummaryDTO dto) {
		double sum = ((((1-dto.getB()) * dto.getTi() * dto.getD1())*((1-dto.getB()) * dto.getTi() * dto.getD2())) / 3)
					+ ((dto.getB()* ((dto.getD1() + dto.getD2())/2) * dto.getTi()) / 2);
		SilSummaryDTO summaryDTO = new SilSummaryDTO();
		summaryDTO.setSilSummaryId(dto.getSilSummaryId());
		SilSummary one = silSummaryService.getOne(summaryDTO);
		dto.setSilSum(sum+(one.getSilSum() == null ? 0:one.getSilSum()));
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 修改
	 */
	@PostMapping("/updateSil2")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R updateSil2(SilSummaryDTO dto) {
		double sum = (dto.getTi() * dto.getDu()) / 2;
		SilSummaryDTO summaryDTO = new SilSummaryDTO();
		summaryDTO.setSilSummaryId(dto.getSilSummaryId());
		SilSummary one = silSummaryService.getOne(summaryDTO);
		dto.setSilSum(sum+(one.getSilSum() == null ? 0:one.getSilSum()));
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 修改
	 */
	@PostMapping("/updateSil4")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R updateSil4(SilSummaryDTO dto) {
		double sum = (((1-dto.getB()) * (1-dto.getB()) * dto.getDu() * dto.getDu() * dto.getTi() * dto.getTi()) / 3)
				+ (dto.getB()*dto.getDu()*dto.getTi() / 2);
		SilSummaryDTO summaryDTO = new SilSummaryDTO();
		summaryDTO.setSilSummaryId(dto.getSilSummaryId());
		SilSummary one = silSummaryService.getOne(summaryDTO);
		dto.setSilSum(sum+(one.getSilSum() == null ? 0:one.getSilSum()));
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 修改
	 */
	@PostMapping("/updateSil5")
	@ApiOperation(value = "修改", notes = "传入silSummary")
	public R updateSil5(SilSummaryDTO dto) {
		double sum = dto.getTi() * dto.getD1();
		SilSummaryDTO summaryDTO = new SilSummaryDTO();
		summaryDTO.setSilSummaryId(dto.getSilSummaryId());
		SilSummary one = silSummaryService.getOne(summaryDTO);
		dto.setSilSum(sum+(one.getSilSum() == null ? 0:one.getSilSum()));
		return R.data(silSummaryService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String silSummaryId) {
		return R.data(silSummaryService.deleteLogic(Func.toIntList(silSummaryId)));
	}

}
