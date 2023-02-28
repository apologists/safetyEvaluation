package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.common.R;
import org.example.dto.AbnormalCausesDTO;
import org.example.dto.AdverseOutComesDTO;
import org.example.dto.FormulaDTO;
import org.example.entity.*;
import org.example.service.IAbnormalCausesService;
import org.example.service.IAdverseOutComesService;
import org.example.service.IFormulaService;
import org.example.utils.Func;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.dto.VariableDTO;

import org.example.service.IVariableService;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  控制器
 *
 * @author AI
 * @since 2022-08-25
 */
@RestController
@AllArgsConstructor
@RequestMapping("variable")
@Api(description = "相关接口")
public class VariableController {

	private IVariableService variableService;

	private IFormulaService formulaService;

	private IAdverseOutComesService adverseOutComesService;

	private IAbnormalCausesService abnormalCausesService;

	/**
	 * 详情
	 */
	@GetMapping("/detail")
	@ApiOperation(value = "详情", notes = "传入variable")
	public R<Variable> detail(VariableDTO dto) {
		Variable detail = variableService.getOne(dto);
		return R.data(detail);
	}

	/**
	 * 分页 
	 */
	@GetMapping("/page")
	@ApiOperation(value = "分页", notes = "传入variable")
	public R<IPage<Variable>> page(VariableDTO dto) {
		IPage<Variable> pages = variableService.page(dto);
		return R.data(pages);
	}
	/**
	 * 不分页 
	 */
	@GetMapping("/list")
	@ApiOperation(value = "不分页", notes = "传入variable")
	public R<VariableMatrix> list(VariableDTO dto) {
		FormulaDTO formulaDTO = new FormulaDTO();
		formulaDTO.setProjectId(dto.getProjectId());
		List<Formula> formulaList = formulaService.list(formulaDTO);
		VariableMatrix variableMatrix = new VariableMatrix();
		HashMap<String, String> variableMatrixList = new HashMap<>();
		List<Map<String, String>> list = new ArrayList<>();
		variableMatrixList.put("0","");
		String str = formulaList.stream().distinct().map(Formula::getFormulaFrom).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining());
		String[] formulaFromStr = str.split("[+\\-]");
		Arrays.sort(formulaFromStr);
		for (int j = 1; j < formulaFromStr.length; j++) {
//			Collection<String> values = variableMatrixList.values();
//			if (!values.contains(formulaFromStr[j])) {
//				variableMatrixList.put(String.valueOf(j),formulaFromStr[j]);
//			}
			variableMatrixList.put(String.valueOf(j),formulaFromStr[j]);
		}

