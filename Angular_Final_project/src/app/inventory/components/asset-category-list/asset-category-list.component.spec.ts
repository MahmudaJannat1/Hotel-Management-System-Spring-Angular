import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetCategoryListComponent } from './asset-category-list.component';

describe('AssetCategoryListComponent', () => {
  let component: AssetCategoryListComponent;
  let fixture: ComponentFixture<AssetCategoryListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetCategoryListComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetCategoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
