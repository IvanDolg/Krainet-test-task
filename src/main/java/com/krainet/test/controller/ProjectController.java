package com.krainet.test.controller;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.service.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {
    private ProjectService projectService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> save(@RequestBody ProjectDto projectDto) {
        ProjectDto savedProject = projectService.save(projectDto);
        return new ResponseEntity<>(savedProject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> findById(@PathVariable Long id) {
        ProjectDto project = projectService.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("Project not found")
        );
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ProjectDto>> findAll() {
        List<ProjectDto> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDto> update(@RequestBody ProjectDto projectDto,
                                             @PathVariable Long id) {
        ProjectDto updatedProject = projectService.update(projectDto, id);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }
}
