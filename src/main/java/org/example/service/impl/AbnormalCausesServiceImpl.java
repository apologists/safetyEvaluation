package org.example.service.impl;

import org.example.dto.HazopDTO;
import org.example.dto.VariableDTO;
import org.example.entity.AbnormalCauses;
import org.example.entity.Hazop;
import org.example.entity.Variable;
import org.example.mapper.AbnormalCausesMapper;
import org.example.service.IAbnormalCausesService;
import org.example.dto.AbnormalCausesDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.common.Condition;
import org.example.service.IHazopService;
import org.example.service.IVariableService;
import org.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import cn.hutool.core.util.StrUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  服务实现类
 *
 * @author AI
 * @since 2022-08-25
 */
@Service
@AllArgsConstructor
public class AbnormalCausesServiceImpl implements IAbnormalCausesService {

    protected AbnormalCausesMapper abnormalCausesMapper;

    private IVariableService variableService;

    private IHazopService hazopService;
    @Override
    public IPage<AbnormalCauses> page(AbnormalCausesDTO dto) {
        IPage<AbnormalCauses> page = Condition.getPage(dto);
        QueryWrapper<AbnormalCauses> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, AbnormalCauses.class));
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
        return abnormalCausesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<AbnormalCauses> list(AbnormalCausesDTO dto) {
        QueryWrapper<AbnormalCauses> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, AbnormalCauses.class));
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
        return abnormalCausesMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(AbnormalCausesDTO dto) {
        VariableDTO variableDTO = new VariableDTO();
        variableDTO.setProjectId(dto.getProjectId());
        List<Variable> list = variableService.list(variableDTO);
        List<String> collect = list.stream().map(Variable::getVariableEn).collect(Collectors.toList());
        if(!collect.contains(dto.getConsequenceNode())){
            return null;
        }
        HazopDTO hazopDTO = new HazopDTO();
        hazopDTO.setProjectId(dto.getProjectId());
        hazopDTO.setPullOffNode(dto.getConsequenceNode());
        List<Hazop> list1 = hazopService.list(hazopDTO);
        if(list1.size() > 0){
            hazopDTO.setAbnormalCauses(dto.getAbnormalCauses());
            hazopDTO.setHazopId(list1.get(0).getHazopId());
            hazopService.updateById(hazopDTO);
        }else {
            hazopDTO.setAbnormalCauses(dto.getAbnormalCauses());
            hazopService.save(hazopDTO);
        }
        return abnormalCausesMapper.insert(BeanCopyUtils.copy(dto,AbnormalCauses.class));
    }

    @Override
    public Integer updateById(AbnormalCausesDTO dto) {
        HazopDTO hazopDTO = new HazopDTO();
        hazopDTO.setProjectId(dto.getProjectId());
        hazopDTO.setPullOffNode(dto.getConsequenceNode());
        if(hazopService.list(hazopDTO).size() > 0){
            hazopDTO.setDeviation(dto.getDeviation());
            hazopDTO.setAbnormalCauses(dto.getAbnormalCauses());
            hazopService.updateById(hazopDTO);
        }else {
            hazopDTO.setDeviation(dto.getDeviation());
            hazopDTO.setAbnormalCauses(dto.getAbnormalCauses());
            hazopService.save(hazopDTO);
        }
        return abnormalCausesMapper.updateById(BeanCopyUtils.copy(dto,AbnormalCauses.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return abnormalCausesMapper.deleteBatchIds(toIntList);
    }

    @Override
    public AbnormalCauses getOne(AbnormalCausesDTO dto) {
        return abnormalCausesMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,AbnormalCauses.class)));
    }
}
