import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { StockItem } from '../models/stock-item.model';

@Injectable({
  providedIn: 'root'
})
export class StockItemService {
  private baseUrl = `${environment.apiUrl}/inventory/stocks`; // ✅ backend path

  constructor(private http: HttpClient) {}

  getAll(): Observable<StockItem[]> {
    return this.http.get<StockItem[]>(this.baseUrl);
  }

  getById(id: number): Observable<StockItem> {
    return this.http.get<StockItem>(`${this.baseUrl}/${id}`);
  }

  create(data: Partial<StockItem>): Observable<StockItem> {
    return this.http.post<StockItem>(this.baseUrl, data); // POST request
  }

  update(id: number, data: Partial<StockItem>): Observable<StockItem> {
    return this.http.put<StockItem>(`${this.baseUrl}/${id}`, data);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
