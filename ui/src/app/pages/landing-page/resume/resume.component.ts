import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {
  public resumeUrl: string = `${environment.apiUrl}/resume`;

  constructor() { }

  ngOnInit(): void {
  }

}
