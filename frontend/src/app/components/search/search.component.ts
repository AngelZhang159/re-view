import {Component, inject, OnInit, WritableSignal} from '@angular/core';
import {ActivatedRoute, RouterLink} from '@angular/router';
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
export class SearchComponent implements OnInit {

  private route = inject(ActivatedRoute);
  query = this.route.snapshot.queryParamMap.get('query');

  searchService = inject(SearchService);
  protected result: WritableSignal<SearchMultiResponse> | undefined;

  ngOnInit(): void {
    this.result = this.searchService.result;
    this.searchService.setSearchQuery(this.query ? this.query : '');
  }
}
