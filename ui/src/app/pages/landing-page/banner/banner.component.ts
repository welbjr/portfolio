import { Component, OnInit } from '@angular/core';
import Typed from 'typed.js';

@Component({
  selector: 'app-banner',
  templateUrl: './banner.component.html',
  styleUrls: ['./banner.component.css']
})
export class BannerComponent implements OnInit {
  private typedOptions = {
    strings: ['Desenvolvedor Full Stack'],
    typeSpeed: 100,
    backSpeed: 100,
    showCursor: false,
  };
  private typed!: Typed;

  constructor() { }

  ngOnInit(): void {
    this.typed = new Typed('.typed-element', this.typedOptions);
  }
}
