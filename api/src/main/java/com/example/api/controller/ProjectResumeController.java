package com.example.api.controller;

import com.example.api.model.ProjectResume;
import com.example.api.service.ProjectResumeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/resume")
public class ProjectResumeController {
    private final ProjectResumeService resumeService;

    public ProjectResumeController(ProjectResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getResume() {
        return resumeService.getResume();
    }

    @PostMapping
    public ProjectResume createResume(@RequestParam MultipartFile resume) throws IOException {
        return resumeService.createResume(resume);
    }
}
