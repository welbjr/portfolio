import { Component, OnInit } from '@angular/core';
import { Project, ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  public projects: Project[] = [];

  constructor(private projectService: ProjectService) {
  }

  ngOnInit(): void {
    this.getProjects();
  }

  getProjects() {
    this.projectService.getProjects()
      .subscribe({
        next: (projects) => this.projects.push(...projects),
      });
      console.log(this.projects);

  }

}
