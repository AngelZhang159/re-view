import {Component} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {enviroment} from './components/enviroments/enviroment';
import {HeaderComponent} from './components/header/header.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
}
