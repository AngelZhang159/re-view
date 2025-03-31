import {Component, inject, OnInit} from '@angular/core';
import {MenuItem} from 'primeng/api';
import {MatDivider, MatListItem, MatListItemIcon, MatNavList} from '@angular/material/list';
import {RouterLink} from '@angular/router';
import {MatIcon} from '@angular/material/icon';
import {MenuService} from '../../services/menu.service';
import {menuList} from '../../models/menu';

@Component({
  selector: 'app-menu',
  imports: [
    MatNavList,
    MatListItem,
    MatIcon,
    RouterLink,
    MatListItemIcon,
    MatDivider,

  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})

export class MenuComponent implements OnInit {

  menuService = inject(MenuService);

  items: MenuItem[] = [];
  routeList: menuList[] = [
    {
      label: 'Home',
      route: '/app',
      icon: 'home'
    },
    {
      label: 'Search',
      route: '/app/search',
      icon: 'search'
    },
    {
      label: 'Reviews',
      route: '/app/reviews',
      icon: 'star'
    },
    {
      label: 'Friends',
      route: '/app/friends',
      icon: 'people'
    },
    {
      label: this.menuService.opened() ? 'Close' : '',
      icon: this.menuService.opened() ? 'close' : 'menu',
      route: '',
      action: () => {
        this.menuService.toggle();
      }
    },
    {
      label: 'Settings',
      route: '/app/settings',
      icon: 'settings'
    }
  ]

  ngOnInit(): void {
    this.items = [
      {
        label: 'Home',
        icon: 'pi pi-home',
        routerLink: '/app'
      },
      {
        label: 'Search',
        icon: 'pi pi-search',
        routerLink: '/app/search'
      },
      {
        label: 'Reviews',
        icon: 'pi pi-star',
        // items: [
        //   {
        //     label: 'Movies & Series',
        //     icon: 'pi pi-star',
        //     routerLink: '/app/reviews/movies',
        //   },
        // ]
      },
      {
        label: 'Friends',
        icon: 'pi pi-users',
        routerLink: '/app/friends',
      },
      {
        label: 'Settings',
        icon: 'pi pi-cog',
        routerLink: '/app/settings',
      }
    ]
  }

}
