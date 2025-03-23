import {Component, inject, OnInit} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router, RouterLink} from '@angular/router';
import {FormControl, FormsModule, ReactiveFormsModule} from '@angular/forms';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs';
import {MediaService} from '../../services/media.service';
import {SearchMultiResponse} from '../../models/search-multi-response';
import {NgOptimizedImage} from '@angular/common';

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
export class MainAppComponent implements OnInit {

  authService = inject(AuthService)
  mediaService = inject(MediaService)

  route = inject(Router)

  searchControl = new FormControl('');
  public result: SearchMultiResponse = {
    page: 0,
    results: [],
    total_results: 0,
    total_pages: 0
  };

  ngOnInit(): void {
    this.searchControl.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => {
        return this.mediaService.searchMulti(query == null ? "" : query);
      })
    ).subscribe(data => this.result = data)
  }

  logOut() {
    this.authService.logOut()
    this.route.navigate(["/"])
  }
}

