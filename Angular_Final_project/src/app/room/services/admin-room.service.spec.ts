import { TestBed } from '@angular/core/testing';

import { AdminRoomService } from './admin-room.service';

describe('AdminRoomService', () => {
  let service: AdminRoomService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminRoomService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
