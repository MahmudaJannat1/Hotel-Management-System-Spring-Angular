import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LaundryRequest } from '../../models/laundry-request.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-laundry-form',
  templateUrl: './laundry-form.component.html',
  styleUrls: ['./laundry-form.component.scss']
})
export class LaundryFormComponent {


  @Input() request: LaundryRequest = {} as LaundryRequest;
  @Output() saved = new EventEmitter<void>();

  constructor(private hkService: HousekeepingService) {}

  saveLaundry() {
    if(this.request.id) {
      this.hkService.updateLaundryStatus(this.request.id, this.request.status!).subscribe(() => this.saved.emit());
    } else {
      this.hkService.createLaundry(this.request).subscribe(() => this.saved.emit());
    }
  }
}
