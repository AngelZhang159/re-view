import {Component, inject, input} from '@angular/core';
import {RouterLink} from '@angular/router';
import {MatCard, MatCardContent, MatCardFooter, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {NgOptimizedImage} from '@angular/common';
import {SearchMultiResponse} from '../../models/search-multi-response';
import {SearchService} from '../../services/search.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-search',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgOptimizedImage,
    RouterLink,
    MatCardHeader,
    MatCard,
    MatCardTitle,
    MatCardContent,
    MatCardFooter,
    MatChipSet,
    MatChip,

  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {

  query = input.required<string>();

  searchService = inject(SearchService);

  public result: SearchMultiResponse = {
    page: 0,
    results: [],
    total_results: 0,
    total_pages: 0
  };

  constructor() {
    console.log("SEARCH COMPONENT CONSTRUCTOR")
    this.searchService.setSearchQuery(this.query());
    this.result = this.searchService.result();
  }
}
