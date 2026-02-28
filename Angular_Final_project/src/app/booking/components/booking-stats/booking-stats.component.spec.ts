import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookingStatsComponent } from './booking-stats.component';

describe('BookingStatsComponent', () => {
  let component: BookingStatsComponent;
  let fixture: ComponentFixture<BookingStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookingStatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookingStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
