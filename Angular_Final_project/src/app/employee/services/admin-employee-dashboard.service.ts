import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AdminEmployeeDashboard } from '../models/admin-employee-dashboard.model';

@Injectable({
  providedIn: 'root'
})
export class AdminEmployeeDashboardService {

  private apiUrl = `${environment.apiUrl}/admin/employee-dashboard`;

  constructor(private http: HttpClient) {}

  getDashboardStats(): Observable<AdminEmployeeDashboard> {
    return this.http.get<AdminEmployeeDashboard>(this.apiUrl);
  }
}
