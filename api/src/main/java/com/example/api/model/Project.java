package com.example.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String projectLink;
    private String codeLink;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate madeIn;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectDetail> details;
    @OneToOne(cascade = CascadeType.ALL)
    private ProjectMedia media;
}
