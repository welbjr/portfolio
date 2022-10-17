package com.example.api.service;

import com.example.api.model.ProjectResume;
import com.example.api.repository.ProjectResumeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProjectResumeService {
    private final ProjectResumeRepository projectResumeRepository;

    public ProjectResumeService(ProjectResumeRepository projectResumeRepository) {
        this.projectResumeRepository = projectResumeRepository;
    }

    public byte[] getResume() {
        ProjectResume resume = projectResumeRepository.findAll().iterator().next();
        return resume.getData();
    }

    public ProjectResume createResume(MultipartFile file) throws IOException {
        projectResumeRepository.deleteAll();
        ProjectResume resume = new ProjectResume(0, file.getBytes());
        ProjectResume savedResume = projectResumeRepository.save(resume);
        return savedResume;
    }
}
