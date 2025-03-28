import {Injectable, signal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MenuService {

  opened = signal<boolean>(true)

  toggle() {
    this.opened.update(value => !value)
  }
}
