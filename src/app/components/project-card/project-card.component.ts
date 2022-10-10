import { Component, Input, OnInit } from '@angular/core';

export interface projectCard {
  title: string,
  description: string;
  videoPath: string;
  languages: string[];
  detailsLink?: string;
}

@Component({
  selector: 'app-project-card',
  templateUrl: './project-card.component.html',
  styleUrls: ['./project-card.component.css']
})
export class ProjectCardComponent implements OnInit {
  @Input()
  public cardData!: projectCard;

  constructor() { }

  ngOnInit(): void {
  }

}
