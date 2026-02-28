import { Component, OnInit } from '@angular/core';
import { AuthStateService } from '../../services/auth-state.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
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



  isMenuOpen = false;

toggleMenu(): void {
  this.isMenuOpen = !this.isMenuOpen;
}

// আপনার existing methods

}
