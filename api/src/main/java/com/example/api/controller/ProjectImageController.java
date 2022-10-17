package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.model.ProjectImage;
import com.example.api.service.ProjectImageService;
import com.example.api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ProjectImageController {
    private final ProjectService projectService;
    private final ProjectImageService projectImageService;

    public ProjectImageController(ProjectService projectService, ProjectImageService projectImageService) {
        this.projectService = projectService;
        this.projectImageService = projectImageService;
    }

    @GetMapping("{parentId}")
    public ResponseEntity<byte[]> getImageData(@PathVariable int parentId) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Project project = optionalProject.get();
        Optional<byte[]> optionalData = projectImageService.getImageData(project.getImage().getId());
        if (optionalData.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalData.get());
    }

    @PostMapping("{parentId}")
    public ResponseEntity createImage(@PathVariable int parentId, @RequestParam MultipartFile image) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        try {
            Optional<ProjectImage> optionalImage = projectImageService.createImage(optionalProject.get(), image);
            if (optionalImage.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
