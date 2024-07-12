package com.krainet.test.repository;

import com.krainet.test.dto.projectDto.ProjectDto;
import com.krainet.test.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByName(String name);
}
