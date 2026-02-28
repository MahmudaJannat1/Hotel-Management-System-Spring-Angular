import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { UserLoginRequest } from '../models/user-login-request';
import { JwtResponse } from '../models/jwt-response.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = environment.apiUrl + '/auth';

  constructor(private http: HttpClient) {}

  login(request: UserLoginRequest): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(
      `${this.baseUrl}/signin`,
      request
    );
  }

  register(data: any) {
    return this.http.post(`${this.baseUrl}/signup`, data);
  }
}

