package com.example.api.service;

import com.example.api.model.Project;
import com.example.api.model.ProjectImage;
import com.example.api.repository.ProjectImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ProjectImageService {
    private final ProjectImageRepository projectImageRepository;
    private final ProjectService projectService;

    public ProjectImageService(ProjectImageRepository projectMediaRepository, ProjectService projectService) {
        this.projectImageRepository = projectMediaRepository;
        this.projectService = projectService;
    }

    public Optional<ProjectImage> createImage(Project project, MultipartFile file) throws IOException {
        if (project.getImage() != null) {
            projectImageRepository.delete(project.getImage());
        }

        ProjectImage image = createProjectImage(file);
        ProjectImage savedImage = projectImageRepository.save(image);

        project.setImage(savedImage);
        project.setImagePath("images/" + project.getId());
        projectService.saveProject(project);
        return Optional.of(savedImage);
    }

    public Optional<byte[]> getImageData(int id) {
        Optional<ProjectImage> optionalImage = projectImageRepository.findById(id);
        if (optionalImage.isEmpty()) {
            return Optional.empty();
        }
        byte[] data = optionalImage.get().getData();
        return Optional.of(data);
    }

    private ProjectImage createProjectImage(MultipartFile file) throws IOException {
        byte[] fileData = file.getBytes();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ProjectImage media = new ProjectImage(0, fileName, fileData);
        return media;
    }
}
