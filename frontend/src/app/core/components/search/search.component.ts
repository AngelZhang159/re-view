import {Component, inject, OnInit, WritableSignal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {MatCard, MatCardContent, MatCardFooter, MatCardHeader, MatCardTitle} from '@angular/material/card';
import {MatChip, MatChipSet} from '@angular/material/chips';
import {NgOptimizedImage} from '@angular/common';
import {SearchMultiResponse} from '../../../models/search-multi-response';
import {SearchService} from '../../../shared/services/search.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DetailsComponent} from '../details/details.component';
import {MediaService} from '../../../shared/services/media.service';
import {MatBottomSheet} from '@angular/material/bottom-sheet';

@Component({
  selector: 'app-search',
  imports: [
    FormsModule,
    ReactiveFormsModule,
    NgOptimizedImage,
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
  mediaService = inject(MediaService);
  private bottomSheet = inject(MatBottomSheet)

  searchService = inject(SearchService);
  protected result: WritableSignal<SearchMultiResponse> | undefined;

  ngOnInit(): void {
    this.result = this.searchService.result;
    this.searchService.setSearchQuery(this.query ? this.query : '');
  }

  openDrawer(mediaType: any, id: number) {
    if (id != null || id != undefined && mediaType != null || mediaType != undefined) {
      this.mediaService.getDetails(mediaType, id).subscribe(data => {
        this.bottomSheet.open(DetailsComponent, {data: {details: data}, height: '80vh'});
      })
    }
  }
}
