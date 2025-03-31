import {Component, inject} from '@angular/core';
import {enviroment} from '../../../enviroments/enviroment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../services/search.service';
import {Router, RouterLink} from '@angular/router';
import {Avatar} from 'primeng/avatar';
import {AuthService} from '../../services/auth.service';
import {Dialog} from 'primeng/dialog';
import {ProfileComponent} from '../profile/profile.component';
import {ProfileService} from '../../services/profile.service';

@Component({
  selector: 'app-header',
  imports: [
    MatIcon,
    Avatar,
    RouterLink,
    Dialog,
    ProfileComponent,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  domain: string = enviroment.domain;
  domainName: string = enviroment.domainName;

  router = inject(Router);
  searchService = inject(SearchService);
  authService = inject(AuthService);
  profileService = inject(ProfileService);

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
