// // import { Component, OnInit } from '@angular/core';
// // import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
// // import { ActivatedRoute, Router } from '@angular/router';
// // import { BookingService } from '../../services/booking.service';
// // import { BookingRequest, BookingItem } from '../../models/booking-request';
// // import { RoomService } from 'src/app/room/services/room.service';
// // import { BookingResponse, RoomType } from '../../models/booking-response'; // ✅ import

// // @Component({
// //   selector: 'app-booking-form',
// //   templateUrl: './booking-form.component.html',
// //   styleUrls: ['./booking-form.component.scss'],
// // })
// // export class BookingFormComponent implements OnInit {
// //   bookingForm!: FormGroup;
// //   bookingCode?: string;
// //   isEditMode = false;
// //   rooms: any[] = [];
// //   bookingItemOptions: string[] = [];
// //   roomTypes = Object.values(RoomType); // enum values for dropdown



// //   constructor(
// //     private fb: FormBuilder,
// //     private bookingService: BookingService,
// //     private roomService: RoomService,
// //     private route: ActivatedRoute,
// //       // private userService: UserService, // ✅

// //     private router: Router
// //   ) { }

// //   ngOnInit(): void {
// //     // this.bookingForm = this.fb.group({
// //     //   roomId: [null, Validators.required],
// //     //   customerId: [null, Validators.required],
// //     //   checkInDate: ['', Validators.required],
// //     //   checkOutDate: ['', Validators.required],
// //     //   adults: [1, [Validators.required, Validators.min(1)]],   // new
// //     //   children: [0, [Validators.required, Validators.min(0)]], // new
// //     //   roomType: ['', Validators.required],                     // new
// //     //   items: this.fb.array([])
// //     // });

// //   this.bookingForm = this.fb.group({
// //     roomId: [null, Validators.required],
// //     roomType: ['', Validators.required],
// //     checkInDate: ['', Validators.required],
// //     checkOutDate: ['', Validators.required],
// //     adults: [1, [Validators.required, Validators.min(1)]],
// //     children: [0, [Validators.required, Validators.min(0)]],
// //     items: this.fb.array([])
// //   });

// //   // 2️⃣ এখন patchValue
// //   const roomIdFromQuery = this.route.snapshot.queryParamMap.get('roomId');
// //   if (roomIdFromQuery) {
// //     this.bookingForm.patchValue({
// //       roomId: Number(roomIdFromQuery)
// //     });
// //   }




// //     // ক্যালকুলেট nights যখন date change হয়
// //     this.bookingForm.get('checkInDate')?.valueChanges.subscribe(() => this.calculateNights());
// //     this.bookingForm.get('checkOutDate')?.valueChanges.subscribe(() => this.calculateNights());

// //     // Load rooms
// //     this.roomService.getAll().subscribe((res) => {
// //       this.rooms = res;
// //     });

// //     // Load booking items
// //     this.bookingService.getBookingItems().subscribe({
// //       next: (data) => {
// //         this.bookingItemOptions = data;
// //       },
// //       error: () => {
// //         alert('Failed to load booking items');
// //       },
// //     });

// //     // Edit mode
// //     this.bookingCode = this.route.snapshot.paramMap.get('code')!;
// //     if (this.bookingCode) {
// //       this.isEditMode = true;

// //       this.bookingService
// //         .getByCode(this.bookingCode)
// //         .subscribe((booking: BookingResponse) => {
// //           this.bookingForm.patchValue({
// //             roomId: booking.roomId,
// //             checkInDate: booking.checkInDate,
// //             checkOutDate: booking.checkOutDate,
// //             adults: booking.adults,
// //             children: booking.children,
// //             roomType: booking.roomType
// //           });


// //           const itemsArray = this.bookingForm.get('items') as FormArray;
// //           booking.items.forEach((item) => {
// //             itemsArray.push(
// //               this.fb.group({
// //                 itemName: item.itemName,
// //                 quantity: item.quantity,
// //                 category: item.category || '',
// //                 description: item.description || '',
// //                 price: item.price,
// //               })
// //             );
// //           });

// //           this.calculateNights(); // edit mode
// //         });
// //     }
// //   }

