import {Component, inject} from '@angular/core';
import {enviroment} from '../../../enviroments/enviroment';
import {MatIcon} from '@angular/material/icon';
import {SearchService} from '../../services/search.service';
import {MenuService} from '../../services/menu.service';
import {ActivatedRoute, Router} from '@angular/router';

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

  menuService = inject(MenuService);
  router = inject(Router);
  activatedRoute = inject(ActivatedRoute);

  onEnter($event: any) {
    const value = ($event.target as HTMLInputElement).value;
    this.router.navigate(
      [{ outlets: { content: ['search'] } }],
      { relativeTo: this.activatedRoute }
    );

  }
}
