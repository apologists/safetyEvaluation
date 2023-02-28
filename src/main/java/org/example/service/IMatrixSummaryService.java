package org.example.service;

import org.example.entity.MatrixSummary;
import org.example.dto.MatrixSummaryDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-22
 */
public interface IMatrixSummaryService  {

        IPage<MatrixSummary> page(MatrixSummaryDTO dto) ;

        List<MatrixSummary> list(MatrixSummaryDTO dto) ;

        Integer save(MatrixSummaryDTO dto) ;
        Integer save2(MatrixSummaryDTO dto) ;

        Integer updateById(MatrixSummaryDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        MatrixSummary getOne(MatrixSummaryDTO dto) ;
}
