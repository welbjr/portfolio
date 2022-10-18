import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { Project } from 'src/app/services/project.service';

@Component({
  selector: 'app-detail-list',
  templateUrl: './detail-list.component.html',
  styleUrls: ['./detail-list.component.css']
})
export class DetailListComponent implements OnInit {
  @Input()
  public project!: Project;

  constructor() {
  }

  ngOnInit(): void {
  }
}
