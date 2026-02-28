// import { Component, OnInit } from '@angular/core';

// import { Router } from '@angular/router';
// import { Attendance } from '../../models/attendance.model';
// import { AttendanceService } from '../../services/attendance.service';
// import { EmployeeService } from '../../services/employee.service';

// @Component({
//   selector: 'app-attendance-list',
//   templateUrl: './attendance-list.component.html'
// })
// export class AttendanceListComponent implements OnInit {
//   attendances: Attendance[] = [];
//   allAttendances: Attendance[] = [];

//   employees: any[] = [];
//   selectedEmployeeId?: number;
//   selectedDate: any;

//   constructor(
//     private service: AttendanceService,
//     private employeeService: EmployeeService,
//     private router: Router
//   ) {}

// ngOnInit(): void {
//   this.employeeService.getAll().subscribe(res => this.employees = res);
//   this.load(); // 🔥 MUST
// }



// load() {
//   this.service.getAll().subscribe(res => {
//     this.attendances = res;   // original data
//     this.applyFilter();          // filtered view
//   });
// }

// applyFilter() {
//   let data = [...this.allAttendances];

//   if (this.selectedEmployeeId) {
//     data = data.filter(a => a.employeeId === this.selectedEmployeeId);
//   }

//   if (this.selectedDate) {
//     data = data.filter(a => a.date === this.selectedDate);
//   }

//   this.attendances = data;
// }

// onFilterChange() {
//   this.applyFilter();
// }


// // onEmployeeChange() {
// //   this.load();
// // }


//   markAttendance() {
//     this.router.navigate(['/employee/admin/attendance/create']);
//   }
// }
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Attendance } from '../../models/attendance.model';
import { AttendanceService } from '../../services/attendance.service';

@Component({
  selector: 'app-attendance-list',
  templateUrl: './attendance-list.component.html',
  styleUrls:['./attendance-list.component.scss']
})
export class AttendanceListComponent implements OnInit {

  attendances: Attendance[] = [];

  constructor(
    private service: AttendanceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.load();   // ✅ only getAll
  }

  load() {
    this.service.getAll().subscribe(res => {
      this.attendances = res;
    });
  }

  markAttendance() {
    this.router.navigate(['/employee/admin/attendance/create']);
  }
}
