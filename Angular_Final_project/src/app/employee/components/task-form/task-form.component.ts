import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeTask, TaskStatusEnum, TaskTypeEnum } from '../../models/employeetask.model';
import { TaskService } from '../../services/task.service';
import { EmployeeService } from '../../services/employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.scss']
})
export class TaskFormComponent  implements OnInit{
 form!: FormGroup;
  taskId?: number;
  taskTypes = Object.values(TaskTypeEnum);
  statuses = Object.values(TaskStatusEnum);
  employees: any[] = []; // get employee list

  constructor(
    private fb: FormBuilder,
    private service: TaskService,
    private employeeService: EmployeeService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.taskId = Number(this.route.snapshot.paramMap.get('id'));
    this.form = this.fb.group({
      employeeId: [null, Validators.required],
      taskType: [null, Validators.required],
      description: ['', Validators.required],
      status: [TaskStatusEnum.PENDING]
    });

    // load employees
    this.employeeService.getAll().subscribe(res => this.employees = res);

   if (this.taskId) {
  this.service.getAll().subscribe(tasks => {
    const t = tasks.find(x => x.id === this.taskId);
    if (t) {
      this.form.patchValue({
        employeeId: t.employeeId,
        taskType: t.taskType,
        description: t.description,
        status: t.status
      });
    }
  });
}

  }
submit() {
  if (this.form.invalid) return;

  const dto: EmployeeTask = this.form.value;

  if (this.taskId) {
    // update task status only (backend method supported)
    this.service.updateStatus(this.taskId, dto.status).subscribe(() => {
      this.router.navigate(['/employee/admin/tasks']);
    });
  } else {
    // create new task
    this.service.create(dto).subscribe(() => {
      this.router.navigate(['/employee/admin/tasks']);
    });
  }
}


}
