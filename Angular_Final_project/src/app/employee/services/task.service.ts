import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EmployeeTask } from '../models/employeetask.model';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private apiUrl = `${environment.apiUrl}/employee-tasks`;

  constructor(private http: HttpClient) { }

  getAll(): Observable<EmployeeTask[]> {
    return this.http.get<EmployeeTask[]>(this.apiUrl);
  }

  getByEmployee(employeeId: number): Observable<EmployeeTask[]> {
    return this.http.get<EmployeeTask[]>(`${this.apiUrl}/employee/${employeeId}`);
  }

  create(task: EmployeeTask): Observable<EmployeeTask> {
    return this.http.post<EmployeeTask>(this.apiUrl, task);
  }

  updateStatus(id: number, status: string): Observable<EmployeeTask> {
    return this.http.put<EmployeeTask>(`${this.apiUrl}/${id}/status?status=${status}`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
