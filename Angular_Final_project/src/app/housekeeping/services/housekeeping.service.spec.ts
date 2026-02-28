import { TestBed } from '@angular/core/testing';

import { HousekeepingService } from './housekeeping.service';

describe('HousekeepingService', () => {
  let service: HousekeepingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HousekeepingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
