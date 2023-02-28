package org.example.service;

import org.example.entity.Sil;
import org.example.dto.SilDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 *  服务类
 *
 * @author AI
 * @since 2022-09-20
 */
public interface ISilService  {

        IPage<Sil> page(SilDTO dto) ;

        List<Sil> list(SilDTO dto) ;

        Integer save(SilDTO dto) ;

        Integer updateById(SilDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Sil getOne(SilDTO dto) ;
}