		Map<String, String> formulaMap = formulaList.stream().collect(Collectors.toMap(Formula::getFormulaTo, Formula::getFormulaFrom, (key1, key2) -> key1+key2));
		formulaMap.forEach((k,v) -> {
			HashMap<String, String> map = new HashMap<>();
			map.put("0", k);
			String[] split = v.split("[+\\-]");
			for (int j = 1; j < split.length; j++) {
				int index = v.indexOf(split[j]);
				String x = split[j];
				System.out.println(Arrays.toString(formulaFromStr));
				int i = (Arrays.binarySearch(formulaFromStr,split[j]))   ;
				if ((index > 0 && v.charAt(index - 1) == '+')) {
					map.put(String.valueOf(i), "+");
				} else if (index  > 0 && v.charAt(index - 1) == '-') {
					map.put(String.valueOf(i), "-");
				}else {
					map.put(String.valueOf(i), "");
				}
			}
			list.add(map);
		});
		variableMatrix.setVariableMatrixList(variableMatrixList);
		variableMatrix.setVariableMatrixData(list);
		return R.data(variableMatrix);
	}

	/**
	 * 新增 
	 */
	@PostMapping("/save")
	@ApiOperation(value = "新增", notes = "传入variable")
	public R save(VariableDTO dto) {
		return R.data(variableService.save(dto));
	}

	/**
	 * 修改 
	 */
	@PostMapping("/update")
	@ApiOperation(value = "修改", notes = "传入variable")
	public R update(VariableDTO dto) {
		return R.data(variableService.updateById(dto));
	}

	/**
	 * 删除 
	 */
	@GetMapping("/remove")
	@ApiOperation(value = "逻辑删除", notes = "传入ids")
	public R remove(String variableId) {
		return R.data(variableService.deleteLogic(Func.toIntList(variableId)));
	}

	/**
	 * 查询基础sdg图
	 */
	@GetMapping("/sdg")
	@ApiOperation(value = "详情", notes = "传入variable")
	public R<SDGSummary> sdg(VariableDTO dto) {
		FormulaDTO formulaDTO = new FormulaDTO();
		formulaDTO.setProjectId(dto.getProjectId());
		Map<Object, Boolean> map = new HashMap<>();
		List<Formula> formulaList = formulaService.list(formulaDTO);
		String formulaFrom = formulaList.stream().distinct().map(Formula::getFormulaFrom).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining());
		List<String> formula = formulaList.stream().map(Formula::getFormulaTo).filter(formulaTo -> map.putIfAbsent(formulaTo, Boolean.TRUE) == null).collect(Collectors.toList());
		Map<String, String> formulaMap = formulaList.stream().collect(Collectors.toMap(Formula::getFormulaTo, Formula::getFormulaFrom, (k1,k2) -> k1+k2));
		String[] formulaFromStr = formulaFrom.split("[+\\-]");
		Arrays.sort(formulaFromStr);
		for (int j = 1; j < formulaFromStr.length; j++) {
			if (!formula.contains(formulaFromStr[j])) {
				formula.add(formulaFromStr[j]);
			}
		}

		List<SDGNode> nodes = new ArrayList<>();
		formula.forEach(s -> {
			nodes.add(new SDGNode(s,s));
		});

		List<SDGEdges> edges = new ArrayList<>();
		formulaMap.forEach((k, v) -> {
			String[] split = v.split("[+\\-]");
			for (int i = 1; i < split.length; i++) {
				int index = v.indexOf(split[i]);
				if(v.charAt(index - 1) == '-'){
					edges.add(new SDGEdges(split[i],k,true));
				}else {
					edges.add(new SDGEdges(split[i],k,false));
				}
			}
		});
		SDGSummary sdgSummary = new SDGSummary();
		sdgSummary.setEdges(edges);
		sdgSummary.setNodes(nodes);
		return R.data(sdgSummary);
	}

	/**
	 * 查询基础sdg图
	 */
	@GetMapping("/sdgDetail")
	@ApiOperation(value = "详情", notes = "传入variable")
	public R<SDGSummary> sdgDetail(VariableDTO dto) {
		FormulaDTO formulaDTO = new FormulaDTO();
		formulaDTO.setProjectId(dto.getProjectId());
		Map<Object, Boolean> map = new HashMap<>();
		List<Formula> formulaList = formulaService.list(formulaDTO);
		AdverseOutComesDTO adverseOutComesDTO = new AdverseOutComesDTO();
		adverseOutComesDTO.setProjectId(dto.getProjectId());
		List<AdverseOutComes> adverseOutComesList = adverseOutComesService.list(adverseOutComesDTO);
		List<String> adverseOutComes = adverseOutComesList.stream().map(AdverseOutComes::getPullOffNode).collect(Collectors.toList());
		List<String> adverseOutComes2 = adverseOutComesList.stream().map(AdverseOutComes::getAdverseOutComes).collect(Collectors.toList());
		Map<String, String> adverseOutComesMap = adverseOutComesList.stream().collect(Collectors.toMap(AdverseOutComes::getPullOffNode, AdverseOutComes::getAdverseOutComes));
		AbnormalCausesDTO abnormalCausesDTO = new AbnormalCausesDTO();
		abnormalCausesDTO.setProjectId(dto.getProjectId());
		List<AbnormalCauses> abnormalCausesList = abnormalCausesService.list(abnormalCausesDTO);
		List<String> abnormalCauses = abnormalCausesList.stream().map(AbnormalCauses::getConsequenceNode).collect(Collectors.toList());
		List<String> abnormalCauses2 = abnormalCausesList.stream().map(AbnormalCauses::getAbnormalCauses).collect(Collectors.toList());
		Map<String, String> abnormalCausesMap = abnormalCausesList.stream().collect(Collectors.toMap(AbnormalCauses::getConsequenceNode, AbnormalCauses::getAbnormalCauses));
		String formulaFrom = formulaList.stream().distinct().map(Formula::getFormulaFrom).collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining());
		List<String> formula = formulaList.stream().map(Formula::getFormulaTo).filter(formulaTo -> map.putIfAbsent(formulaTo, Boolean.TRUE) == null).collect(Collectors.toList());
		Map<String, String> formulaMap = formulaList.stream().collect(Collectors.toMap(Formula::getFormulaTo, Formula::getFormulaFrom, (k1,k2) -> k1+k2));
		String[] formulaFromStr = formulaFrom.split("[+\\-]");
		Arrays.sort(formulaFromStr);
		for (int j = 1; j < formulaFromStr.length; j++) {
			if (!formula.contains(formulaFromStr[j])) {
				formula.add(formulaFromStr[j]);
			}
		}
		List<SDGNode> nodes = new ArrayList<>();
		formula.forEach(s -> {
			nodes.add(new SDGNode(s,s));
		});
		adverseOutComes2.forEach(s -> {
			nodes.add(new SDGNode(s,s,"box"));
		});
		abnormalCauses2.forEach(s -> {
			nodes.add(new SDGNode(s,s,"box"));
		});

		Set<SDGEdges> edges = new HashSet<>();
		formulaMap.forEach((k, v) -> {
			String[] split = v.split("[+\\-]");
			for (int i = 1; i < split.length; i++) {
				int index = v.indexOf(split[i]);
				if(v.charAt(index - 1) == '-'){
					edges.add(new SDGEdges(split[i],k,true));
				}else {
					edges.add(new SDGEdges(split[i],k,false));
				}
				if(adverseOutComes.contains(split[i])){
					edges.add(new SDGEdges(split[i],adverseOutComesMap.get(split[i]),false));
				}
				if(adverseOutComes.contains(k)){
					edges.add(new SDGEdges(k,adverseOutComesMap.get(k),false));
				}
				if(abnormalCauses.contains(split[i])){
					edges.add(new SDGEdges(abnormalCausesMap.get(split[i]),split[i],false));
				}
				if(abnormalCauses.contains(k)){
					edges.add(new SDGEdges(abnormalCausesMap.get(k),k,false));
				}
			}
		});
		SDGSummary sdgSummary = new SDGSummary();
		sdgSummary.setEdges(new ArrayList<>(edges));
		sdgSummary.setNodes(nodes);
		return R.data(sdgSummary);
	}

}
