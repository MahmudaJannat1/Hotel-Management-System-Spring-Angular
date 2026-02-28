import { Component, HostListener } from '@angular/core';
import { AuthStateService } from 'src/app/shared/services/auth-state.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

   isScrolled = false;

  @HostListener('window:scroll', [])
  onWindowScroll() {
    this.isScrolled = window.scrollY > 50; // hide topbar after 50px scroll
  }

  isLoggedIn = false;
    role: string | null = null;
  
    constructor(private authState: AuthStateService) {}
  
    ngOnInit(): void {
      this.authState.isLoggedIn$.subscribe(status => this.isLoggedIn = status);
      this.authState.role$.subscribe(role => this.role = role);
    }
  
    logout() {
      this.authState.logout();
    }

}
