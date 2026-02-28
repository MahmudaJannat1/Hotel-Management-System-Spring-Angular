import { Component, OnInit } from '@angular/core';
import { HousekeepingStatsDTO } from '../../models/housekeeping-stats.model';
import { HousekeepingService } from '../../services/housekeeping.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-housekeeping',
  templateUrl: './admin-housekeeping.component.html',
  styleUrls: ['./admin-housekeeping.component.scss']
})
export class AdminHousekeepingComponent implements OnInit {


  stats!: HousekeepingStatsDTO;

  constructor(
    private housekeepingService: HousekeepingService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.housekeepingService.getStats().subscribe(res => {
      this.stats = res;
    });
  }

  go(path: string) {
    this.router.navigate(['/housekeeping', path]);

  }
}


