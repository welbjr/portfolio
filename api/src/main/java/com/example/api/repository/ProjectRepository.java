package com.example.api.repository;

import com.example.api.model.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    @Query("SELECT p FROM Project p ORDER BY p.madeIn DESC")
    public Iterable<Project> findAllOrderByMadeInAsc();
}
