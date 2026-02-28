import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HousekeepingStatsComponent } from './housekeeping-stats.component';

describe('HousekeepingStatsComponent', () => {
  let component: HousekeepingStatsComponent;
  let fixture: ComponentFixture<HousekeepingStatsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HousekeepingStatsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HousekeepingStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
