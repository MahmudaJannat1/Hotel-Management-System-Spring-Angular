import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomTypeListComponent } from './room-type-list.component';

describe('RoomTypeListComponent', () => {
  let component: RoomTypeListComponent;
  let fixture: ComponentFixture<RoomTypeListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RoomTypeListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RoomTypeListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
