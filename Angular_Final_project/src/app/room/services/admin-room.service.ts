  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { environment } from '../../../environments/environment';
  import { RoomStats } from '../../room/models/room-stats.model';
  import { RoomDTO } from '../models/room-dto.model';

  @Injectable({
    providedIn: 'root'
  })
  export class AdminRoomService {

    private baseUrl = `${environment.apiUrl}/admin/rooms`;

    constructor(private http: HttpClient) {}

    getAll(): Observable<any> {
      return this.http.get(`${this.baseUrl}`);
    }

    getById(id: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/${id}`);
    }

    create(dto: RoomDTO): Observable<any> {
      return this.http.post(`${this.baseUrl}`, dto);
    }

    update(id: number, dto: RoomDTO): Observable<any> {
      return this.http.put(`${this.baseUrl}/${id}`, dto);
    }

    delete(id: number): Observable<void> {
      return this.http.delete<void>(`${this.baseUrl}/${id}`);
    }

    getStats(): Observable<RoomStats> {
      return this.http.get<RoomStats>(`${this.baseUrl}/room-stats`);
    }

    // -----------------------------
    // ✅ New methods for file upload
    // -----------------------------

    createWithFile(formData: FormData): Observable<any> {
      return this.http.post(`${this.baseUrl}`, formData);                             
    }

    updateWithFile(id: number, formData: FormData): Observable<any> {
      return this.http.put(`${this.baseUrl}/${id}`, formData);
    }
    uploadImage(id: number, file?: File): Observable<any> {
      const formData = new FormData();
      if (file) {
        formData.append('image', file);
      }
      return this.http.post(`${this.baseUrl}/${id}/uploads`, formData);
    }
  }
    