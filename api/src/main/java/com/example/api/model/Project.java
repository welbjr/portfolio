package com.example.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "details")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String[] languages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectDetail> details;
    @OneToOne(cascade = CascadeType.ALL)
    private ProjectMedia media;
}
