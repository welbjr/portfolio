import { Component, Input, OnInit } from '@angular/core';
import { Project } from 'src/app/services/project.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent implements OnInit {
  @Input()
  public cardData!: Project;
  public url: string = environment.apiUrl;

  constructor() {
  }

  ngOnInit(): void {
  }

}
