import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RoomTypeService } from '../../services/room-type.service';
import { RoomType } from '../../models/room-type.model';
import { RoomDTO } from '../../models/room-dto.model';
import { AdminRoomService } from '../../services/admin-room.service';

@Component({
  selector: 'app-room-admin-form',
  templateUrl: './room-admin-form.component.html',
  styleUrls: ['./room-admin-form.component.scss']
})
export class RoomAdminFormComponent implements OnInit {

  roomForm!: FormGroup;
  roomId?: number;
  isEditMode = false;

  roomTypes: RoomType[] = [];
  statusOptions = ['AVAILABLE', 'OCCUPIED', 'BOOKED', 'MAINTENANCE'];

  selectedFiles: File[] = [];
  previewUrls: string[] = [];

  constructor(
    private fb: FormBuilder,
    private roomTypeService: RoomTypeService,
    private route: ActivatedRoute,
    private router: Router,
    private adminRoomService: AdminRoomService
  ) {}

  ngOnInit(): void {
    this.roomForm = this.fb.group({
      roomNumber: ['', Validators.required],
      floor: [null, Validators.required],
      roomTypeId: [null, Validators.required],
      status: ['AVAILABLE', Validators.required],
        pricePerNight: [null, Validators.required] // <-- add this

    });

    // Load room types
    this.roomTypeService.getAll().subscribe(types => this.roomTypes = types);

    // Edit mode
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.roomId = +idParam;
      this.isEditMode = true;

      this.adminRoomService.getById(this.roomId).subscribe(room => {
        this.roomForm.patchValue({
          roomNumber: room.roomNumber,
          floor: room.floor,
          roomTypeId: room.roomType?.id,
          status: room.status,
              pricePerNight: room.pricePerNight // <-- add this

        });

        // Optional: load existing images for preview
        if (room.images && room.images.length > 0) {
          this.previewUrls = room.images.map((img: any) => `/uploads/${img.filename}`);
        }
      });
    }
  }

  onFileSelected(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (!files) return;

    this.selectedFiles = Array.from(files);

    // Preview selected images (first image)
    this.previewUrls = [];
    for (let file of this.selectedFiles) {
      const reader = new FileReader();
      reader.onload = () => this.previewUrls.push(reader.result as string);
      reader.readAsDataURL(file);
    }
  }

//   saveRoom() {
//   if (this.roomForm.invalid) return;

//   const dto: RoomDTO = this.roomForm.value;

//   const formData = new FormData();

//   // 🔥 MUST be "room" (NOT dto)
//   formData.append(
//     'room',
//     new Blob([JSON.stringify(dto)], { type: 'application/json' })
//   );

//   // 🔥 MUST be "images"
//   this.selectedFiles.forEach(file => {
//     formData.append('images', file);
//   });

//   if (this.isEditMode && this.roomId) {
//   this.adminRoomService.updateWithFile(this.roomId, formData).subscribe(() => {
//     this.router.navigate(['/rooms/admin/list']); // ✅ FIX
//   });
// } else {
//   this.adminRoomService.createWithFile(formData).subscribe(() => {
//     this.router.navigate(['/rooms/admin/list']); // ✅ FIX
//   });
// }

//   }


saveRoom() {
  if (this.roomForm.invalid) return;

  const dto: RoomDTO = this.roomForm.value;

  // ================= UPDATE =================
  if (this.isEditMode && this.roomId) {

    // 🔥 যদি নতুন image select না করা হয়
    if (this.selectedFiles.length === 0) {
      this.adminRoomService.update(this.roomId, dto).subscribe(() => {
        this.router.navigate(['/rooms/admin/list']);
      });
      return;
    }

    // 🔥 যদি image select করা হয় → multipart
    const formData = new FormData();
    formData.append(
      'room',
      new Blob([JSON.stringify(dto)], { type: 'application/json' })
    );
    this.selectedFiles.forEach(file =>
      formData.append('images', file)
    );

    this.adminRoomService.updateWithFile(this.roomId, formData).subscribe(() => {
      this.router.navigate(['/rooms/admin/list']);
    });

    return;
  }

  // ================= CREATE =================
  const formData = new FormData();
  formData.append(
    'room',
    new Blob([JSON.stringify(dto)], { type: 'application/json' })
  );
  this.selectedFiles.forEach(file =>
    formData.append('images', file)
  );

  this.adminRoomService.createWithFile(formData).subscribe(() => {
    this.router.navigate(['/rooms/admin/list']);
  });
}

}
