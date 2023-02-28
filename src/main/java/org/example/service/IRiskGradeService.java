package org.example.service;

import org.example.entity.RiskGrade;
import org.example.dto.RiskGradeDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 风险等级说明 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IRiskGradeService  {

        IPage<RiskGrade> page(RiskGradeDTO dto) ;

        List<RiskGrade> list(RiskGradeDTO dto) ;

        Integer save(RiskGradeDTO dto) ;

        Integer updateById(RiskGradeDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        RiskGrade getOne(RiskGradeDTO dto) ;
}
