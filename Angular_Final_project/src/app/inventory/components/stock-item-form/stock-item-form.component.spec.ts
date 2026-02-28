import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockItemFormComponent } from './stock-item-form.component';

describe('StockItemFormComponent', () => {
  let component: StockItemFormComponent;
  let fixture: ComponentFixture<StockItemFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StockItemFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockItemFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
