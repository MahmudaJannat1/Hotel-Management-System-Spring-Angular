import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RoomRoutingModule } from './room-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { RoomListComponent } from './components/room-list/room-list.component';
import { RoomDetailComponent } from './components/room-detail/room-detail.component';
import { RoomTypeListComponent } from './components/room-type-list/room-type-list.component';
import { RoomTypeFormComponent } from './components/room-type-form/room-type-form.component';
import { RoomAdminComponent } from './components/room-admin/room-admin.component';
import { HttpClientModule } from '@angular/common/http';
import { RoomAdminListComponent } from './components/room-admin-list/room-admin-list.component';
import { RoomAdminFormComponent } from './components/room-admin-form/room-admin-form.component';


@NgModule({
  declarations: [

    RoomListComponent,
    RoomDetailComponent,
    RoomTypeListComponent,
    RoomTypeFormComponent,
    RoomAdminComponent,
    RoomAdminListComponent,
    RoomAdminFormComponent
  ],
  imports: [
    CommonModule,
    RoomRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    SharedModule,
    HttpClientModule
  ],
  exports: [
  ]
})
export class RoomModule { }
