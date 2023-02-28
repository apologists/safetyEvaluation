package org.example.service;

import org.example.entity.Frequency;
import org.example.dto.FrequencyDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 频率说明表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IFrequencyService  {

        IPage<Frequency> page(FrequencyDTO dto) ;

        List<Frequency> list(FrequencyDTO dto) ;

        Integer save(FrequencyDTO dto) ;

        Integer updateById(FrequencyDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Frequency getOne(FrequencyDTO dto) ;
}
