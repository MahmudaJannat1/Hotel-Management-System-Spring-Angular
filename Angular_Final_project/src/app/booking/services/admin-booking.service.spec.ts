import { TestBed } from '@angular/core/testing';

import { AdminBookingService } from './admin-booking.service';

describe('AdminBookingService', () => {
  let service: AdminBookingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminBookingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
