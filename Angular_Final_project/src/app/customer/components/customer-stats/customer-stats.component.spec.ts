import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerStatsComponent } from './customer-stats.component';

describe('CustomerStatsComponent', () => {
  let component: CustomerStatsComponent;
  let fixture: ComponentFixture<CustomerStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerStatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
