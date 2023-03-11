package org.example.service;

import org.example.entity.Sdg;
import org.example.dto.SdgDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * sdg拉偏表 服务类
 *
 * @author AI
 * @since 2023-03-11
 */
public interface ISdgService  {

        IPage<Sdg> page(SdgDTO dto) ;

        List<Sdg> list(SdgDTO dto) ;

        Integer save(SdgDTO dto) ;

        Integer updateById(SdgDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Sdg getOne(SdgDTO dto) ;
}
