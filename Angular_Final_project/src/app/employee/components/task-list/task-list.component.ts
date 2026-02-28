import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';
import { EmployeeTask, TaskStatusEnum, TaskTypeEnum } from '../../models/employeetask.model';
import { TaskService } from '../../services/task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
    styleUrls:['./task-list.component.scss']

})
export class TaskListComponent implements OnInit {
  tasks: EmployeeTask[] = [];
  statuses = Object.values(TaskStatusEnum);
  taskTypes = Object.values(TaskTypeEnum);

  constructor(private service: TaskService, private router: Router) {}

  ngOnInit(): void {
    this.load();
  }

  load() {
    this.service.getAll().subscribe(res => this.tasks = res);
  }

  edit(id: number) {
    this.router.navigate(['/employee/admin/tasks/edit', id]);
  }

updateStatus(task: EmployeeTask, status: TaskStatusEnum) {
  this.service.updateStatus(task.id!, status).subscribe(() => this.load());
}

}
