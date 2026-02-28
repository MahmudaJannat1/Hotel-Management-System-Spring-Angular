import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomAdminFormComponent } from './room-admin-form.component';

describe('RoomAdminFormComponent', () => {
  let component: RoomAdminFormComponent;
  let fixture: ComponentFixture<RoomAdminFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoomAdminFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomAdminFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