// //   calculateNights() {
// //     const checkIn = this.bookingForm.get('checkInDate')?.value;
// //     const checkOut = this.bookingForm.get('checkOutDate')?.value;
// //     if (checkIn && checkOut) {
// //       const diff = Math.ceil(
// //         (new Date(checkOut).getTime() - new Date(checkIn).getTime()) / (1000 * 60 * 60 * 24)
// //       );
// //       this.bookingForm.get('numberOfNights')?.setValue(diff > 0 ? diff : 0);
// //     }
// //   }

// //   get items() {
// //     return this.bookingForm.get('items') as FormArray;
// //   }

// //   // addItem() {
// //   //   this.items.push(this.fb.group({
// //   //     itemName: '',
// //   //     quantity: 1,
// //   //     price: 0
// //   //   }));
// //   // }

// //   addItem() {
// //     this.items.push(
// //       this.fb.group({
// //         itemName: ['', Validators.required],
// //         quantity: [1, Validators.required],
// //         category: [''],
// //         description: [''],
// //       })
// //     );
// //   }

// //   removeItem(index: number) {
// //     this.items.removeAt(index);
// //   }

// //   saveBooking() {
// //     if (this.bookingForm.invalid) return;

// //     const dto: BookingRequest = this.bookingForm.value;

// //     if (!this.isEditMode) {
// //       this.bookingService.create(dto).subscribe({
// //         next: (res) => {
// //           alert('Booking created successfully');
// //           this.router.navigate(['/booking/confirmation', res.bookingCode]);
// //         },
// //         error: () => alert('Booking creation failed'),
// //       });
// //     } else {
// //       this.bookingService.update(this.bookingCode!, dto).subscribe({
// //         next: () => {
// //           alert('Booking updated successfully');
// //           this.router.navigate(['/admin/bookings']);
// //         },
// //         error: () => alert('Failed to update booking'),
// //       });
// //     }
// //   }

// // // saveBooking() {
// // //   if (this.bookingForm.invalid) return;

// // //   const dto: BookingRequest = this.bookingForm.value;

// // //   this.bookingService.create(dto).subscribe({
// // //     next: (res) => {
// // //       alert('Booking created successfully');
// // //       this.router.navigate(['/booking/confirmation', res.bookingCode]);
// // //     },
// // //     error: () => alert('Booking creation failed'),
// // //   });
// // // }

// // }
// import { Component, OnInit } from '@angular/core';
// import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
// import { ActivatedRoute, Router } from '@angular/router';
// import { BookingService } from '../../services/booking.service';
// import { RoomService } from 'src/app/room/services/room.service';
// import { BookingRequest } from '../../models/booking-request';
// import { BookingResponse, RoomType } from '../../models/booking-response';

// @Component({
//   selector: 'app-booking-form',
//   templateUrl: './booking-form.component.html',
//   styleUrls: ['./booking-form.component.scss'],
// })
// export class BookingFormComponent implements OnInit {
//   bookingForm!: FormGroup;
//   bookingCode?: string;
//   isEditMode = false;
//   rooms: any[] = [];
//   bookingItemOptions: string[] = [];
//   roomTypes = Object.values(RoomType);

//   constructor(
//     private fb: FormBuilder,
//     private bookingService: BookingService,
//     private roomService: RoomService,
//     private route: ActivatedRoute,
//     private router: Router
//   ) {}

//   ngOnInit(): void {
//     // --- Form Initialization ---
//     this.bookingForm = this.fb.group({
//       roomId: [null, Validators.required],
//       customerId: [null, Validators.required],
//       roomType: ['', Validators.required],
//       checkInDate: ['', Validators.required],
//       checkOutDate: ['', Validators.required],
//       adults: [1, [Validators.required, Validators.min(1)]],
//       children: [0, [Validators.required, Validators.min(0)]],
//       items: this.fb.array([]),
//     });

//     // --- Patch roomId from queryParams if exists ---
//     const roomIdFromQuery = this.route.snapshot.queryParamMap.get('roomId');
//     if (roomIdFromQuery) {
//       this.bookingForm.patchValue({ roomId: Number(roomIdFromQuery) });
//     }

//     // --- Patch customerId from queryParams if exists ---
//     const customerIdFromQuery = this.route.snapshot.queryParamMap.get('customerId');
//     if (customerIdFromQuery) {
//       this.bookingForm.patchValue({ customerId: Number(customerIdFromQuery) });
//     }

