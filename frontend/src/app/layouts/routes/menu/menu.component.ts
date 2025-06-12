import {Component, inject} from '@angular/core';
import {MatListItem, MatListItemIcon, MatNavList} from '@angular/material/list';
import {RouterLink} from '@angular/router';
import {MatIcon} from '@angular/material/icon';
import {MenuService} from '../../../core/services/menu.service';
import {MenuList} from '../../../core/models/menu';
import {NgStyle} from '@angular/common';
import {MatDivider} from '@angular/material/divider';
import {MatDialog} from '@angular/material/dialog';
import {SettingsComponent} from '../../components/settings/settings.component';

@Component({
  selector: 'app-menu',
  imports: [
    MatNavList,
    MatListItem,
    MatIcon,
    RouterLink,
    MatListItemIcon,
    MatDivider,
    NgStyle,
  ],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.css'
})

export class MenuComponent {

  menuService = inject(MenuService);
  dialog = inject(MatDialog);

  routeList: MenuList[] = [
    {
      label: 'Home',
      route: '/app',
      icon: 'home'
    },
    // {
    //   label: 'Search',
    //   route: '/app/search',
    //   icon: 'search'
    // },
    {
      label: 'Reviews',
      route: [{outlets: {content: ["reviews"]}}],
      icon: 'star'
    },
    // {
    //   label: 'Friends',
    //   route: [{outlets: {content: ["friends"]}}],
    //   icon: 'people',
    // },
    {
      label: 'Settings',
      // route: [{outlets: {content: ["settings"]}}],
      icon: 'settings',
      action: () => {
        this.dialog.open(SettingsComponent)
      }
    },
    {
      label: 'Close',
      icon: 'menu',
      action: () => {
        this.menuService.toggle();
      }
    }
  ]
}
