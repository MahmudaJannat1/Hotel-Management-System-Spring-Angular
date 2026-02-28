import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableComponent } from './components/table/table.component';
import { ModalComponent } from './components/modal/modal.component';
import { LoaderComponent } from './components/loader/loader.component';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { DateFormatPipe } from './pipes/date-format.pipe';
import { EnumLabelPipe } from './pipes/enum-label.pipe';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { RouterModule } from '@angular/router';
import { SidebarComponent } from './components/sidebar/sidebar.component';



@NgModule({
declarations: [
    NavbarComponent,
    FooterComponent,
    TableComponent,
    ModalComponent,
    LoaderComponent,
    ConfirmDialogComponent,
    DateFormatPipe,
    EnumLabelPipe,
    SidebarComponent
  ],
  imports: [CommonModule, RouterModule],
  exports: [
    NavbarComponent,
    FooterComponent,
    TableComponent,
    ModalComponent,
    LoaderComponent,
    ConfirmDialogComponent,
    DateFormatPipe,
    EnumLabelPipe,
    RouterModule,
    SidebarComponent
  ]


})
export class SharedModule { }
