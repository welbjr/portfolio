package com.example.api.service;

import com.example.api.model.Project;
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

    public Optional<byte[]> getMediaData(Project project) {
        int mediaId = project.getMedia().getId();
        byte[] mediaData = projectMediaRepository.findById(mediaId).get().getData();
        return Optional.of(mediaData);
    }

    public Optional<byte[]> getMediaData(ProjectDetail detail) {
        int mediaId = detail.getMedia().getId();
        byte[] mediaData = projectMediaRepository.findById(mediaId).get().getData();
        return Optional.of(mediaData);
    }

    public Iterable<ProjectMedia> getAllMedias() {
        return projectMediaRepository.findAll();
    }

    public Optional<ProjectMedia> getMedia(int id) {
        return projectMediaRepository.findById(id);
    }

    public Optional deleteMedia(Project project) {
        ProjectMedia media = project.getMedia();
        if (media == null) {
            return Optional.empty();
        }
        project.setMedia(null);
        project.setMediaPath(null);
        projectService.saveProject(project);
        projectMediaRepository.delete(media);
        return Optional.of(media);
    }

    public Optional deleteMedia(ProjectDetail detail) {
        ProjectMedia media = detail.getMedia();
        if (media == null) {
            return Optional.empty();
        }
        detail.setMedia(null);
        detail.setMediaPath(null);
        projectDetailService.saveDetail(detail);
        projectMediaRepository.delete(media);
        return Optional.of(media);
    }

    public Optional createMedia(Project project, MultipartFile file) throws IOException {
        if (project.getMedia() != null) {
            return Optional.empty();
        }

        ProjectMedia media = createProjectMedia(file);

        ProjectMedia savedMedia = projectMediaRepository.save(media);
        project.setMedia(savedMedia);
        project.setMediaPath("media/project/" + project.getId());
        projectService.saveProject(project);
        return Optional.of(savedMedia);
    }

    public Optional createMedia(ProjectDetail detail, MultipartFile file) throws IOException {
        if (detail.getMedia() != null) {
            return Optional.empty();
        }

        ProjectMedia media = createProjectMedia(file);

        ProjectMedia savedMedia = projectMediaRepository.save(media);
        detail.setMedia(savedMedia);
        detail.setMediaPath("media/project-detail/" + detail.getId());
        projectDetailService.saveDetail(detail);
        return Optional.of(savedMedia);
    }

    public Optional updateMedia(Project project, MultipartFile file) throws IOException {
        ProjectMedia oldMedia = project.getMedia();
        if (oldMedia == null) {
            return Optional.empty();
        }
        Optional optionalDeletedMedia = deleteMedia(project);
        if (optionalDeletedMedia.isEmpty()) {
            return Optional.empty();
        }
        Optional<ProjectMedia> optionalMedia = createMedia(project, file);
        if (optionalMedia.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(optionalMedia.get());
    }

    public Optional updateMedia(ProjectDetail detail, MultipartFile file) throws IOException {
        ProjectMedia oldMedia = detail.getMedia();
        if (oldMedia == null) {
            return Optional.empty();
        }
        Optional optionalDeletedMedia = deleteMedia(detail);
        if (optionalDeletedMedia.isEmpty()) {
            return Optional.empty();
        }
        Optional<ProjectMedia> optionalMedia = createMedia(detail, file);
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
