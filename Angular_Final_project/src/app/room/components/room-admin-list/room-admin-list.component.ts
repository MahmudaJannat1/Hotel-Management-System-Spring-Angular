import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminRoomService } from '../../services/admin-room.service';
import { Room } from '../../../room/models/room.model';

@Component({
  selector: 'app-room-admin-list',
  templateUrl: './room-admin-list.component.html',
  styleUrls: ['./room-admin-list.component.scss']

})
export class RoomAdminListComponent implements OnInit {

  rooms: Room[] = [];
  loading = true;

  constructor(
    private roomService: AdminRoomService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadRooms();
  }

loadRooms() {
  this.loading = true;

  this.roomService.getAll().subscribe({
    next: res => {
      this.rooms = res;
      this.loading = false;
    },
    error: err => {
      console.error('Failed to load rooms', err);
      this.loading = false;
    }
  });
}


  deleteRoom(id: number) {
    if(confirm('Disable this room?')) {
      this.roomService.delete(id).subscribe(() => {
        this.loadRooms();
      });
    }
  }

  editRoom(id: number) {
    this.router.navigate(['/rooms/admin/edit', id]);
  }

  uploadRoomImage(id: number) {
    this.router.navigate(['/rooms/admin/upload-image', id]);
  }

  createRoom() {
    this.router.navigate(['/rooms/admin/create']);
  }
}
