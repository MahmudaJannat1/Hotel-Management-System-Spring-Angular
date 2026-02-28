import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { AdminUserStatisticsDTO } from '../models/admin-user-statistics.model';
import { AdminStatisticsDTO } from '../models/admin-statistics.model';

@Injectable({
  providedIn: 'root'
})
export class AdminStatisticsService {
  private baseUrl = `${environment.apiUrl}/admin/statistics`;

  constructor(private http: HttpClient) {}

  getUserStatistics(): Observable<AdminUserStatisticsDTO> {
    return this.http.get<AdminUserStatisticsDTO>(`${this.baseUrl}/users`);
  }

  getSystemStatistics(): Observable<AdminStatisticsDTO> {
    return this.http.get<AdminStatisticsDTO>(`${this.baseUrl}/system`);
  }
}
