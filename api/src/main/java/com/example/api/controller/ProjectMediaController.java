package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.model.ProjectDetail;
import com.example.api.model.ProjectMedia;
import com.example.api.service.ProjectDetailService;
import com.example.api.service.ProjectMediaService;
import com.example.api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class ProjectMediaController {

    // todo: refatorar

    private final ProjectMediaService projectMediaService;
    private final ProjectService projectService;
    private final ProjectDetailService projectDetailService;

    public ProjectMediaController(ProjectMediaService projectMediaService,
                                  ProjectService projectService,
                                  ProjectDetailService projectDetailService) {
        this.projectMediaService = projectMediaService;
        this.projectService = projectService;
        this.projectDetailService = projectDetailService;
    }

    @GetMapping
    public Iterable<String> getMediasNames() {
        return projectMediaService.getAllMediasNames();
    }

    @GetMapping("project/{parentId}")
    public ResponseEntity getProjectMediaData(@PathVariable int parentId) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<byte[]> optionalData = projectMediaService.getMediaData(optionalProject.get());
        return optionalData.isEmpty()
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            : ResponseEntity.status(HttpStatus.OK).body(optionalData.get());
    }

    @GetMapping("project-detail/{parentId}")
    public ResponseEntity getProjectDetailMediaData(@PathVariable int parentId) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(parentId);
        if (optionalDetail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<byte[]> optionalData = projectMediaService.getMediaData(optionalDetail.get());
        return optionalData.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalData.get());
    }

    @DeleteMapping("project/{parentId}")
    public ResponseEntity deleteProjectMedia(@PathVariable int parentId) {
        Optional<Project> optionalProject = projectService.findProjectById(parentId);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<ProjectMedia> optionalMedia = projectMediaService.deleteMedia(optionalProject.get());
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("project-detail/{parentId}")
    public ResponseEntity deleteDetailMedia(@PathVariable int parentId) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(parentId);
        if (optionalDetail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Optional<ProjectMedia> optionalMedia = projectMediaService.deleteMedia(optionalDetail.get());
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("project/{parentId}")
    public ResponseEntity createProjectMedia(@PathVariable int parentId, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            Optional<Project> optionalProject = projectService.findProjectById(parentId);
            if (optionalProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            optionalMedia = projectMediaService.createMedia(optionalProject.get(), media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("project-detail/{parentId}")
    public ResponseEntity createDetailMedia(@PathVariable int parentId, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(parentId);
            if (optionalDetail.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            optionalMedia = projectMediaService.createMedia(optionalDetail.get(), media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("project/{parentId}")
    public ResponseEntity updatedProjectMedia(@PathVariable int parentId, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            Optional<Project> optionalProject = projectService.findProjectById(parentId);
            if (optionalProject.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            optionalMedia = projectMediaService.updateMedia(optionalProject.get(), media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("project-detail/{parentId}")
    public ResponseEntity updatedDetailMedia(@PathVariable int parentId, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(parentId);
            if (optionalDetail.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            optionalMedia = projectMediaService.updateMedia(optionalDetail.get(), media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.OK).build();
    }
}
