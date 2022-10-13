package com.example.api.controller;

import com.example.api.model.ProjectDetail;
import com.example.api.service.ProjectDetailService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProjectDetailControllerTest {
    @Mock
    private ProjectDetailService projectDetailService;

    @InjectMocks
    private ProjectDetailController projectDetailController;

    @BeforeEach
    void setUp() {
        projectDetailController = new ProjectDetailController(projectDetailService);
    }

    @Test
    void getAllDetails() {
        ArrayList<ProjectDetail> details = new ArrayList<>();

        when(projectDetailService.findAllDetails()).thenReturn(details);
        Iterable<ProjectDetail> result = projectDetailController.getAllDetails();

        assertEquals(result, details);
    }

    @Test
    void getDetail() {
        ProjectDetail detail = new ProjectDetail();
        detail.setId(1);

        doReturn(Optional.of(detail)).when(projectDetailService).findDetailById(detail.getId());
        ResponseEntity result = projectDetailController.getDetail(detail.getId());

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), detail);
    }

    @Test
    void createDetail() {
        ProjectDetail detail = new ProjectDetail();
        int parentId = 1;

        doReturn(Optional.of(detail)).when(projectDetailService).createDetail(parentId, detail);
        ResponseEntity result = projectDetailController.createDetail(parentId, detail);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), detail);
    }

    @Test
    void deleteDetail() {
        ProjectDetail detail = new ProjectDetail();
        detail.setId(1);

        doReturn(Optional.of(detail)).when(projectDetailService).findDetailById(detail.getId());
        doReturn(Optional.of(detail)).when(projectDetailService).deleteDetail(detail);
        ResponseEntity result = projectDetailController.deleteDetail(detail.getId());

        assertEquals(result.getStatusCode(), HttpStatus.NO_CONTENT);
    }

    @Test
    void updateDetail() {
        ProjectDetail detail = new ProjectDetail();
        detail.setId(1);
        ProjectDetail updatedDetail = new ProjectDetail();
        updatedDetail.setTitle("updated detail");
        updatedDetail.setId(1);

        doReturn(Optional.of(detail)).when(projectDetailService).findDetailById(detail.getId());
        doReturn(Optional.of(updatedDetail)).when(projectDetailService).updateDetail(detail.getId(), updatedDetail);
        ResponseEntity result = projectDetailController.updateDetail(detail.getId(), updatedDetail);

        assertEquals(result.getStatusCode(), HttpStatus.OK);
        assertEquals(result.getBody(), updatedDetail);
    }
}