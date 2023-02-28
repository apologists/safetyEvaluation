package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.*;
import org.example.entity.*;
import org.example.service.IRelationService;
import org.example.service.IRiskGradeService;
import org.example.service.IRiskService;
import org.example.utils.BeanCopyUtils;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.example.service.IMatrixSummaryService;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-22
 */
@RestController
@AllArgsConstructor
@RequestMapping("matrixSummary")
@Api(description = "相关接口")
public class MatrixSummaryController {

	private IMatrixSummaryService matrixSummaryService;

	private IRiskService riskService;

	private IRelationService relationService;

	private IRiskGradeService riskGradeService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入matrixSummary")
	public R<MatrixSummary> detail(MatrixSummaryDTO dto) {
		MatrixSummary detail = matrixSummaryService.getOne(dto);
		return R.data(detail);
	}

	@GetMapping("/matrix")
	@ApiOperation(value = "不分页", notes = "matrixSummaryDTO")
	public R<Matrix> matrix(MatrixSummaryDTO dto) {
		Matrix matrix = new Matrix();
		List<Map<String, String>> list = new ArrayList<>();
		RiskDTO risk = new RiskDTO();
		RelationDTO relation = new RelationDTO();
		risk.setProjectId(dto.getProjectId());
		relation.setProjectId(dto.getProjectId());
		List<Risk> riskList = riskService.list(risk);
		List<Relation> relationList = relationService.list(relation);
		for (int i=riskList.size(); i>0; i--) {
			HashMap<String, String> map = new HashMap<>();
			map.put("0",String.valueOf(i));
			for (int j = 1; j <= relationList.size(); j++) {
				RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
				riskGradeDTO.setHorizontal(i);
				riskGradeDTO.setLongitudinal(j);
				riskGradeDTO.setProjectId(dto.getProjectId());
				RiskGrade one = riskGradeService.getOne(riskGradeDTO);
				String str = one.getRiskGrade() + "（"+ (j*i) + "）";
				map.put(String.valueOf(j),str);
			}
			list.add(map);
		}
		HashMap<String, String> matrixList = new HashMap<>();
		matrixList.put("0","");
		for (int j = 1; j <= relationList.size(); j++) {
			matrixList.put(String.valueOf(j),relationList.get(j-1).getFrequencyValue());
		}
		matrix.setMatrixList(matrixList);
		matrix.setMatrixData(list);
		return R.data(matrix);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入matrixSummary")
	public R<IPage<MatrixSummary>> page(MatrixSummaryDTO dto) {
		IPage<MatrixSummary> pages = matrixSummaryService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入matrixSummary")
	public R<MatrixSummary> list(MatrixSummaryDTO dto) {
		List<MatrixSummary> list = matrixSummaryService.list(dto);
		return R.data(list.isEmpty() ? new MatrixSummary() : list.get(0));
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入matrixSummary")
	public R save(MatrixSummaryDTO dto) {
		return R.data(matrixSummaryService.save(dto));
	}

	/**
	 * 新增
	 */
	@GetMapping("/saveMatrix")
	@ApiOperation(value = "新增", notes = "传入matrixSummary")
	public R saveMatrix(Integer matrixId,Integer projectId) {
		MatrixSummaryDTO dto = new MatrixSummaryDTO();

		dto.setMatrixId(matrixId);
		MatrixSummary one = matrixSummaryService.getOne(dto);
		Integer projectId1 = one.getProjectId();
		one.setProjectId(projectId);
		MatrixSummaryDTO copy = BeanCopyUtils.copy(one, MatrixSummaryDTO.class);
		matrixSummaryService.save2(copy);

		RiskDTO riskDTO = new RiskDTO();
		riskDTO.setProjectId(projectId1);
		List<Risk> list = riskService.list(riskDTO);
		for (Risk r:list) {
			r.setProjectId(projectId);
			riskService.save(BeanCopyUtils.copy(r, RiskDTO.class));
		}

		RelationDTO relationDTO = new RelationDTO();
		relationDTO.setProjectId(projectId1);
		List<Relation> list1 = relationService.list(relationDTO);
		for (Relation r:list1) {
			r.setProjectId(projectId);
			relationService.save(BeanCopyUtils.copy(r, RelationDTO.class));
		}

		RiskGradeDTO riskGradeDTO = new RiskGradeDTO();
		riskGradeDTO.setProjectId(projectId1);
		List<RiskGrade> list2 = riskGradeService.list(riskGradeDTO);
		riskGradeDTO.setProjectId(projectId);
		List<RiskGrade> list3 = riskGradeService.list(riskGradeDTO);
		int i = 0;
		for (RiskGrade r:list2) {
			r.setRiskGradeId(list3.get(i).getRiskGradeId());
			i++;
			r.setProjectId(projectId);
			riskGradeService.updateById(BeanCopyUtils.copy(r, RiskGradeDTO.class));
		}
		return R.data(true);
	}

	/**
	 * 修改 
	 */
	@PutMapping("/update")
	@ApiOperation(value = "修改", notes = "传入matrixSummary")
	public R update(MatrixSummaryDTO dto) {
		return R.data(matrixSummaryService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@DeleteMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(@ApiParam(value = "主键集合", required = true) @RequestParam String ids) {
		return R.data(matrixSummaryService.deleteLogic(Func.toIntList(ids)));
	}

}
