package com.krainet.test.service;

import com.krainet.test.dto.projectDto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto, Long id);
    List<ProjectDto> findAll();
    ProjectDto findById(Long id);
    void deleteById(Long id);
}
