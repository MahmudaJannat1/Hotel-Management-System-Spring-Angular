import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StockTransactionListComponent } from './stock-transaction-list.component';

describe('StockTransactionListComponent', () => {
  let component: StockTransactionListComponent;
  let fixture: ComponentFixture<StockTransactionListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StockTransactionListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StockTransactionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
