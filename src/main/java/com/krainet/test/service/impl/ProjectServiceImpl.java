package com.krainet.test.service.impl;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.entity.Project;
import com.krainet.test.mapper.AutoProjectMapper;
import com.krainet.test.repository.ProjectRepository;
import com.krainet.test.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private ProjectRepository projectRepository;
    @Override
    public ProjectDto save(ProjectDto projectDto) {
        Project project = AutoProjectMapper.MAPPER.mapToProject(projectDto);
        Project savedProject = projectRepository.save(project);
        return AutoProjectMapper.MAPPER.mapToProjectDto(savedProject);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Project not found")
        );

        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());

        Project updatedProject = projectRepository.save(project);
        return AutoProjectMapper.MAPPER.mapToProjectDto(updatedProject);
    }

    @Override
    public List<ProjectDto> findAll() {
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(AutoProjectMapper.MAPPER::mapToProjectDto).toList();
    }

    @Override
    public Optional<ProjectDto> findById(Long id) {
        return projectRepository.findById(id).map(AutoProjectMapper.MAPPER::mapToProjectDto);
    }

    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
