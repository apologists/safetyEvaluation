package org.example.service;

import org.example.entity.AdverseOutComes;
import org.example.dto.AdverseOutComesDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-08-25
 */
public interface IAdverseOutComesService  {

        IPage<AdverseOutComes> page(AdverseOutComesDTO dto) ;

        List<AdverseOutComes> list(AdverseOutComesDTO dto) ;

        Integer save(AdverseOutComesDTO dto) ;

        Integer updateById(AdverseOutComesDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        AdverseOutComes getOne(AdverseOutComesDTO dto) ;
}
