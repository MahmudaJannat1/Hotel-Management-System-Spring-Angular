import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TasksDetailComponent } from './tasks-detail.component';

describe('TasksDetailComponent', () => {
  let component: TasksDetailComponent;
  let fixture: ComponentFixture<TasksDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TasksDetailComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TasksDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
