import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MaintenanceRequest } from '../../models/maintenance-request.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-maintenance-form',
  templateUrl: './maintenance-form.component.html',
  styleUrls: ['./maintenance-form.component.scss']
})
export class MaintenanceFormComponent {


  @Input() request: MaintenanceRequest = {} as MaintenanceRequest;
  @Output() saved = new EventEmitter<void>();

  constructor(private hkService: HousekeepingService) {}

  saveMaintenance() {
    if(this.request.id) {
      this.hkService.updateMaintenanceStatus(this.request.id, this.request.status!).subscribe(() => this.saved.emit());
    } else {
      this.hkService.createMaintenance(this.request).subscribe(() => this.saved.emit());
    }
  }
}
