import { TestBed } from '@angular/core/testing';

import { AdminStatisticsService } from './admin-statistics.service';

describe('AdminStatisticsService', () => {
  let service: AdminStatisticsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminStatisticsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
