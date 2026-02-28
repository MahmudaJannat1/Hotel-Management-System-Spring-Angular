import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminEmployeeDashboardComponent } from './admin-employee-dashboard.component';

describe('AdminEmployeeDashboardComponent', () => {
  let component: AdminEmployeeDashboardComponent;
  let fixture: ComponentFixture<AdminEmployeeDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminEmployeeDashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminEmployeeDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
