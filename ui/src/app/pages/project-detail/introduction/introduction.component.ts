import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project, ProjectService } from 'src/app/services/project.service';

@Component({
  selector: 'app-introduction',
  templateUrl: './introduction.component.html',
  styleUrls: ['./introduction.component.css']
})
export class IntroductionComponent implements OnInit {
  @Input()
  public project!: Project;

  constructor() {
  }

  ngOnInit(): void {
  }
}
