import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InvoiceResponse } from '../models/invoice-response.model';
import { InvoiceStats } from '../models/invoice-stats.model';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private apiUrl = `${environment.apiUrl}/invoices`;

  constructor(private http: HttpClient) { }

  // Generate invoice from a booking
  generate(bookingId: number): Observable<InvoiceResponse> {
    return this.http.post<InvoiceResponse>(`${this.apiUrl}/booking/${bookingId}`, {});
  }

  // Get invoice by ID
  get(id: number): Observable<InvoiceResponse> {
    return this.http.get<InvoiceResponse>(`${this.apiUrl}/${id}`);
  }

  // Get all invoice stats (Admin)
  getInvoiceStats(): Observable<InvoiceStats> {
    return this.http.get<InvoiceStats>(`${this.apiUrl}/stats`);
  }
}
