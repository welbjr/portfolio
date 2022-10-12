package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Iterable<Project> getAllProjects() {
        return projectService.findAllProjects();
    }

    @GetMapping("{id}")
    public ResponseEntity getProject(@PathVariable int id) {
        Optional<Project> optionalProject = projectService.findProjectById(id);
        return optionalProject.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalProject.get());
    }

    @PostMapping
    public ResponseEntity createProject(@RequestBody Project project) {
        Project p = projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProject(@PathVariable int id) {
        Optional<Project> optionalProject = projectService.findProjectById(id);
        if (optionalProject.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        optionalProject.ifPresent(projectService::deleteProject);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateProject(@PathVariable int id,
                                        @RequestBody Project project) {
        Optional<Project> optionalProject = projectService.updateProject(id, project);
        return optionalProject.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                : ResponseEntity.status(HttpStatus.OK).body(optionalProject.get());
    }
}
