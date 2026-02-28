import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PublicRoutingModule } from './public-routing.module';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { SharedModule } from '../shared/shared.module';
import { RouterModule } from '@angular/router';
import { RoomModule } from '../room/room.module'; 
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    HomeComponent,
    ContactComponent,
    // BookingFormComponent
  ],
  imports: [
    CommonModule,
    PublicRoutingModule,
    SharedModule,
    RouterModule,
    RoomModule, 
    FormsModule
  ]
})
export class PublicModule { }
