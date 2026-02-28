import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetLogListComponent } from './asset-log-list.component';

describe('AssetLogListComponent', () => {
  let component: AssetLogListComponent;
  let fixture: ComponentFixture<AssetLogListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetLogListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetLogListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
