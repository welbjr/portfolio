package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.repository.ProjectRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Optional<Project> findProjectById(int id) {
        return projectRepository.findById(id);
    }

    public void deleteProject(Project project) {
        projectRepository.delete(project);
    }

    public Optional<Project> updateProject(int id, Project updatedProject) {
        Optional<Project> optionalProject = findProjectById(id);
        if (optionalProject.isEmpty()) {
            return Optional.empty();
        }
        Project project = optionalProject.get();
        BeanUtils.copyProperties(updatedProject, project, "id", "details");
        return Optional.of(projectRepository.save(project));
    }
}
