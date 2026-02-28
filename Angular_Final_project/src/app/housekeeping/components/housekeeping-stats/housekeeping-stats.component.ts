import { Component, OnInit } from '@angular/core';
import { HousekeepingStatsDTO } from '../../models/housekeeping-stats.model';
import { HousekeepingService } from '../../services/housekeeping.service';

@Component({
  selector: 'app-housekeeping-stats',
  templateUrl: './housekeeping-stats.component.html',
  styleUrls: ['./housekeeping-stats.component.scss']
})

export class HousekeepingStatsComponent implements OnInit {
  stats: HousekeepingStatsDTO | null = null;

  constructor(private hkService: HousekeepingService) {}

  ngOnInit(): void {
    this.hkService.getStats().subscribe(res => this.stats = res);
  }
}
