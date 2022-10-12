package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.repository.ProjectRepository;
import com.example.api.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectControllerTest {
    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        projectController = new ProjectController(projectService);
    }

    @Test
    void getAllProjects() {
        ArrayList<Project> projects = new ArrayList<>();

        when(projectService.findAllProjects()).thenReturn(projects);
        Iterable<Project> result = projectController.getAllProjects();

        assertEquals(result, projects);
    }

    @Test
    void createProject() {
        Project project = new Project();

        when(projectService.saveProject(project)).thenReturn(project);
        ResponseEntity result = projectController.createProject(project);

        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void deleteProject() {
        int projectId = 1;
        Project project = new Project();

        doReturn(Optional.of(project)).when(projectService).findProjectById(projectId);
        doNothing().when(projectService).deleteProject(project);
        ResponseEntity result = projectController.deleteProject(projectId);

        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void updateProject() {
        int projectId = 1;
        Project project = new Project();
        Project updatedProject = new Project();
        updatedProject.setTitle("updated project");

        doReturn(Optional.of(project)).when(projectService).findProjectById(projectId);
        doReturn(Optional.of(updatedProject)).when(projectService).updateProject(projectId, project);
        var result = projectController.updateProject(projectId, project);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), updatedProject);
        assertNotEquals(result.getBody(), project);
    }
}