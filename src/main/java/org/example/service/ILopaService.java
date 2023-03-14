package org.example.service;

import org.example.entity.Lopa;
import org.example.dto.LopaDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.entity.LopaSummary;

/**
 * lopa分析 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface ILopaService  {

        IPage<Lopa> page(LopaDTO dto) ;

        List<Lopa> list(LopaDTO dto) ;

        Integer save(LopaDTO dto) ;

        Integer updateById(LopaDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        LopaSummary getOne(LopaDTO dto) ;
}
