package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.model.ProjectDetail;
import com.example.api.repository.ProjectDetailRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class ProjectDetailService {
    private final ProjectDetailRepository projectDetailRepository;
    private final ProjectService projectService;

    public ProjectDetailService(ProjectDetailRepository projectDetailRepository,
                                ProjectService projectService) {
        this.projectDetailRepository = projectDetailRepository;
        this.projectService = projectService;
    }

    public Iterable<ProjectDetail> findAllDetails() {
        return projectDetailRepository.findAll();
    }

    public Optional<ProjectDetail> findDetailById(int id) {
        return projectDetailRepository.findById(id);
    }

    public Optional<ProjectDetail> createDetail(int parentId, ProjectDetail detail) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return Optional.empty();
        }
        Project project = optionalProject.get();
        detail.setProject(project);
        projectDetailRepository.save(detail);
        return Optional.of(detail);
    }

    public Optional<ProjectDetail> deleteDetail(ProjectDetail detail) {
        Optional<Project> project = projectService.findProjectById(detail.getProject().getId());
        if (project.isEmpty()) {
            return Optional.empty();
        }
        Set<ProjectDetail> projectDetails = project.get().getDetails();
        projectDetails.removeIf(d -> d.getId() == detail.getId());
        projectService.saveProject(project.get());
        projectDetailRepository.delete(detail);
        return Optional.of(detail);
    }

    public Optional<ProjectDetail> updateDetail(int id, ProjectDetail updatedDetail) {
        Optional<ProjectDetail> optionalDetail = findDetailById(id);
        if (optionalDetail.isEmpty()) {
            return Optional.empty();
        }
        ProjectDetail detail = optionalDetail.get();
        BeanUtils.copyProperties(updatedDetail, detail, "id", "project");
        projectDetailRepository.save(detail);
        return Optional.of(detail);
    }

    public void saveDetail(ProjectDetail detail) {
        projectDetailRepository.save(detail);
    }
}
