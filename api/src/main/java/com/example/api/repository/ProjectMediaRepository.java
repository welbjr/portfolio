package com.example.api.repository;

import com.example.api.model.ProjectMedia;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProjectMediaRepository extends CrudRepository<ProjectMedia, Integer> {
    @Query("SELECT pm.name FROM ProjectMedia pm")
    Iterable<String> getNames();
}
