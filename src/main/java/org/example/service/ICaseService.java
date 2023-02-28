package org.example.service;

import org.example.entity.Case;
import org.example.dto.CaseDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 案例库表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface ICaseService  {

        IPage<Case> page(CaseDTO dto) ;

        List<Case> list(CaseDTO dto) ;

        Integer save(CaseDTO dto) ;

        Integer updateById(CaseDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Case getOne(CaseDTO dto) ;
}
