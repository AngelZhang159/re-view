import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServiceDownComponent } from './service-down.component';

describe('ServiceDownComponent', () => {
  let component: ServiceDownComponent;
  let fixture: ComponentFixture<ServiceDownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServiceDownComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServiceDownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
