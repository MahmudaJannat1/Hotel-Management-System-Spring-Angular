// admin-booking.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { BookingResponse } from '../models/booking-response';
import { BookingStats } from '../models/booking-stats';

@Injectable({
  providedIn: 'root'
})
export class AdminBookingService {
  private baseUrl = `${environment.apiUrl}/admin/bookings`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<BookingResponse[]> {
    return this.http.get<BookingResponse[]>(this.baseUrl);
  }

  getByCode(code: string): Observable<BookingResponse> {
    return this.http.get<BookingResponse>(`${this.baseUrl}/${code}`);
  }

  update(code: string, request: any): Observable<BookingResponse> {
    return this.http.put<BookingResponse>(`${this.baseUrl}/${code}`, request);
  }

  cancel(code: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${code}`);
  }

  getStats(): Observable<BookingStats> {
    return this.http.get<BookingStats>(`${this.baseUrl}/stats`);
  }
}
