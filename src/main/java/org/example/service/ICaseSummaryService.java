package org.example.service;

import org.example.entity.CaseSummary;
import org.example.dto.CaseSummaryDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 案例库表 服务类
 *
 * @author AI
 * @since 2023-03-11
 */
public interface ICaseSummaryService  {

        IPage<CaseSummary> page(CaseSummaryDTO dto) ;

        List<CaseSummary> list(CaseSummaryDTO dto) ;

        Integer save(CaseSummaryDTO dto) ;

        Integer updateById(CaseSummaryDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        CaseSummary getOne(CaseSummaryDTO dto) ;
}
