package com.example.api.service;

import com.example.api.model.ProjectDescription;
import com.example.api.model.ProjectDetail;
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
    private final ProjectDetailService projectDetailService;

    public ProjectMediaService(ProjectMediaRepository projectMediaRepository,
                               ProjectService projectService,
                               ProjectDetailService projectDetailService) {
        this.projectMediaRepository = projectMediaRepository;
        this.projectService = projectService;
        this.projectDetailService = projectDetailService;
    }

    public Iterable<String> getAllMediasNames() {
        return projectMediaRepository.getNames();
    }

    public Optional<byte[]> getMediaData(ProjectDescription projectDescription) {
        int mediaId = projectDescription.getMedia().getId();
        byte[] mediaData = projectMediaRepository.findById(mediaId).get().getData();
        return Optional.of(mediaData);
    }

    public Iterable<ProjectMedia> getAllMedias() {
        return projectMediaRepository.findAll();
    }

    public Optional<ProjectMedia> getMedia(int id) {
        return projectMediaRepository.findById(id);
    }

    public Optional<ProjectDescription> deleteMedia(ProjectDescription projectDescription) {
        ProjectMedia media = projectDescription.getMedia();
        if (media == null) {
            return Optional.empty();
        }
        projectDescription.setMedia(null);
        projectDescription.setMediaPath(null);
        projectMediaRepository.delete(media);
        return Optional.of(projectDescription);
    }

    public Optional<ProjectDescription> createMedia(
            ProjectDescription projectDescription, MultipartFile file) throws IOException {
        if (projectDescription.getMedia() != null) {
            return Optional.empty();
        }
        ProjectMedia media = createProjectMedia(file);
        ProjectMedia savedMedia = projectMediaRepository.save(media);

        String path = projectDescription.getClass() == ProjectDetail.class ? "project-detail/" : "project/";
        projectDescription.setMedia(savedMedia);
        projectDescription.setMediaPath("media/" + path + projectDescription.getId());
        return Optional.of(projectDescription);
    }

    public Optional<ProjectDescription> updateMedia(
            ProjectDescription projectDescription, MultipartFile file) throws IOException {
        ProjectMedia oldMedia = projectDescription.getMedia();
        if (oldMedia == null) {
            return Optional.empty();
        }
        Optional optionalDeletedMedia = deleteMedia(projectDescription);
        if (optionalDeletedMedia.isEmpty()) {
            return Optional.empty();
        }
        Optional<ProjectDescription> optionalMedia = createMedia(projectDescription, file);
        if (optionalMedia.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(optionalMedia.get());
    }

    private ProjectMedia createProjectMedia(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileType = file.getContentType();
        byte[] fileData = file.getBytes();
        ProjectMedia media = new ProjectMedia(0, fileName, fileType, fileData);
        return media;
    }
}
