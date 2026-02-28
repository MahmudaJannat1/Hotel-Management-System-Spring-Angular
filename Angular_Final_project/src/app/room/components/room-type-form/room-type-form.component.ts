// src/app/room/components/room-type-form/room-type-form.component.ts
import { Component } from '@angular/core';
import { RoomTypeService } from '../../services/room-type.service';
import { Router } from '@angular/router';
import { RoomType } from '../../models/room-type.model';

@Component({
  selector: 'app-room-type-form',
  templateUrl: './room-type-form.component.html',
  styleUrls: ['./room-type-form.component.scss']
})
export class RoomTypeFormComponent {
  roomType: RoomType = { name: '', description: '' };
  loading = false;

  constructor(private roomTypeService: RoomTypeService, private router: Router) {}

  createRoomType() {
    if (!this.roomType.name) return alert('Name is required!');
    this.loading = true;
    this.roomTypeService.create(this.roomType).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/room/room-types']);
      },
      error: (err) => {
        this.loading = false;
        console.error(err);
      }
    });
  }
}
