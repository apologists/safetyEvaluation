package org.example.service.impl;

import org.example.dto.*;
import org.example.entity.*;
import org.example.mapper.FormulaMapper;
import org.example.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-20
 */
@Service
@AllArgsConstructor
public class FormulaServiceImpl implements IFormulaService {

    protected FormulaMapper formulaMapper;

    private IVariableService variableService;

    private IHazopService hazopService;

    @Autowired
    @Lazy
    private IAbnormalCausesService abnormalCausesService;
    @Autowired
    @Lazy
    private IAdverseOutComesService adverseOutComesService;

    @Override
    public IPage<Formula> page(FormulaDTO dto) {
        IPage<Formula> page = Condition.getPage(dto);
        QueryWrapper<Formula> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Formula.class));
        if (StrUtil.isNotEmpty(dto.getColumn()) && StrUtil.isNotEmpty(dto.getKeywords())) {
        queryWrapper.like(dto.getColumn(),dto.getKeywords());
        }
        if (StrUtil.isNotEmpty(dto.getOrderAsc())) {
        queryWrapper.orderByAsc(dto.getOrderAsc());
        }
        if (StrUtil.isNotEmpty(dto.getOrderDesc())) {
        queryWrapper.orderByDesc(dto.getOrderDesc());
        }
        if (dto.getStartTime() != null) {
        queryWrapper.gt("created",dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
        queryWrapper.lt("created",dto.getEndTime());
        }
        return formulaMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<Formula> list(FormulaDTO dto) {
        QueryWrapper<Formula> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, Formula.class));
        if (StrUtil.isNotEmpty(dto.getColumn()) && StrUtil.isNotEmpty(dto.getKeywords())) {
        queryWrapper.like(dto.getColumn(),dto.getKeywords());
        }
        if (StrUtil.isNotEmpty(dto.getOrderAsc())) {
        queryWrapper.orderByAsc(dto.getOrderAsc());
        }
        if (StrUtil.isNotEmpty(dto.getOrderDesc())) {
        queryWrapper.orderByDesc(dto.getOrderDesc());
        }
        if (dto.getStartTime() != null) {
        queryWrapper.gt("created",dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
        queryWrapper.lt("created",dto.getEndTime());
        }
        return formulaMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(FormulaDTO dto) {
        Formula formula = null;
        Map<String,List<String>> map = new HashMap<>();
        try {
            VariableDTO variableDTO = new VariableDTO();
            variableDTO.setProjectId(dto.getProjectId());
            List<Variable> list = variableService.list(variableDTO);
            List<String> collect = list.stream().map(Variable::getVariableEn).collect(Collectors.toList());

            formula = BeanCopyUtils.copy(dto, Formula.class);
            String formulaStr = dto.getFormula();
            String[] split = formulaStr.split("<-");
            formula.setFormulaTo(split[0]);
            if(!(split[1].charAt(0) == '-')){
                formula.setFormulaFrom("+"+split[1]);
            }else {
                formula.setFormulaFrom(split[1]);
            }
            String[] formulaFrom = split[1].split("[+\\-]");
            for (String str : formulaFrom) {
                if(!collect.contains(str) && !str.equals("")){
                    return null;
                }
            }

        } catch (Exception e) {
            return null;
        }
        formulaMapper.insert(formula);
        FormulaDTO formulaDTO = new FormulaDTO();
        formulaDTO.setProjectId(dto.getProjectId());
        List<Formula> list = list(formulaDTO);
        Graph<String> graph=new Graph<>(true);
        list.forEach(f -> {
            graph.addVertex(f.getFormulaTo(),0);
            String formulaFrom = f.getFormulaFrom();
            String[] split = formulaFrom.split("[+\\-]");
            for (String str : split) {
                if(!str.equals("")){
                    graph.addVertex(str,0);
                    graph.addEdge(f.getFormulaTo(),str,0);
                }
            }
        });
        graph.printGraph(map);
        map.forEach((k,v)->{
            for (String f : v) {
                AbnormalCausesDTO abnormalCausesDTO = new AbnormalCausesDTO();
                abnormalCausesDTO.setProjectId(dto.getProjectId());
                abnormalCausesDTO.setConsequenceNode(f);
                List<AbnormalCauses> list1 = abnormalCausesService.list(abnormalCausesDTO);

                AdverseOutComesDTO adverseOutComes = new AdverseOutComesDTO();
                adverseOutComes.setProjectId(dto.getProjectId());
                adverseOutComes.setPullOffNode(f);
                List<AdverseOutComes> list2 = adverseOutComesService.list(adverseOutComes);

                HazopDTO hazopDTO = new HazopDTO();
                hazopDTO.setPullOffNode(f);
                hazopDTO.setProjectId(dto.getProjectId());
                if(!list1.isEmpty()){
                    hazopDTO.setAbnormalCauses(list1.get(0).getAbnormalCauses());
                    hazopDTO.setDeviation(list1.get(0).getDeviation());
                }
                if(!list2.isEmpty()){
                    hazopDTO.setAdverseOutComes(list2.get(0).getAdverseOutComes());
                }
                hazopService.save(hazopDTO);
            }
        });
        return 1;
    }

    @Override
    public Integer updateById(FormulaDTO dto) {
        return formulaMapper.updateById(BeanCopyUtils.copy(dto,Formula.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return formulaMapper.deleteBatchIds(toIntList);
    }

    @Override
    public Formula getOne(FormulaDTO dto) {
        return formulaMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,Formula.class)));
    }
}
