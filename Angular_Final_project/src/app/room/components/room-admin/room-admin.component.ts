import { Component, OnInit } from '@angular/core';
import { AdminRoomService } from '../../services/admin-room.service';
import { RoomStats } from '../../../room/models/room-stats.model';

@Component({
  selector: 'app-room-admin',
  templateUrl: './room-admin.component.html',
  styleUrls: ['./room-admin.component.scss']
})
export class RoomAdminComponent implements OnInit {

  stats!: RoomStats;
  loading = true;

  constructor(private roomService: AdminRoomService) {}

  ngOnInit(): void {
  this.roomService.getStats().subscribe({
    next: (res) => {
      this.stats = res;
      this.loading = false;
    },
    error: (err) => {
      console.error('Failed to load stats', err);
      this.loading = false;
    }
  });
}

}
