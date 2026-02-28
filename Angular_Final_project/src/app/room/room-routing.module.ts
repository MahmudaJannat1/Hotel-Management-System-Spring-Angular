import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RoomListComponent } from './components/room-list/room-list.component';
import { RoomDetailComponent } from './components/room-detail/room-detail.component';
import { RoomTypeListComponent } from './components/room-type-list/room-type-list.component';
import { RoomTypeFormComponent } from './components/room-type-form/room-type-form.component';
import { RoomAdminComponent } from './components/room-admin/room-admin.component';
import { RoomAdminListComponent } from './components/room-admin-list/room-admin-list.component';
import { RoomAdminFormComponent } from './components/room-admin-form/room-admin-form.component';
import { AuthGuard } from '../core/guards/auth.guard';
import { AdminGuard } from '../core/guards/admin.guard';
import { BookingFormComponent } from '../booking/components/booking-form/booking-form.component';

const routes: Routes = [
  // 🌍 PUBLIC
  { path: '', component: RoomListComponent },          
  { path: 'details/:id', component: RoomDetailComponent },     
  { path: 'create', component: BookingFormComponent, canActivate: [AuthGuard] },

  // 🔐 ADMIN
  {
    path: 'admin',
    canActivate: [AuthGuard, AdminGuard],
    children: [
      { path: '', component: RoomAdminComponent },                // Dashboard / stats
      { path: 'list', component: RoomAdminListComponent },        // Room list with actions
      { path: 'create', component: RoomAdminFormComponent },      // Create new room
      { path: 'edit/:id', component: RoomAdminFormComponent },    // Edit existing room
      { path: 'room-types', component: RoomTypeListComponent },   
      { path: 'room-types/create', component: RoomTypeFormComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RoomRoutingModule {}
