import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomService } from '../../services/room.service';
import { Room } from '../../models/room.model';
import { TokenService } from 'src/app/core/services/token.service';

@Component({
  selector: 'app-room-detail',
  templateUrl: './room-detail.component.html',
  styleUrls: ['./room-detail.component.scss']
})
export class RoomDetailComponent implements OnInit {

  room!: Room;
  loading = true;

  constructor(
    private route: ActivatedRoute,
    private roomService: RoomService,
    private tokenService: TokenService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    const id = idParam ? +idParam : null;

    if (!id) {
      console.error('Room ID is missing or invalid');
      return;
    }

    this.roomService.get(id).subscribe({
      next: (data: Room) => { 
        this.room = data; 
        this.loading = false; 
      },
      error: (err) => console.error('Failed to load room:', err)
    });
  }

  bookNow() {
    if (!this.tokenService.isTokenValid()) {
      this.router.navigate(
        ['/auth/login'],
        { queryParams: { redirectTo: `/booking/create?roomId=${this.room.id}` } }
      );
      return;
    }

    this.router.navigate(
      ['/booking/create'],
      { queryParams: { roomId: this.room.id } }
    );
  }

}
