package org.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.example.common.R;
import org.example.dto.*;
import org.example.entity.*;
import org.example.service.*;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.Resource;

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

	@Resource
	private IFormulaService formulaService;

	@Resource
	private IVariableService variableService;

	@Resource
	private ICauseService causeService;

	@Resource
	private IConsequenceService consequenceService;
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
	public R updateList(@RequestBody SdgSummary dto) {
		List<Cause> causeList = dto.getCauseList();
		List<Consequence> consequenceList = dto.getConsequenceList();
		Set<SdgDTO> set = new HashSet<>();
		if (!causeList.isEmpty()) {
			causeList.forEach(cause -> {
				SdgDTO sdgDTO = new SdgDTO().setProjectId(cause.getProjectId())
						.setUnitId(cause.getUnitId())
						.setModelId(cause.getModelId())
						.setVariableName(cause.getVariableName())
						.setVariableNameEn(cause.getVariableNameEn())
						.setPullDirection(dto.getPullDirection());
				set.add(sdgDTO);
			});
		}

		if (!consequenceList.isEmpty()) {
			consequenceList.forEach(consequence -> {
				SdgDTO sdgDTO = new SdgDTO().setProjectId(consequence.getProjectId())
						.setUnitId(consequence.getUnitId())
						.setModelId(consequence.getModelId())
						.setVariableName(consequence.getVariableName())
						.setVariableNameEn(consequence.getVariableNameEn())
						.setPullDirection(dto.getPullDirection());
				set.add(sdgDTO);
			});
		}

//		List<Sdg> oldList = sdgService.list(new SdgDTO()
//				.setProjectId(dto.getProjectId())
//				.setUnitId(dto.getUnitId())
//				.setModelId(dto.getModelId())
//		);
//		if (!oldList.isEmpty()) {
//			sdgService.deleteLogic(oldList.stream().map(Sdg::getSdgId).collect(Collectors.toList()));
//		}
		new ArrayList<>(set).forEach(sdgDTO -> sdgService.save(sdgDTO));
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
	@PostMapping("/sdgOptions")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R<SDGOptions> sdgOptions(@RequestBody(required=false) SdgDTO dto) {

		String json = "{\n" +
				"        \"nodes\": [\n" +
				"            {\"id\": \"TIC2\", \"label\": \"TIC2\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"P1\", \"label\": \"P1\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T2\", \"label\": \"T2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F1\", \"label\": \"F1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC1\", \"label\": \"LIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"PIC1\", \"label\": \"PIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F2\", \"label\": \"F2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC2\", \"label\": \"LIC2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V1\", \"label\": \"V1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"R1\", \"label\": \"R1\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"            {\"id\": \"R2\", \"label\": \"R2\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"            {\"id\": \"R3\", \"label\": \"R3\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"            {\"id\": \"R4\", \"label\": \"R4\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"            {\"id\": \"C1\", \"label\": \"C1\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"            {\"id\": \"C2\", \"label\": \"C2\",\"borderWidth\": 2, \"shape\": \"box\"}\n" +
				"        ],\n" +
				"        \"edges\": [\n" +
				"            {\"from\": \"T2\", \"to\": \"C1\",\"dashes\": false},\n" +
				"            {\"from\": \"P1\", \"to\": \"F2\", \"dashes\": false },\n" +
				"            {\"from\": \"P1\", \"to\": \"C2\", \"dashes\": false},\n" +
				"            {\"from\": \"TIC2\", \"to\": \"P1\", \"dashes\": false},\n" +
				"            {\"from\": \"PIC1\", \"to\": \"P1\", \"dashes\": false},\n" +
				"            {\"from\": \"F1\", \"to\": \"P1\",\"dashes\": false},\n" +
				"            {\"from\": \"LIC1\", \"to\": \"F1\",\"dashes\": false},\n" +
				"            {\"from\": \"R1\", \"to\": \"LIC1\",\"dashes\": false},\n" +
				"            {\"from\": \"R2\", \"to\": \"TIC2\",\"dashes\": false}\n" +
				"            {\"from\": \"R3\", \"to\": \"LIC2\",\"dashes\": false}\n" +
				"            {\"from\": \"R4\", \"to\": \"V1\",\"dashes\": false}\n" +
				"            {\"from\": \"V1\", \"to\": \"F2\",\"dashes\": false}\n" +
				"            {\"from\": \"P1\", \"to\": \"T2\",\"dashes\": true}\n" +
				"            {\"from\": \"LIC2\", \"to\": \"F2\",\"dashes\": false}\n" +
				"        ]\n" +
				"    }";
		SDGOptions sdgOptions = JSON.parseObject(json, SDGOptions.class);
		return R.data(sdgOptions);
//		List<Formula> formulas = formulaService.list(new FormulaDTO()
//				.setProjectId(dto.getProjectId())
//				.setUnitId(dto.getUnitId())
//				.setModelId(dto.getModelId())
//		);
//		List<Variable> variableList = variableService.list(new VariableDTO()
//				.setProjectId(dto.getProjectId())
//				.setUnitId(dto.getUnitId())
//				.setModelId(dto.getModelId())
//		);
//
//		List<SDGNode> nodes = variableList.stream().map(x -> {
//			SDGNode sdgNode = new SDGNode();
//			sdgNode.setId(x.getVariableNameEn());
//			sdgNode.setLabel(x.getVariableNameEn());
//			sdgNode.setShape("circle");
//			sdgNode.setBorderWidth(2);
//			return sdgNode;
//		}).collect(Collectors.toList());
//
//		ArrayList<FormulaNode> formulaList = new ArrayList<>();
//		formulas.forEach(formula -> {
//			String formulaLeft = formula.getFormulaLeft();
//			String formulaRight = formula.getFormulaRight();
//
//			List<String> left = new ArrayList<>();
//			List<String> right = new ArrayList<>();
//			Collections.addAll(left,formulaLeft.split("-|\\+"));
//			Collections.addAll(right,formulaRight.split("-|\\+"));
//			if(left.get(0).equals("")){
//				left.remove(0);
//			}
//			if(right.get(0).equals(""))
//			{
//				right.remove(0);
//			}
//
//			for (int i = 0; i < left.size(); i++) {
//				for (int j = 0; j < right.size() ; j++) {
//					FormulaNode formulaNode = new FormulaNode();
//					formulaNode.setFormulaLeft(left.get(i));
//					formulaNode.setFormulaRight(right.get(j));
//					int index = formulaRight.indexOf(right.get(j));
//					formulaNode.setDashes(index > 0 && formulaRight.charAt(index - 1) == '-');
//					formulaList.add(formulaNode);
//				}
//			}
//		});
//		List<SDGEdges> edges = formulaList.stream().map(formula -> {
//			SDGEdges sdgEdges = new SDGEdges();
//			sdgEdges.setTo(formula.getFormulaLeft());
//			sdgEdges.setFrom(formula.getFormulaRight());
//			sdgEdges.setDashes(formula.getDashes());
//			return sdgEdges;
//		}).collect(Collectors.toList());
//		SDGOptions sdgOptions = new SDGOptions();
//		sdgOptions.setNodes(nodes);
//		sdgOptions.setEdges(edges);
//		return R.data(sdgOptions);
	}

	/**
	 * sdg图(原因结果)
	 */
	@PostMapping("/sdgOptionsDetail")
	@ApiOperation(value = "新增", notes = "传入sdg")
	public R<SDGOptions> sdgOptionsDetail(@RequestBody(required=false) SdgDTO dto) {
		R<SDGOptions> sdgOptionsR = sdgOptions(dto);
		SDGOptions data = sdgOptionsR.getData();
		List<SDGEdges> edges = data.getEdges();
		List<SDGNode> nodes = data.getNodes();


		List<Sdg> list = sdgService.list(dto);

		List<Cause> causeList = causeService.list(new CauseDTO()
				.setProjectId(dto.getProjectId())
				.setUnitId(dto.getUnitId())
				.setModelId(dto.getModelId())
		);

		List<Consequence> consequenceList = consequenceService.list(new ConsequenceDTO()
				.setProjectId(dto.getProjectId())
				.setUnitId(dto.getUnitId())
				.setModelId(dto.getModelId())
		);

		for (int i = 0; i < causeList.size(); i++) {
			Cause cause = causeList.get(i);
			SDGNode sdgNode1 = new SDGNode();
			sdgNode1.setId("S"+i);
			sdgNode1.setLabel("S"+i);
			sdgNode1.setShape("box");
			sdgNode1.setBorderWidth(2);

			SDGEdges sdgEdges1 = new SDGEdges();
			sdgEdges1.setFrom(cause.getVariableNameEn());
			sdgEdges1.setTo("S"+i);
			sdgEdges1.setDashes(cause.getStraight()==null);

			edges.add(sdgEdges1);
			nodes.add(sdgNode1);

			SDGNode sdgNode2 = new SDGNode();
			sdgNode2.setId("B"+i);
			sdgNode2.setLabel("B"+i);
			sdgNode2.setShape("box");
			sdgNode2.setBorderWidth(2);

			SDGEdges sdgEdges2 = new SDGEdges();
			sdgEdges2.setFrom(cause.getVariableNameEn());
			sdgEdges2.setTo("B"+i);
			sdgEdges2.setDashes(cause.getBurden()!=null);

			edges.add(sdgEdges2);
			nodes.add(sdgNode2);
		}


		for (int i = 0; i < consequenceList.size(); i++) {
			Cause cause = causeList.get(i);
			SDGNode sdgNode1 = new SDGNode();
			sdgNode1.setId("CS"+i);
			sdgNode1.setLabel("CS"+i);
			sdgNode1.setShape("box");
			sdgNode1.setBorderWidth(2);

			SDGEdges sdgEdges1 = new SDGEdges();
			sdgEdges1.setFrom(cause.getVariableNameEn());
			sdgEdges1.setTo("CS"+i);
			sdgEdges1.setDashes(cause.getStraight()==null);

			edges.add(sdgEdges1);
			nodes.add(sdgNode1);

			SDGNode sdgNode2 = new SDGNode();
			sdgNode2.setId("CB"+i);
			sdgNode2.setLabel("CB"+i);
			sdgNode2.setShape("box");
			sdgNode2.setBorderWidth(2);

			SDGEdges sdgEdges2 = new SDGEdges();
			sdgEdges2.setFrom(cause.getVariableNameEn());
			sdgEdges2.setTo("CB"+i);
			sdgEdges2.setDashes(cause.getBurden()!=null);

			edges.add(sdgEdges2);
			nodes.add(sdgNode2);
		}

//		causeList.forEach(cause -> {
//			SDGNode sdgNode = new SDGNode();
//			sdgNode.setId("R"+String.valueOf(cause.getCauseId()));
//			sdgNode.setLabel("R"+String.valueOf(cause.getCauseId()));
//			sdgNode.setShape("box");
//			sdgNode.setBorderWidth(2);
//
//			SDGEdges sdgEdges = new SDGEdges();
//			sdgEdges.setFrom(cause.getVariableNameEn());
//			sdgEdges.setTo("R"+String.valueOf(cause.getCauseId()));
//			sdgEdges.setDashes(cause.getBurden()==null);
//
//			edges.add(sdgEdges);
//			nodes.add(sdgNode);
//		});

//		consequenceList.forEach(consequence -> {
//			SDGNode sdgNode = new SDGNode();
//			sdgNode.setId("C"+String.valueOf(consequence.getConsequenceId()));
//			sdgNode.setLabel("C"+String.valueOf(consequence.getConsequenceId()));
//			sdgNode.setShape("box");
//			sdgNode.setBorderWidth(2);
//			SDGEdges sdgEdges = new SDGEdges();
//			sdgEdges.setTo(consequence.getVariableNameEn());
//			sdgEdges.setFrom("C"+String.valueOf(consequence.getConsequenceId()));
//			sdgEdges.setDashes(consequence.getBurden()==null);
//
//			edges.add(sdgEdges);
//			nodes.add(sdgNode);
//		});

		String json = "{\n" +
				"        \"nodes\": [\n" +
				"            {\"id\": \"P1\", \"label\": \"P1\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F1\", \"label\": \"F1\", \"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F2\", \"label\": \"F2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F3\", \"label\": \"F3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F4\", \"label\": \"F4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"F5\", \"label\": \"F5\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T1\", \"label\": \"T1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"T2\", \"label\": \"T2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V1\", \"label\": \"V1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V2\", \"label\": \"V2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V3\", \"label\": \"V3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"V4\", \"label\": \"V4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC1\", \"label\": \"LIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC2\", \"label\": \"LIC2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC3\", \"label\": \"LIC3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"LIC4\", \"label\": \"LIC4\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"TIC1\", \"label\": \"TIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"TIC2\", \"label\": \"TIC2\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"TIC3\", \"label\": \"TIC3\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
				"            {\"id\": \"PIC1\", \"label\": \"PIC1\",\"borderWidth\": 2, \"shape\": \"circle\"},\n" +
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
				"            {\"id\": \"C1\", \"label\": \"C1\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C2\", \"label\": \"C2\", \"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"            {\"id\": \"C3\", \"label\": \"C3\",\"borderWidth\": 2, \"shape\": \"box\"},\n" +
				"        ],\n" +
				"        \"edges\": [\n" +
				"            {\"from\": \"P1\", \"to\": \"F2\", \"dashes\": false },\n" +
				"            {\"from\": \"P1\", \"to\": \"T2\", \"dashes\": true },\n" +
				"            {\"from\": \"P1\", \"to\": \"C1\", \"dashes\": false},\n" +
				"            {\"from\": \"F1\", \"to\": \"P1\", \"dashes\": false},\n" +
				"            {\"from\": \"F2\", \"to\": \"T1\", \"dashes\": false},\n" +
				"            {\"from\": \"F3\", \"to\": \"T1\",\"dashes\": false},\n" +
				"            {\"from\": \"F4\", \"to\": \"T1\",\"dashes\": false},\n" +
				"            {\"from\": \"F5\", \"to\": \"T1\",\"dashes\": true},\n" +
				"            {\"from\": \"T1\", \"to\": \"C2\",\"dashes\": false},\n" +
				"            {\"from\": \"T2\", \"to\": \"C3\",\"dashes\": false},\n" +
				"            {\"from\": \"V1\", \"to\": \"F2\", \"dashes\": false },\n" +
				"            {\"from\": \"V2\", \"to\": \"F3\", \"dashes\": false},\n" +
				"            {\"from\": \"V3\", \"to\": \"F3\", \"dashes\": false},\n" +
				"            {\"from\": \"V4\", \"to\": \"F3\", \"dashes\": false},\n" +
				"            {\"from\": \"LIC1\", \"to\": \"F1\",\"dashes\": false},\n" +
				"            {\"from\": \"LIC2\", \"to\": \"F2\",\"dashes\": false},\n" +
				"            {\"from\": \"LIC3\", \"to\": \"F3\",\"dashes\": false},\n" +
				"            {\"from\": \"LIC4\", \"to\": \"F5\",\"dashes\": false},\n" +
				"            {\"from\": \"TIC1\", \"to\": \"F4\",\"dashes\": false},\n" +
				"            {\"from\": \"TIC2\", \"to\": \"P1\",\"dashes\": false},\n" +
				"            {\"from\": \"TIC3\", \"to\": \"T1\",\"dashes\": true},\n" +
				"            {\"from\": \"PIC1\", \"to\": \"P1\", \"dashes\": false },\n" +
				"            {\"from\": \"R1\", \"to\": \"LIC2\", \"dashes\": false},\n" +
				"            {\"from\": \"R2\", \"to\": \"TIC2\",\"dashes\": false},\n" +
				"            {\"from\": \"R3\", \"to\": \"V1\", \"dashes\": false },\n" +
				"            {\"from\": \"R4\", \"to\": \"PIC1\", \"dashes\": false},\n" +
				"            {\"from\": \"R5\", \"to\": \"LIC1\", \"dashes\": false},\n" +
				"            {\"from\": \"R6\", \"to\": \"TIC1\", \"dashes\": false},\n" +
				"            {\"from\": \"R7\", \"to\": \"TIC3\",\"dashes\": false},\n" +
				"            {\"from\": \"R8\", \"to\": \"F5\",\"dashes\": false},\n" +
				"            {\"from\": \"R9\", \"to\": \"LIC4\",\"dashes\": false},\n" +
				"            {\"from\": \"R10\", \"to\": \"V4\",\"dashes\": false},\n" +
				"            {\"from\": \"R11\", \"to\": \"V3\", \"dashes\": false },\n" +
				"            {\"from\": \"R12\", \"to\": \"F3\", \"dashes\": false},\n" +
				"            {\"from\": \"R13\", \"to\": \"LIC3\", \"dashes\": false},\n" +
				"            {\"from\": \"R14\", \"to\": \"V2\",\"dashes\": false},\n" +
				"        ]\n" +
				"    }";
		SDGOptions sdgOptions = JSON.parseObject(json, SDGOptions.class);
		return R.data(sdgOptions);
	}
}
