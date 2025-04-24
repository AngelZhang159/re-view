import {Component, inject} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../../shared/services/search.service';
import {Router} from '@angular/router';
import {Avatar} from 'primeng/avatar';
import {AuthService} from '../../../shared/services/auth.service';
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
  domain: string = environment.domain;
  domainName: string = environment.domainName;
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
