import {inject, Injectable, Injector, runInInjectionContext, signal} from '@angular/core';
import {SearchMultiResponse} from '../models/search-multi-response';
import {MediaService} from './media.service';
import {toObservable} from '@angular/core/rxjs-interop';
import {debounceTime, distinctUntilChanged} from 'rxjs';

@Injectable({
      providedIn: 'root'
    })
    export class SearchService {
      private injector = inject(Injector);
      mediaService = inject(MediaService);
      searchQuery = signal<string>('');
      result = signal<SearchMultiResponse>({
        page: 0,
        results: [],
        totalResults: 0,
        totalPages: 0
      });

      setSearchQuery(query: string) {
        if (query != this.searchQuery()) {
          this.searchQuery.set(query);
          this.callApi();
        }
      }

      private callApi() {
        runInInjectionContext(this.injector, () => {
          toObservable(this.searchQuery)
            .pipe(
              debounceTime(300),
              distinctUntilChanged()
            )
            .subscribe(query => {
              this.mediaService.searchMulti(query).subscribe(response => {
                this.result.set(response);
              });
            });
        });
      }
    }
