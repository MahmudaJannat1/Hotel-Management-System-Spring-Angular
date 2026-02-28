import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LaundryListComponent } from './laundry-list.component';

describe('LaundryListComponent', () => {
  let component: LaundryListComponent;
  let fixture: ComponentFixture<LaundryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LaundryListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LaundryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
