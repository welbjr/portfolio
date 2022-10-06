import { Component, OnInit } from '@angular/core';
import Typed from 'typed.js';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  public cols!: string;
  public rowHeight!: string;

  private typedOptions = {
    strings: ['Desenvolvedor Full Stack'],
    typeSpeed: 100,
    backSpeed: 100,
    showCursor: false,
  };
  private typed!: Typed;

  constructor() { }

  ngOnInit(): void {
    this.setScreenViewMode();
    this.typed = new Typed('.typed-element', this.typedOptions);
  }

  handleResize() {
    this.setScreenViewMode();
  }

  setScreenViewMode(): void {
    this.isMobile()
      ? this.setMobileViewMode()
      : this.setDesktopViewMode();
  }

  isMobile(): boolean {
    return window.innerWidth < 900;
  }

  setMobileViewMode(): void {
    this.cols = "1";
    this.rowHeight = "45vh";
  }

  setDesktopViewMode(): void {
    this.cols = "2";
    this.rowHeight = "90vh";
  }

}
