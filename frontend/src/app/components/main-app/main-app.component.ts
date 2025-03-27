import {Component, effect, inject, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MediaService} from '../../services/media.service';
import {SearchMultiResponse} from '../../models/search-multi-response';
import {NgOptimizedImage} from '@angular/common';
import {SearchService} from '../../services/search.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs';

@Component({
  selector: 'app-main-app',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgOptimizedImage,
    RouterLink
  ],
  templateUrl: './main-app.component.html',
  styleUrl: './main-app.component.css'
})
export class MainAppComponent {

  authService = inject(AuthService)
  searchService = inject(SearchService)

  route = inject(Router)

  public result: SearchMultiResponse = {
    page: 0,
    results: [],
    total_results: 0,
    total_pages: 0
  };

  constructor() {
    effect(() => {
      this.result = this.searchService.result();
    })
  }


  logOut() {
    this.authService.logOut()
    this.route.navigate(["/"])
  }
}

