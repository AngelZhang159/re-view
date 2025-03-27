import {Component, inject} from '@angular/core';
import {enviroment} from '../../../enviroments/enviroment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../services/search.service';

@Component({
  selector: 'app-header',
  imports: [
    MatIcon
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  domain: string = enviroment.domain;
  domainName: string = enviroment.domainName;

  searchService = inject(SearchService);


  onSearch($event: KeyboardEvent) {
    const value = ($event.target as HTMLInputElement).value;
    this.searchService.setSearchQuery(value);
  }
}
