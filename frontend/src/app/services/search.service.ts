import {inject, Injectable, runInInjectionContext, signal, Injector} from '@angular/core';
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
        total_results: 0,
        total_pages: 0
      });

      setSearchQuery(query: string) {
        console.log("Searching: " + query);
        this.searchQuery.set(query);
        this.callApi(query);
      }

      private callApi(query: string) {
        runInInjectionContext(this.injector, () => {
          toObservable(this.searchQuery)
            .pipe(
              debounceTime(300),
              distinctUntilChanged()
            )
            .subscribe(query => {
              this.mediaService.searchMulti(query).subscribe(response => {
                this.result.set(response);
                console.log("Sending search query SIGNAL")
              });
            });
        });
      }
    }
