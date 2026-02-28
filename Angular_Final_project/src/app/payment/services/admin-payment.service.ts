import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaymentStats } from '../models/payment-stats.model';
import { PaymentResponse } from '../models/payment-response.model';

@Injectable({
  providedIn: 'root'
})
export class AdminPaymentService {

  private api = environment.apiUrl + '/payments';

  constructor(private http: HttpClient) {}

  getPaymentStats(): Observable<PaymentStats> {
    return this.http.get<PaymentStats>(`${this.api}/admin/payment-stats`);
  }

  getAllPayments(): Observable<PaymentResponse[]> {
    return this.http.get<PaymentResponse[]>(this.api);
  }

  cancelPayment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
