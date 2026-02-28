import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AssetCategory } from '../models/asset-category.model';

@Injectable({
  providedIn: 'root'
})
export class AssetCategoryService {

  private baseUrl = `${environment.apiUrl}/inventory/categories`;

  constructor(private http: HttpClient) {}

  // GET all categories
  getAll(): Observable<AssetCategory[]> {
    return this.http.get<AssetCategory[]>(this.baseUrl);
  }

  // GET by ID
  get(id: number): Observable<AssetCategory> {
    return this.http.get<AssetCategory>(`${this.baseUrl}/${id}`);
  }

  // CREATE
  create(data: Partial<AssetCategory>): Observable<AssetCategory> {
    return this.http.post<AssetCategory>(this.baseUrl, data);
  }

  // UPDATE
  update(id: number, data: Partial<AssetCategory>): Observable<AssetCategory> {
    return this.http.put<AssetCategory>(`${this.baseUrl}/${id}`, data);
  }

  // DELETE
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
