import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthStateService {

  private loggedIn = new BehaviorSubject<boolean>(false);
  private userRole = new BehaviorSubject<string | null>(null);

  isLoggedIn$ = this.loggedIn.asObservable();
  role$ = this.userRole.asObservable();

  // ✅ Helper getters
  get isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  get role(): string | null {
    return this.userRole.value;
  }

  setLoginState(isLoggedIn: boolean, role: string | null) {
    this.loggedIn.next(isLoggedIn);
    this.userRole.next(role);
  }

  logout() {
    this.loggedIn.next(false);
    this.userRole.next(null);
  }
}
