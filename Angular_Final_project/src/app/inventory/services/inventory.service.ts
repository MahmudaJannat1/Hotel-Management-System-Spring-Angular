import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { InventoryStats } from '../models/inventory.model';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

  private baseUrl = `${environment.apiUrl}/inventory`;

  constructor(private http: HttpClient) { }

  // 🔥 Fetch inventory stats
  getInventoryStats(): Observable<InventoryStats> {
    return this.http.get<InventoryStats>(`${this.baseUrl}/stats`);
  }

}
