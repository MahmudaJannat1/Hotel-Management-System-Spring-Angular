import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payroll } from '../models/payroll.model';
import { environment } from 'src/environments/environment';
import { PayrollDTO } from '../models/payrollDTO.model';

@Injectable({ providedIn: 'root' })
export class PayrollService {

  private readonly baseUrl = `${environment.apiUrl}/payroll`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Payroll[]> {
    return this.http.get<Payroll[]>(this.baseUrl);
  }

  getById(id: number): Observable<Payroll> {
    return this.http.get<Payroll>(`${this.baseUrl}/${id}`);
  }
create(data: PayrollDTO) { return this.http.post<PayrollDTO>(this.baseUrl, data); }


update(id: number, data: PayrollDTO) { return this.http.put<PayrollDTO>(`${this.baseUrl}/${id}`, data); }


  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  downloadPayslip(id: number) {
  return this.http.get(`${this.baseUrl}/${id}/payslip`, { responseType: 'blob' });
}


}
