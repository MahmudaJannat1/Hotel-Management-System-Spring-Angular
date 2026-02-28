import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from '../models/employee.model';
import { Attendance } from '../models/attendance.model';
import { EmployeeTask } from '../models/employeetask.model';
import { Payroll } from '../models/payroll.model';

@Injectable({
  providedIn: 'root'
})
export class AdminEmployeeService {
private apiUrl = `${environment.apiUrl}/api/admin/employees`;


  constructor(private http: HttpClient) {}

  // ===== EMPLOYEE =====
  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiUrl}`);
  }

  getEmployee(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.apiUrl}/${id}`);
  }

  createEmployee(dto: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiUrl}/employees`, dto);
  }

  updateEmployee(id: number, dto: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiUrl}/employees/${id}`, dto);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/employees/${id}`);
  }

  // ===== ATTENDANCE =====
 getAttendanceByEmployee(employeeId: number): Observable<Attendance[]> {
  return this.http.get<Attendance[]>(`${this.apiUrl}/employee/${employeeId}`);
}


markAttendance(dto: Attendance): Observable<Attendance> {
  return this.http.post<Attendance>(`${this.apiUrl}`, dto);
}

  updateAttendance(id: number, dto: Attendance): Observable<Attendance> {
    return this.http.put<Attendance>(`${this.apiUrl}/attendance/${id}`, dto);
  }

  deleteAttendance(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/attendance/${id}`);
  }

  // ===== TASKS =====
getTasksByEmployee(employeeId: number): Observable<EmployeeTask[]> {
  return this.http.get<EmployeeTask[]>(`${this.apiUrl}/employee/${employeeId}`);
}

  getAllTasks(): Observable<EmployeeTask[]> {
    return this.http.get<EmployeeTask[]>(`${this.apiUrl}/tasks`);
  }

  assignTask(employeeId: number, dto: EmployeeTask): Observable<EmployeeTask> {
    return this.http.post<EmployeeTask>(`${this.apiUrl}/${employeeId}/tasks`, dto);
  }

  updateTaskStatus(id: number, status: string): Observable<EmployeeTask> {
    return this.http.put<EmployeeTask>(`${this.apiUrl}/tasks/${id}/status?status=${status}`, {});
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/tasks/${id}`);
  }

  // ===== PAYROLL =====
 getPayrollsByEmployee(employeeId: number): Observable<Payroll[]> {
  return this.http.get<Payroll[]>(`${this.apiUrl}/employee/${employeeId}`);
}


  getAllPayrolls(): Observable<Payroll[]> {
    return this.http.get<Payroll[]>(`${this.apiUrl}/payroll`);
  }

  getPayroll(id: number): Observable<Payroll> {
    return this.http.get<Payroll>(`${this.apiUrl}/payroll/${id}`);
  }

  createPayroll(employeeId: number, dto: Payroll): Observable<Payroll> {
    return this.http.post<Payroll>(`${this.apiUrl}/${employeeId}/payroll`, dto);
  }

  updatePayroll(id: number, dto: Payroll): Observable<Payroll> {
    return this.http.put<Payroll>(`${this.apiUrl}/payroll/${id}`, dto);
  }

  updatePayrollStatus(id: number, status: string): Observable<Payroll> {
    return this.http.put<Payroll>(`${this.apiUrl}/payroll/${id}/status?status=${status}`, {});
  }

  deletePayroll(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/payroll/${id}`);
  }

downloadPayslip(id: number): Observable<Blob> {
  return this.http.get(`${this.apiUrl}/payroll/${id}/payslip`, { responseType: 'blob' });
}


}
