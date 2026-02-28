import { Component, EventEmitter, Input, Output } from '@angular/core';
import { HousekeepingTask } from '../../models/housekeeping-task.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-tasks-form',
  templateUrl: './tasks-form.component.html',
  styleUrls: ['./tasks-form.component.scss']
})
export class TasksFormComponent {


  @Input() task: HousekeepingTask = {} as HousekeepingTask;
  @Output() saved = new EventEmitter<void>();

  constructor(private hkService: HousekeepingService) {}

  saveTask() {
    if(this.task.id) {
      this.hkService.updateTask(this.task.id, this.task).subscribe(() => this.saved.emit());
    } else {
      this.hkService.createTask(this.task).subscribe(() => this.saved.emit());
    }
  }
}
