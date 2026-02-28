import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AssetMaintenanceLog } from '../../models/asset-maintenance-log.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-asset-log-form',
  templateUrl: './asset-log-form.component.html',
  styleUrls: ['./asset-log-form.component.scss']
})
export class AssetLogFormComponent {


  @Input() log: AssetMaintenanceLog = {} as AssetMaintenanceLog;
  @Output() saved = new EventEmitter<void>();

  constructor(private hkService: HousekeepingService) {}

  saveLog() {
    this.hkService.createAssetLog(this.log).subscribe(() => this.saved.emit());
  }
}
