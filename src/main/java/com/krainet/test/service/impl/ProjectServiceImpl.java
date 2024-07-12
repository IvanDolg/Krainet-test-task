package com.krainet.test.service.impl;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.entity.Project;
import com.krainet.test.exception.ProjectAlreadyExistsException;
import com.krainet.test.exception.RecurseNotFoundException;
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

        Optional<Project> optionalProject = projectRepository.findByName(projectDto.getName());

        if (optionalProject.isPresent()) {
            throw new ProjectAlreadyExistsException("Project already exists");
        }

        Project project = AutoProjectMapper.MAPPER.mapToProject(projectDto);
        Project savedProject = projectRepository.save(project);
        return AutoProjectMapper.MAPPER.mapToProjectDto(savedProject);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
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
    public ProjectDto findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
        );

        return AutoProjectMapper.MAPPER.mapToProjectDto(project);
    }

    @Override
    public void deleteById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(
                () -> new RecurseNotFoundException("Project", "id", String.valueOf(id))
        );

        projectRepository.delete(project);
    }
}
