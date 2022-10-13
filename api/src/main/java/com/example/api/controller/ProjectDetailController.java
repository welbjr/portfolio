package com.example.api.controller;

import com.example.api.model.ProjectDetail;
import com.example.api.service.ProjectDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("project-details")
public class ProjectDetailController {
    private final ProjectDetailService projectDetailService;

    public ProjectDetailController(ProjectDetailService projectDetailService) {
        this.projectDetailService = projectDetailService;
    }

    @GetMapping
    public Iterable<ProjectDetail> getAllDetails() {
        return projectDetailService.findAllDetails();
    }

    @GetMapping("{id}")
    public ResponseEntity getDetail(@PathVariable int id) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(id);
        return optionalDetail.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalDetail.get());
    }

    @PostMapping("parent/{id}")
    public ResponseEntity createDetail(@PathVariable int id, @RequestBody ProjectDetail projectDetail) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.createDetail(id, projectDetail);
        return optionalDetail.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalDetail.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteDetail(@PathVariable int id) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.findDetailById(id);
        if (optionalDetail.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        projectDetailService.deleteDetail(optionalDetail.get());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity updateDetail(@PathVariable int id, @RequestBody ProjectDetail detail) {
        Optional<ProjectDetail> optionalDetail = projectDetailService.updateDetail(id, detail);
        return optionalDetail.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalDetail.get());
    }
}
