import { Component, OnInit } from '@angular/core';
import { projectCard } from 'src/app/components/project-card/project-card.component';

@Component({
  selector: 'app-detail-list',
  templateUrl: './detail-list.component.html',
  styleUrls: ['./detail-list.component.css']
})
export class DetailListComponent implements OnInit {
  public projectDetails: Array<projectCard> = new Array<projectCard>();

  constructor() {
    this.projectDetails.push({
      title: "test title",
      description: "test description",
      videoPath: "https://welbjr.github.io/portfolio/static/provincia_landing-page.mkv",
    });

    this.projectDetails.push({
      title: "test title",
      description: "test description",
      videoPath: "https://welbjr.github.io/portfolio/static/provincia_landing-page.mkv",
    });

    this.projectDetails.push({
      title: "test title",
      description: "test description",
      videoPath: "https://welbjr.github.io/portfolio/static/provincia_landing-page.mkv",
    });
  }

  ngOnInit(): void {
  }

}
