import { Component, OnInit } from '@angular/core';
import { HousekeepingTask } from '../../models/housekeeping-task.model';
import { HousekeepingService } from '../../services/housekeeping.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.scss']
})
export class TasksListComponent implements OnInit {

  tasks: HousekeepingTask[] = [];
  statuses = ['PENDING', 'IN_PROGRESS', 'COMPLETED'];
  editingTaskId: number | null = null; // row-level edit

  constructor(private hkService: HousekeepingService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  loadTasks() {
    this.hkService.getAllTasks().subscribe(res => this.tasks = res);
  }

  startEdit(id: number) {
    this.editingTaskId = id;
  }

  cancelEdit() {
    this.editingTaskId = null;
    this.loadTasks(); // reload original data
  }

  updateTask(task: HousekeepingTask) {
    this.hkService.updateTask(task.id, task).subscribe({
      next: () => {
        this.editingTaskId = null;
        this.loadTasks();
      },
      error: err => console.error('Error updating task:', err)
    });
  }

  deleteTask(id: number) {
    if(confirm('Are you sure you want to delete this task?')) {
      this.hkService.deleteTask(id).subscribe(() => this.loadTasks());
    }
  }
}