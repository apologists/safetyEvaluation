package org.example.service;

import org.example.entity.RiskConsequence;
import org.example.dto.RiskConsequenceDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 风险后果说明表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IRiskConsequenceService  {

        IPage<RiskConsequence> page(RiskConsequenceDTO dto) ;

        List<RiskConsequence> list(RiskConsequenceDTO dto) ;

        Integer save(RiskConsequenceDTO dto) ;

        Integer updateById(RiskConsequenceDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        RiskConsequence getOne(RiskConsequenceDTO dto) ;
}
