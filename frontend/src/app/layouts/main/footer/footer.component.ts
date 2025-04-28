import { Component } from '@angular/core';
import {environment} from '../../../../environments/environment';
import {MatDivider} from '@angular/material/divider';

@Component({
  selector: 'app-footer',
  imports: [
    MatDivider
  ],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  date = new Date()
  protected readonly environment = environment;
}
