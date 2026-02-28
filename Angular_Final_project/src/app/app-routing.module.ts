import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/guards/auth.guard';
import { AdminGuard } from './core/guards/admin.guard';

const routes: Routes = [

  // 🌍 Public website
  {
    path: '',
    loadChildren: () =>
      import('./public/public.module').then(m => m.PublicModule)
  },

  // 🔐 Auth
  {
    path: 'auth',
    loadChildren: () =>
      import('./auth/auth.module').then(m => m.AuthModule)
  },

  // 👤 User Dashboard
  {
    path: 'dashboard',
    loadChildren: () =>
      import('./dashboard/dashboard.module').then(m => m.DashboardModule),
    canActivate: [AuthGuard]
  },

  // 👑 Admin Dashboard
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AdminGuard]
  },

  // 📘 Booking
  {
    path: 'booking',
    loadChildren: () =>
      import('./booking/booking.module').then(m => m.BookingModule),
    canActivate: [AuthGuard]
  },

  // 🏨 Rooms
  {
    path: 'rooms',
    loadChildren: () =>
      import('./room/room.module').then(m => m.RoomModule)
  },

  // 💳 Payment
  {
    path: 'payment',
    loadChildren: () =>
      import('./payment/payment.module').then(m => m.PaymentModule),
    canActivate: [AuthGuard]
  },

  // 👨‍💼 Employee (Admin only)
{
  path: 'employee',
  loadChildren: () =>
    import('./employee/employee.module').then(m => m.EmployeeModule),
  canActivate: [AdminGuard]
},

{
  path: 'customers',
  loadChildren: () =>
    import('./customer/customer.module').then(m => m.CustomerModule)
},

  // 🧹 Housekeeping
  {
    path: 'housekeeping',
    loadChildren: () =>
      import('./housekeeping/housekeeping.module').then(m => m.HousekeepingModule),
    canActivate: [AdminGuard]
  },
    // 🧹 Housekeeping
  {
    path: 'inventory',
    loadChildren: () =>
      import('./inventory/inventory.module').then(m => m.InventoryModule),
    canActivate: [AdminGuard]
  },

  // ❌ Fallback
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
