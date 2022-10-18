import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project, ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-project-detail',
  templateUrl: './project-detail.component.html',
  styleUrls: ['./project-detail.component.css']
})
export class ProjectDetailComponent implements OnInit {
  public project!: Project;
  public projectId: number;

  constructor(private route: ActivatedRoute, private projectService: ProjectService) {
    this.projectId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.getProject(this.projectId);
  }

  getProject(id: number) {
    this.projectService.getProject(id)
      .subscribe({
        next: (project) => this.project = project,
      });
      console.log(this.project);
  }


}
