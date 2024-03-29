import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { BannerComponent } from './pages/landing-page/banner/banner.component';
import { ResumeComponent } from './pages/landing-page/resume/resume.component';
import { ProjectListComponent } from './pages/landing-page/project-list/project-list.component';
import { ProjectCardComponent } from './components/project-card/project-card.component';

import { MatGridListModule } from '@angular/material/grid-list';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { ContactComponent } from './pages/landing-page/contact/contact.component';
import { ProjectDetailComponent } from './pages/project-detail/project-detail.component';
import { IntroductionComponent } from './pages/project-detail/introduction/introduction.component';
import { DetailListComponent } from './pages/project-detail/detail-list/detail-list.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    BannerComponent,
    ResumeComponent,
    ProjectListComponent,
    ProjectCardComponent,
    ContactComponent,
    ProjectDetailComponent,
    IntroductionComponent,
    DetailListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,

    // Material
    MatGridListModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatChipsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
