package com.example.api.controller;

import com.example.api.model.ProjectMedia;
import com.example.api.service.ProjectMediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/media")
public class ProjectMediaController {
    private final ProjectMediaService projectMediaService;

    public ProjectMediaController(ProjectMediaService projectMediaService) {
        this.projectMediaService = projectMediaService;
    }

    @GetMapping
    public Iterable<String> getMediasNames() {
        return projectMediaService.getAllMediasNames();
    }

    @DeleteMapping("parent/{id}")
    public ResponseEntity deleteMedia(@PathVariable int id) {
        Optional<ProjectMedia> optionalMedia = projectMediaService.deleteMedia(id);
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("parent/{id}")
    public ResponseEntity createMedia(@PathVariable int id, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            optionalMedia = projectMediaService.createMedia(id, media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("parent/{id}")
    public ResponseEntity updatedMedia(@PathVariable int id, @RequestParam MultipartFile media) {
        Optional<ProjectMedia> optionalMedia;
        try {
            optionalMedia = projectMediaService.updateMedia(id, media);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return optionalMedia.isEmpty()
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.OK).build();
    }
}
