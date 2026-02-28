import { Component, OnInit } from '@angular/core';
import { RoomService } from '../../services/room.service';
import { Room } from '../../models/room.model';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { TokenService } from 'src/app/core/services/token.service';

@Component({
  selector: 'app-room-list',
  templateUrl: './room-list.component.html',
  styleUrls: ['./room-list.component.scss']
})
export class RoomListComponent implements OnInit {

  rooms: Room[] = [];
  loading = true;



  imageBaseUrl = environment.imagesUrl + 'rooms/images/';

  constructor(
    private roomService: RoomService,
    private router: Router,
      private tokenService: TokenService,
          private route: ActivatedRoute,
      
  ) {}

  ngOnInit(): void {
    this.roomService.getAll().subscribe({
      next: (data: Room[]) => {

        this.rooms = data.map(room => ({
          ...room,
          imageUrls: room.imageUrls?.length
            ? room.imageUrls.map(img => this.imageBaseUrl + img)
            : [],
              pricePerNight: room.pricePerNight // <-- explicit handle

        }));

        this.loading = false;
      },
      error: err => {
        console.error('Failed to load rooms:', err);
        this.loading = false;
      }
    });
  }

  viewDetails(room: Room) {
    if (room.id) {
      this.router.navigate(['/rooms/details', room.id]);
    }
  }

bookNow(room: Room) {
  if (!this.tokenService.isTokenValid()) {
    this.router.navigate(
      ['/auth/login'],
      { queryParams: { redirectTo: `/booking/create?roomId=${room.id}` } }
    );
    return;
  }

  this.router.navigate(
    ['/booking/create'],
    { queryParams: { roomId: room.id } }
  );
}








// default temporary images
defaultImages: string[] = [
  'assets/room3.jpg',
  'assets/r1.jpg',
  'assets/room4.jpg'
];

getRoomImage(room: Room): string {
  if (room.imageUrls && room.imageUrls.length > 0) {
    return room.imageUrls[0]; // backend image thakle first image
  }
  // na thakle default image randomly choose
  const randomIndex = Math.floor(Math.random() * this.defaultImages.length);
  return this.defaultImages[randomIndex];
}


}
