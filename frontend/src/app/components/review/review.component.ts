import {Component} from '@angular/core';
import {Tab, TabList, TabPanel, TabPanels, Tabs} from 'primeng/tabs';

@Component({
  selector: 'app-review',
  imports: [
    Tabs,
    TabList,
    TabPanels,
    TabPanel,
    Tab
  ],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent {
  tabs = [
    {
      title: 'ALL',
      value: 'all',
      content: 'All reviews content goes here'
    },
    {
      title: 'MOVIES',
      value: 'movies',
      content: 'My reviews content goes here'
    },
    {
      title: 'SERIES',
      value: 'series',
      content: 'Friends reviews content goes here'
    }
  ];

}
