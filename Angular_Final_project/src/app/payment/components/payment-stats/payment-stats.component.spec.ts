import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentStatsComponent } from './payment-stats.component';

describe('PaymentStatsComponent', () => {
  let component: PaymentStatsComponent;
  let fixture: ComponentFixture<PaymentStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentStatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
