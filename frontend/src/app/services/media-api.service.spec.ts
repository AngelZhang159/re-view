import { TestBed } from '@angular/core/testing';

import { MediaApiService } from './media-api.service';

describe('MediaService', () => {
  let service: MediaApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MediaApiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
