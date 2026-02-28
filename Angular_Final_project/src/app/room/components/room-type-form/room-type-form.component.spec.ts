import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomTypeFormComponent } from './room-type-form.component';

describe('RoomTypeFormComponent', () => {
  let component: RoomTypeFormComponent;
  let fixture: ComponentFixture<RoomTypeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoomTypeFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomTypeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
