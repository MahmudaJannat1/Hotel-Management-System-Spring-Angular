// src/app/services/housekeeping.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HousekeepingTask } from '../models/housekeeping-task.model';
import { LaundryRequest } from '../models/laundry-request.model';
import { MaintenanceRequest } from '../models/maintenance-request.model';
import { AssetMaintenanceLog } from '../models/asset-maintenance-log.model';
import { environment } from 'src/environments/environment';
import { HousekeepingStatsDTO } from '../models/housekeeping-stats.model';

@Injectable({
  providedIn: 'root'
})
export class HousekeepingService {

  private baseUrl = `${environment.apiUrl}/housekeeping`;

  constructor(private http: HttpClient) {}

  // ===== Housekeeping Tasks =====
  createTask(task: HousekeepingTask): Observable<HousekeepingTask> {
    return this.http.post<HousekeepingTask>(`${this.baseUrl}/tasks`, task);
  }

  getTask(id: number): Observable<HousekeepingTask> {
    return this.http.get<HousekeepingTask>(`${this.baseUrl}/tasks/${id}`);
  }

  getTasksByRoom(roomId: number): Observable<HousekeepingTask[]> {
    return this.http.get<HousekeepingTask[]>(`${this.baseUrl}/tasks/room/${roomId}`);
  }

  getAllTasks(): Observable<HousekeepingTask[]> {
    return this.http.get<HousekeepingTask[]>(`${this.baseUrl}/tasks`);
  }

  updateTask(id: number, task: HousekeepingTask): Observable<HousekeepingTask> {
    return this.http.put<HousekeepingTask>(`${this.baseUrl}/tasks/${id}`, task);
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/tasks/${id}`);
  }

  // ===== Laundry Requests =====
  createLaundry(request: LaundryRequest): Observable<LaundryRequest> {
    return this.http.post<LaundryRequest>(`${this.baseUrl}/laundry`, request);
  }

  getAllLaundry(): Observable<LaundryRequest[]> {
    return this.http.get<LaundryRequest[]>(`${this.baseUrl}/laundry`);
  }

  updateLaundryStatus(id: number, status: string): Observable<LaundryRequest> {
    return this.http.put<LaundryRequest>(`${this.baseUrl}/laundry/${id}`, { status });
  }

  deleteLaundry(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/laundry/${id}`);
  }

  // ===== Maintenance Requests =====
  createMaintenance(request: MaintenanceRequest): Observable<MaintenanceRequest> {
    return this.http.post<MaintenanceRequest>(`${this.baseUrl}/maintenance`, request);
  }

  getAllMaintenance(): Observable<MaintenanceRequest[]> {
    return this.http.get<MaintenanceRequest[]>(`${this.baseUrl}/maintenance`);
  }

  updateMaintenanceStatus(id: number, status: string): Observable<MaintenanceRequest> {
    return this.http.put<MaintenanceRequest>(`${this.baseUrl}/maintenance/${id}`, { status });
  }

  deleteMaintenance(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/maintenance/${id}`);
  }

  // ===== Asset Maintenance Logs =====
  createAssetLog(log: AssetMaintenanceLog): Observable<AssetMaintenanceLog> {
    return this.http.post<AssetMaintenanceLog>(`${this.baseUrl}/asset-maintenance`, log);
  }

  getAllAssetLogs(): Observable<AssetMaintenanceLog[]> {
    return this.http.get<AssetMaintenanceLog[]>(`${this.baseUrl}/asset-maintenance`);
  }

  updateAssetLog(id: number, log: AssetMaintenanceLog): Observable<AssetMaintenanceLog> {
    return this.http.put<AssetMaintenanceLog>(`${this.baseUrl}/asset-maintenance/${id}`, log);
  }

  deleteAssetLog(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/asset-maintenance/${id}`);
  }

  // ===== Housekeeping Stats =====
  getStats(): Observable<HousekeepingStatsDTO> {
    return this.http.get<HousekeepingStatsDTO>(`${this.baseUrl}/stats`);
  }
}
