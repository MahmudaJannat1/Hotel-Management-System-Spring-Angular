import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

// ✅ IMPORTANT: alias use
import { PaymentRequest as PaymentRequestModel } from '../models/payment-request.model';
import { PaymentResponse } from '../models/payment-response.model';
import { PaymentStats } from '../models/payment-stats.model';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiUrl = `${environment.apiUrl}/payments`;

  constructor(private http: HttpClient) {}

  // Create a new payment
  create(request: PaymentRequestModel): Observable<PaymentResponse> {
    return this.http.post<PaymentResponse>(this.apiUrl, request);
  }

  // Get a payment by ID
  get(id: number): Observable<PaymentResponse> {
    return this.http.get<PaymentResponse>(`${this.apiUrl}/${id}`);
  }

  // Get all payments
  getAll(): Observable<PaymentResponse[]> {
    return this.http.get<PaymentResponse[]>(this.apiUrl);
  }

  // Update a payment
  update(id: number, request: PaymentRequestModel): Observable<PaymentResponse> {
    return this.http.put<PaymentResponse>(`${this.apiUrl}/${id}`, request);
  }

  // Cancel a payment
  cancelPayment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // Admin payment stats
  getPaymentStats(): Observable<PaymentStats> {
    return this.http.get<PaymentStats>(`${this.apiUrl}/admin/payment-stats`);
  }

 // my payment stats
  getMyPayments(): Observable<PaymentResponse[]> {
  return this.http.get<PaymentResponse[]>(`${this.apiUrl}/my`);
}

}
