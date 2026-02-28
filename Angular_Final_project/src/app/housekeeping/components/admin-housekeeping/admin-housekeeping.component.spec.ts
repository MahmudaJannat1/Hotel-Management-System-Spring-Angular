import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHousekeepingComponent } from './admin-housekeeping.component';

describe('AdminHousekeepingComponent', () => {
  let component: AdminHousekeepingComponent;
  let fixture: ComponentFixture<AdminHousekeepingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminHousekeepingComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminHousekeepingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
