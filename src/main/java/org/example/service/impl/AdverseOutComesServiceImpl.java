package org.example.service.impl;

import org.example.dto.HazopDTO;
import org.example.dto.VariableDTO;
import org.example.entity.AdverseOutComes;
import org.example.entity.Hazop;
import org.example.entity.Variable;
import org.example.mapper.AdverseOutComesMapper;
import org.example.service.IAdverseOutComesService;
import org.example.dto.AdverseOutComesDTO;
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
public class AdverseOutComesServiceImpl implements IAdverseOutComesService {

    protected AdverseOutComesMapper adverseOutComesMapper;

    private IVariableService variableService;

    private IHazopService hazopService;
    @Override
    public IPage<AdverseOutComes> page(AdverseOutComesDTO dto) {
        IPage<AdverseOutComes> page = Condition.getPage(dto);
        QueryWrapper<AdverseOutComes> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, AdverseOutComes.class));
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
        return adverseOutComesMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<AdverseOutComes> list(AdverseOutComesDTO dto) {
        QueryWrapper<AdverseOutComes> queryWrapper = Condition.getQueryWrapper(BeanCopyUtils.copy(dto, AdverseOutComes.class));
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
        return adverseOutComesMapper.selectList(queryWrapper);
    }

    @Override
    public Integer save(AdverseOutComesDTO dto) {
        VariableDTO variableDTO = new VariableDTO();
        variableDTO.setProjectId(dto.getProjectId());
        List<Variable> list = variableService.list(variableDTO);
        List<String> collect = list.stream().map(Variable::getVariableEn).collect(Collectors.toList());
        if(!collect.contains(dto.getPullOffNode())){
            return null;
        }
        HazopDTO hazopDTO = new HazopDTO();
        hazopDTO.setProjectId(dto.getProjectId());
        hazopDTO.setPullOffNode(dto.getPullOffNode());
        List<Hazop> list1 = hazopService.list(hazopDTO);
        if(list1.size() > 0){
            hazopDTO.setAdverseOutComes(dto.getAdverseOutComes());
            hazopDTO.setHazopId(list1.get(0).getHazopId());
            hazopService.updateById(hazopDTO);
        }else {
            hazopDTO.setAdverseOutComes(dto.getAdverseOutComes());
            hazopService.save(hazopDTO);
        }
        return adverseOutComesMapper.insert(BeanCopyUtils.copy(dto,AdverseOutComes.class));
    }

    @Override
    public Integer updateById(AdverseOutComesDTO dto) {
        HazopDTO hazopDTO = new HazopDTO();
        hazopDTO.setProjectId(dto.getProjectId());
        hazopDTO.setPullOffNode(dto.getPullOffNode());
        if(hazopService.list(hazopDTO).size() > 0){
            hazopDTO.setDeviation(dto.getDeviation());
            hazopDTO.setAdverseOutComes(dto.getAdverseOutComes());
            hazopService.updateById(hazopDTO);
        }else {
            hazopDTO.setDeviation(dto.getDeviation());
            hazopDTO.setAdverseOutComes(dto.getAdverseOutComes());
            hazopService.save(hazopDTO);
        }
        return adverseOutComesMapper.updateById(BeanCopyUtils.copy(dto,AdverseOutComes.class));
    }


    @Override
    public Integer deleteLogic(List<Integer> toIntList) {
        return adverseOutComesMapper.deleteBatchIds(toIntList);
    }

    @Override
    public AdverseOutComes getOne(AdverseOutComesDTO dto) {
        return adverseOutComesMapper.selectOne(Condition.getQueryWrapper(BeanCopyUtils.copy(dto,AdverseOutComes.class)));
    }
}
