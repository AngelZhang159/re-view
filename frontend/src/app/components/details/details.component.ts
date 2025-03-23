import {Component, inject} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-details',
  imports: [],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  route = inject(ActivatedRoute)

  type = this.route.snapshot.paramMap.get("type")
  id = this.route.snapshot.paramMap.get("id")

  
}
