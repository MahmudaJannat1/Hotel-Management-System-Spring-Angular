// room-type.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { RoomType } from '../models/room-type.model';

@Injectable({
  providedIn: 'root'
})
export class RoomTypeService {
  private baseUrl = `${environment.apiUrl}/room-types`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<RoomType[]> {
    return this.http.get<RoomType[]>(this.baseUrl);
  }

  create(roomType: RoomType): Observable<RoomType> {
    return this.http.post<RoomType>(this.baseUrl, roomType);
  }
}
