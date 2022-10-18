import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface Project {
  id: number,
  title: string,
	description: string,
	videoPath: string,
	madeIn: string,
	projectLink: string,
	codeLink: string,
	languages: string[],
  details: Project[],
  mediaPath: string,
  picturePath: string,
  media: Media
}

interface Media {
  data: string[],

}

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private url: string = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.url + "/projects");
  }

  public getProject(id: number): Observable<Project> {
    return this.http.get<Project>(`${this.url}/${id}`);
  }
}
