package org.example.service;

import org.example.entity.SilSummary;
import org.example.dto.SilSummaryDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-10-06
 */
public interface ISilSummaryService  {

        IPage<SilSummary> page(SilSummaryDTO dto) ;

        List<SilSummary> list(SilSummaryDTO dto) ;

        Integer save(SilSummaryDTO dto) ;

        Integer updateById(SilSummaryDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        SilSummary getOne(SilSummaryDTO dto) ;
}
