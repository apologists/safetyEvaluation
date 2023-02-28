package org.example.service;

import org.example.entity.BaseSummary;
import org.example.dto.BaseSummaryDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-25
 */
public interface IBaseSummaryService  {

        IPage<BaseSummary> page(BaseSummaryDTO dto) ;

        List<BaseSummary> list(BaseSummaryDTO dto) ;

        Integer save(BaseSummaryDTO dto) ;

        Integer updateById(BaseSummaryDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        BaseSummary getOne(BaseSummaryDTO dto) ;
}
