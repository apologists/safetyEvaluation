package org.example.service;

import org.example.entity.Project;
import org.example.dto.ProjectDTO;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 项目表 服务类
 *
 * @author AI
 * @since 2023-03-01
 */
public interface IProjectService  {

        IPage<Project> page(ProjectDTO dto) ;

        List<Project> list(ProjectDTO dto) ;

        Integer save(ProjectDTO dto) ;

        Integer updateById(ProjectDTO dto) ;

        Integer deleteLogic(List<Integer> toIntList) ;

        Project getOne(ProjectDTO dto) ;
}
