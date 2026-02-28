import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetCategoryFormComponent } from './asset-category-form.component';

describe('AssetCategoryFormComponent', () => {
  let component: AssetCategoryFormComponent;
  let fixture: ComponentFixture<AssetCategoryFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetCategoryFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetCategoryFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
