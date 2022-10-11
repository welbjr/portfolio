import { Component, OnInit } from '@angular/core';
import { projectCard } from 'src/app/components/project-card/project-card.component';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  public tempCardData: projectCard;

  constructor() {
    this.tempCardData = {
      title: "Hello World",
      description: "Lorem ipsum dolor sit amet.",
      videoPath: "https://welbjr.github.io/portfolio/static/provincia_admin-geral.mkv",
      languages: ['java', 'spring', 'typescript', 'angular', 'heroku'],
      detailsLink: '/todo'
    }
  }

  ngOnInit(): void {
  }

}
