import {Component, inject} from '@angular/core';
import {enviroment} from '../../../enviroments/enviroment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../services/search.service';
import {Router} from '@angular/router';
import {Avatar} from 'primeng/avatar';
import {AuthService} from '../../services/auth.service';
import {MatDialog} from '@angular/material/dialog';
import {ProfileComponent} from '../profile/profile.component';

@Component({
  selector: 'app-header',
  imports: [
    MatIcon,
    Avatar,

  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  domain: string = enviroment.domain;
  domainName: string = enviroment.domainName;
  dialog = inject(MatDialog);

  router = inject(Router);
  searchService = inject(SearchService);
  authService = inject(AuthService);

  onEnter($event: any) {
    const value = ($event.target as HTMLInputElement).value;

    this.searchService.setSearchQuery(value);
    this.router.navigate(['/app', {outlets: {content: 'search'}}], {queryParams: {query: value}});
  }

  openProfile() {
    this.dialog.open(ProfileComponent)
  }
}
