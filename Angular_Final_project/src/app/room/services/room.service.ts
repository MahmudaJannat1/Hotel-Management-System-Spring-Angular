// room.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { RoomStats } from '../models/room-stats.model';
import { Room } from '../models/room.model';
import { RoomDTO } from '../models/room-dto.model';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseUrl = `${environment.apiUrl}/rooms`;
  private imageUrl = `${environment.apiUrl}/rooms/images`;

  constructor(private http: HttpClient) {}

  // ================= ROOM CRUD =================

  getAll(): Observable<Room[]> {
    return this.http.get<Room[]>(this.baseUrl);
  }

  get(id: number): Observable<Room> {
    return this.http.get<Room>(`${this.baseUrl}/${id}`);
  }

  create(dto: RoomDTO): Observable<Room> {
    return this.http.post<Room>(this.baseUrl, dto);
  }

  update(id: number, dto: RoomDTO): Observable<Room> {
    return this.http.put<Room>(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getStats(): Observable<RoomStats> {
    return this.http.get<RoomStats>(`${this.baseUrl}/room-stats`);
  }

  // ================= ROOM IMAGES =================

  uploadImage(roomId: number, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(
      `${this.imageUrl}/${roomId}`,
      formData
    );
  }

  getRoomImages(roomId: number): Observable<string[]> {
    return this.http.get<string[]>(
      `${this.imageUrl}/room/${roomId}`
    );
  }

getImageUrl(filename: string): string {
  return `${this.imageUrl}/${filename}`;
}

}
