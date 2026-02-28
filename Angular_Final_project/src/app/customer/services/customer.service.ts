import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Customer } from '../models/customer.model';
import { CustomerStats } from '../models/customer-stats.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = `${environment.apiUrl}/customers`;

  constructor(private http: HttpClient) {}

  // 🔹 CREATE
  createCustomer(data: any): Observable<Customer> {
    return this.http.post<Customer>(this.baseUrl, data);
  }

  // 🔹 GET ALL
  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.baseUrl);
  }

  // 🔹 GET BY ID
  getCustomerById(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.baseUrl}/${id}`);
  }

  // 🔹 UPDATE
  updateCustomer(id: number, data: any): Observable<Customer> {
    return this.http.put<Customer>(`${this.baseUrl}/${id}`, data);
  }

  // 🔹 SOFT DELETE
  deleteCustomer(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  // 🔥 ADMIN STATS
  getCustomerStats(): Observable<CustomerStats> {
    return this.http.get<CustomerStats>(`${this.baseUrl}/customer-stats`);
  }
}
