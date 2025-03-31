import {Injectable, signal} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  open = signal(false)
}