//     // --- Load Rooms ---
//     this.roomService.getAll().subscribe(res => {
//       this.rooms = res;
//     });

//     // --- Load Booking Items ---
//     this.bookingService.getBookingItems().subscribe({
//       next: data => this.bookingItemOptions = data,
//       error: () => alert('Failed to load booking items'),
//     });

//     // --- Edit Mode ---
//     this.bookingCode = this.route.snapshot.paramMap.get('code')!;
//     if (this.bookingCode) {
//       this.isEditMode = true;
//       this.bookingService.getByCode(this.bookingCode).subscribe((booking: BookingResponse) => {
//         this.bookingForm.patchValue({
//           roomId: booking.roomId,
//           customerId: booking.customerId,
//           roomType: booking.roomType,
//           checkInDate: booking.checkInDate,
//           checkOutDate: booking.checkOutDate,
//           adults: booking.adults,
//           children: booking.children,
//         });

//         const itemsArray = this.bookingForm.get('items') as FormArray;
//         booking.items.forEach(item => {
//           itemsArray.push(this.fb.group({
//             itemName: item.itemName,
//             quantity: item.quantity,
//             category: item.category || '',
//             description: item.description || '',
//             price: item.price,
//           }));
//         });
//       });
//     }

//     // --- Calculate Nights on date change ---
//     this.bookingForm.get('checkInDate')?.valueChanges.subscribe(() => this.calculateNights());
//     this.bookingForm.get('checkOutDate')?.valueChanges.subscribe(() => this.calculateNights());
//   }

//   // --- FormArray Getter ---
//   get items() {
//     return this.bookingForm.get('items') as FormArray;
//   }

//   addItem() {
//     this.items.push(this.fb.group({
//       itemName: ['', Validators.required],
//       quantity: [1, Validators.required],
//       category: [''],
//       description: [''],
//     }));
//   }

//   removeItem(index: number) {
//     this.items.removeAt(index);
//   }

//   calculateNights() {
//     const checkIn = this.bookingForm.get('checkInDate')?.value;
//     const checkOut = this.bookingForm.get('checkOutDate')?.value;
//     if (checkIn && checkOut) {
//       const diff = Math.ceil(
//         (new Date(checkOut).getTime() - new Date(checkIn).getTime()) / (1000 * 60 * 60 * 24)
//       );
//       this.bookingForm.patchValue({ numberOfNights: diff > 0 ? diff : 0 });
//     }
//   }

//   saveBooking() {
//     if (this.bookingForm.invalid) {
//       alert('Please fill all required fields');
//       return;
//     }

//     const dto: BookingRequest = this.bookingForm.value;

//     if (!this.isEditMode) {
//       this.bookingService.create(dto).subscribe({
//         next: res => {
//           alert('Booking created successfully');
//           this.router.navigate(['/booking/confirmation', res.bookingCode]);
//         },
//         error: err => {
//           console.error(err);
//           alert('Booking creation failed');
//         },
//       });
//     } else {
//       this.bookingService.update(this.bookingCode!, dto).subscribe({
//         next: () => {
//           alert('Booking updated successfully');
//           this.router.navigate(['/admin/bookings']);
//         },
//         error: err => {
//           console.error(err);
//           alert('Failed to update booking');
//         },
//       });
//     }
//   }
// }



import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookingService } from '../../services/booking.service';
import { RoomService } from 'src/app/room/services/room.service';
import { BookingRequest } from '../../models/booking-request';
import { BookingResponse, RoomType } from '../../models/booking-response';

@Component({
  selector: 'app-booking-form',
  templateUrl: './booking-form.component.html',
  styleUrls: ['./booking-form.component.scss'],
})
export class BookingFormComponent implements OnInit {
  bookingForm!: FormGroup;
  bookingCode?: string;
  isEditMode = false;
  rooms: any[] = [];
  bookingItemOptions: string[] = [];
  roomTypes = Object.values(RoomType);

