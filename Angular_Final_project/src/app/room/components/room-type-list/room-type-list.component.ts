// src/app/room/components/room-type-list/room-type-list.component.ts
import { Component, OnInit } from '@angular/core';
import { RoomTypeService } from '../../services/room-type.service';
import { RoomType } from '../../models/room-type.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-room-type-list',
  templateUrl: './room-type-list.component.html',
  styleUrls: ['./room-type-list.component.scss']
})
export class RoomTypeListComponent implements OnInit {
  roomTypes: RoomType[] = [];
  loading = true;

  constructor(private roomTypeService: RoomTypeService, private router: Router) {}

  ngOnInit(): void {
    this.loadRoomTypes();
  }

  loadRoomTypes() {
    this.loading = true;
    this.roomTypeService.getAll().subscribe({
      next: (data) => {
        this.roomTypes = data;
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  goToCreate() {
    this.router.navigate(['/room/room-types/create']);
  }
}
