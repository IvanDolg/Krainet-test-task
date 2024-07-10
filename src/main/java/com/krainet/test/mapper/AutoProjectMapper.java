package com.krainet.test.mapper;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.userDto.UserDto;
import com.krainet.test.entity.Project;
import com.krainet.test.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoProjectMapper {
    AutoProjectMapper MAPPER = Mappers.getMapper(AutoProjectMapper.class);

    ProjectDto mapToProjectDto(Project project);
    Project mapToProject(ProjectDto projectDto);
}