  constructor(
    private fb: FormBuilder,
    private bookingService: BookingService,
    private roomService: RoomService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

ngOnInit(): void {
  // --- Form Initialization ---
  this.bookingForm = this.fb.group({
    roomId: [null, Validators.required],
    customerId: [null, Validators.required],
    roomType: ['', Validators.required],
    checkInDate: ['', Validators.required],
    checkOutDate: ['', Validators.required],
    adults: [1, [Validators.required, Validators.min(1)]],
    children: [0, [Validators.required, Validators.min(0)]],
    items: this.fb.array([]),
  });

  // --- Patch roomId if query param exists ---
  const roomIdFromQuery = this.route.snapshot.queryParamMap.get('roomId');
  if (roomIdFromQuery) {
    this.bookingForm.patchValue({ roomId: Number(roomIdFromQuery) });
  }

// --- Patch customerId from localStorage (AUTO) ---
let customerId = localStorage.getItem('customerId');

// 🟢 Temporary workaround for testing: use valid customer ID if not found
if (!customerId) {
  console.warn('No customerId in localStorage, using test customerId=3');
  customerId = '3'; // <-- Make sure this ID exists in DB (jannat123)
  localStorage.setItem('customerId', customerId);
}

this.bookingForm.patchValue({
  customerId: Number(customerId)
});


  // --- Load Rooms ---
  this.roomService.getAll().subscribe(res => this.rooms = res);

  // --- Load Booking Items ---
  this.bookingService.getBookingItems().subscribe({
    next: data => this.bookingItemOptions = data,
    error: () => alert('Failed to load booking items'),
  });

  // --- Edit Mode ---
  this.bookingCode = this.route.snapshot.paramMap.get('code')!;
  if (this.bookingCode) {
    this.isEditMode = true;
    this.bookingService.getByCode(this.bookingCode).subscribe((booking: BookingResponse) => {
      this.bookingForm.patchValue({
        roomId: booking.roomId,
        customerId: booking.customerId,
        roomType: booking.roomType,
        checkInDate: booking.checkInDate,
        checkOutDate: booking.checkOutDate,
        adults: booking.adults,
        children: booking.children,
      });

      const itemsArray = this.bookingForm.get('items') as FormArray;
      booking.items.forEach(item => {
        itemsArray.push(this.fb.group({
          itemName: item.itemName,
          quantity: item.quantity,
          category: item.category || '',
          description: item.description || '',
          price: item.price,
        }));
      });
    });
  }

  // --- Calculate Nights on date change ---
  this.bookingForm.get('checkInDate')?.valueChanges.subscribe(() => this.calculateNights());
  this.bookingForm.get('checkOutDate')?.valueChanges.subscribe(() => this.calculateNights());
}


  // --- FormArray Getter ---
  get items() {
    return this.bookingForm.get('items') as FormArray;
  }

  addItem() {
    this.items.push(this.fb.group({
      itemName: ['', Validators.required],
      quantity: [1, Validators.required],
      category: [''],
      description: [''],
    }));
  }

  removeItem(index: number) {
    this.items.removeAt(index);
  }

  calculateNights() {
    const checkIn = this.bookingForm.get('checkInDate')?.value;
    const checkOut = this.bookingForm.get('checkOutDate')?.value;
    if (checkIn && checkOut) {
      const diff = Math.ceil(
        (new Date(checkOut).getTime() - new Date(checkIn).getTime()) / (1000 * 60 * 60 * 24)
      );
      this.bookingForm.patchValue({ numberOfNights: diff > 0 ? diff : 0 });
    }
  }

  

  saveBooking() {
    console.log('ROOM ID:', this.bookingForm.get('roomId')?.value);
console.log('CUSTOMER ID:', this.bookingForm.get('customerId')?.value);

    
  console.log('FORM VALUE:', this.bookingForm.value);
  console.log('FORM VALID:', this.bookingForm.valid);
  console.log('FORM ERRORS:', this.bookingForm.errors);

    if (this.bookingForm.invalid) {
      alert('Please fill all required fields');
      return;
    }

    const dto: BookingRequest = this.bookingForm.value;

    if (!this.isEditMode) {
      this.bookingService.create(dto).subscribe({
        next: res => {
          alert('Booking created successfully');
          this.router.navigate(['/booking/confirmation', res.bookingCode]);
        },
        error: err => {
          console.error(err);
          alert('Booking creation failed');
        },
      });
    } else {
      this.bookingService.update(this.bookingCode!, dto).subscribe({
        next: () => {
          alert('Booking updated successfully');
          this.router.navigate(['/admin/bookings']);
        },
        error: err => {
          console.error(err);
          alert('Failed to update booking');
        },
      });
    }
  }
}
