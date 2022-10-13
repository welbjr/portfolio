package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.model.ProjectMedia;
import com.example.api.repository.ProjectMediaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProjectMediaService {
    private final ProjectMediaRepository projectMediaRepository;
    private final ProjectService projectService;

    public ProjectMediaService(ProjectMediaRepository projectMediaRepository, ProjectService projectService) {
        this.projectMediaRepository = projectMediaRepository;
        this.projectService = projectService;
    }

    public Iterable<String> getAllMediasNames() {
        return projectMediaRepository.getNames();
    }

    public Iterable<ProjectMedia> getAllMedias() {
        return projectMediaRepository.findAll();
    }

    public Optional<ProjectMedia> getMedia(int id) {
        return projectMediaRepository.findById(id);
    }

    public Optional deleteMedia(int parentId) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return Optional.empty();
        }
        Project project = optionalProject.get();
        ProjectMedia media = project.getMedia();
        project.setMedia(null);
        projectService.saveProject(project);
        projectMediaRepository.delete(media);
        return Optional.of(media);
    }

    public Optional createMedia(int parentId, MultipartFile file) throws IOException {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return Optional.empty();
        }

        Project project = optionalProject.get();
        if (project.getMedia() != null) {
            return Optional.empty();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        byte[] fileData = file.getBytes();
        ProjectMedia media = new ProjectMedia(0, fileName, fileType, fileData);

        ProjectMedia savedMedia = projectMediaRepository.save(media);
        project.setMedia(savedMedia);
        projectService.saveProject(project);
        return Optional.of(media);
    }

    public Optional updateMedia(int parentId, MultipartFile file) throws IOException {
        Optional<ProjectMedia> optionalDeleted = deleteMedia(parentId);
        if (optionalDeleted.isEmpty()) {
            return Optional.empty();
        }

        Optional<ProjectMedia> optionalMedia = createMedia(parentId, file);
        if (optionalMedia.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(optionalMedia.get());
    }
}
