package org.example.service;

import org.example.entity.RiskGrade;
import org.example.dto.RiskGradeDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-23
 */
public interface IRiskGradeService  {

        IPage<RiskGrade> page(RiskGradeDTO dto) ;

        List<RiskGrade> list(RiskGradeDTO dto) ;

        Integer save(RiskGradeDTO dto) ;

        Integer updateById(RiskGradeDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        RiskGrade getOne(RiskGradeDTO dto) ;
}
