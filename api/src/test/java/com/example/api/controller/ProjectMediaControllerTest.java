package com.example.api.controller;

import com.example.api.model.Project;
import com.example.api.model.ProjectMedia;
import com.example.api.service.ProjectDetailService;
import com.example.api.service.ProjectMediaService;
import com.example.api.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectMediaControllerTest {
    @Mock
    private ProjectMediaService projectMediaService;
    @Mock
    private ProjectService projectService;
    @Mock
    private ProjectDetailService projectDetailService;

    @InjectMocks
    private ProjectMediaController projectMediaController;

    @BeforeEach
    void setUp() {
        projectMediaController = new ProjectMediaController(projectMediaService, projectService, projectDetailService);
    }

    @Test
    void getMediasNames() {
        ArrayList<String> mediaNames = new ArrayList<>();

        when(projectMediaService.getAllMediasNames()).thenReturn(mediaNames);
        Iterable<String> result = projectMediaController.getMediasNames();

        assertEquals(result, mediaNames);
    }

    @Test
    void deleteMedia() {
        ProjectMedia media = new ProjectMedia();
        media.setId(1);

        doReturn(Optional.of(media)).when(projectMediaService).deleteMedia(media.getId());
        ResponseEntity result = projectMediaController.deleteMedia(media.getId());

        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void createMedia() throws IOException {
        Project project = new Project();
        project.setId(1);
        MultipartFile file = mock(MultipartFile.class);
        ProjectMedia media = new ProjectMedia();

        doReturn(Optional.of(media)).when(projectMediaService).createMedia(project, file);
        ResponseEntity result = projectMediaController.createProjectMedia(project.getId(), file);

        assertEquals(result.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void updatedMedia() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        ProjectMedia media = new ProjectMedia();
        media.setId(1);
        ProjectMedia updatedMedia = new ProjectMedia();
        updatedMedia.setName("updated media");

        doReturn(Optional.of(media)).when(projectMediaService).updateMedia(media.getId(), file);
        ResponseEntity result = projectMediaController.updatedMedia(media.getId(), file);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}