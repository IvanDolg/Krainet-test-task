package com.krainet.test.service;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.dto.userDto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto, Long id);
    List<ProjectDto> findAll();
    Optional<ProjectDto> findById(Long id);
    void deleteById(Long id);
}
