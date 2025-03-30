import {Component, inject} from '@angular/core';
import {enviroment} from '../../../enviroments/enviroment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../services/search.service';
import {MenuService} from '../../services/menu.service';
import {Router} from '@angular/router';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-header',
  imports: [
    MatIcon,
    MatIconButton,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  domain: string = enviroment.domain;
  domainName: string = enviroment.domainName;

  router = inject(Router);
  menuService = inject(MenuService);
  searchService = inject(SearchService);

  lastQuery = "";

  onEnter($event: any) {
    const value = ($event.target as HTMLInputElement).value;
    if (value != this.lastQuery) {
      this.lastQuery = value
      this.searchService.setSearchQuery(value);
      this.router.navigate(['/app', {outlets: {content: 'search'}}], {queryParams: {query: value}});
    }
  }
}
