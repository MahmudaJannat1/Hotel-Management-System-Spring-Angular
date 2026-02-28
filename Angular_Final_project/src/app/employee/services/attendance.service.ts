import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attendance } from '../models/attendance.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {
  private apiUrl = `${environment.apiUrl}/attendance`;

  constructor(private http: HttpClient) {}

  mark(dto: Attendance): Observable<Attendance> {
    return this.http.post<Attendance>(this.apiUrl, dto);
  }

  getAll(): Observable<Attendance[]> {
  return this.http.get<Attendance[]>(`${environment.apiUrl}/attendance`);
}

  getByEmployee(employeeId: number): Observable<Attendance[]> {
    return this.http.get<Attendance[]>(`${this.apiUrl}/employee/${employeeId}`);
  }

  getByEmployeeAndDate(employeeId: number, date: string): Observable<Attendance[]> {
    return this.http.get<Attendance[]>(`${this.apiUrl}/employee/${employeeId}/date/${date}`);
  }
}
