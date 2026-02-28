import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssetLogFormComponent } from './asset-log-form.component';

describe('AssetLogFormComponent', () => {
  let component: AssetLogFormComponent;
  let fixture: ComponentFixture<AssetLogFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssetLogFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssetLogFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
