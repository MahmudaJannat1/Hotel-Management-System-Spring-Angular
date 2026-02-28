import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { UserLoginRequest } from '../../models/user-login-request';
import { TokenService } from 'src/app/core/services/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  username = '';
  password = '';
  errorMsg = '';

  constructor(
    private authService: AuthService,
    private tokenService: TokenService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  // login() {
  //   const request: UserLoginRequest = {
  //     username: this.username,
  //     password: this.password
  //   };

  //   this.authService.login(request).subscribe({
  //     next: (res: any) => {

  //       const token = res.jwtToken;
  //       if (!token) {
  //         this.errorMsg = 'No token returned from backend';
  //         return;
  //       }

  //       this.tokenService.saveToken(token);

  //          // --- Save customerId in localStorage ---
  //     if (res.customerId) {
  //       localStorage.setItem('customerId', res.customerId.toString());
  //     } else {
  //       console.warn('Login response has no customerId!');
  //     }

  //       // 🔑 highest priority
  //       const redirectTo =
  //         this.route.snapshot.queryParamMap.get('redirectTo');

  //       if (redirectTo) {
  //         this.router.navigateByUrl(redirectTo);
  //         return;
  //       }

  //       const roles: string[] = res.roles || [];

  //       if (roles.includes('ROLE_ADMIN')) {
  //         this.router.navigate(['/admin/dashboard']);
  //       } else if (roles.includes('ROLE_MODERATOR')) {
  //         this.router.navigate(['/moderator/dashboard']);
  //       } else {
  //         this.router.navigate(['/booking/create']); // 👈 better UX
  //       }
  //     },
  //     error: (err) => {
  //       this.errorMsg = err.error?.message || 'Login failed';
  //     }
  //   });
  // }

login() {
  const request: UserLoginRequest = {
    username: this.username,
    password: this.password
  };

  this.authService.login(request).subscribe({
    next: (res: any) => {
      const token = res.jwtToken;
      if (!token) {
        this.errorMsg = 'No token returned from backend';
        return;
      }

      // Save JWT token
      this.tokenService.saveToken(token);

      // 🔑 --- Save customerId from login response ---
  let customerId = res.user?.customer?.id; // <-- important
  if (!customerId) {
    console.warn('No customerId found in login response');
  } else {
    localStorage.setItem('customerId', customerId);
    console.log('Saved customerId:', customerId);
  }
    // Redirect logic
    const redirectTo = this.route.snapshot.queryParamMap.get('redirectTo');
    if (redirectTo) {
      this.router.navigateByUrl(redirectTo);
      return;
    }

    const roles: string[] = res.roles || [];
    if (roles.includes('ROLE_ADMIN')) {
      this.router.navigate(['/admin/dashboard']);
    } else if (roles.includes('ROLE_MODERATOR')) {
      this.router.navigate(['/moderator/dashboard']);
    } else {
      this.router.navigate(['/booking/create']);
    }
  },
  error: (err) => {
    this.errorMsg = err.error?.message || 'Login failed';
  }
  });
}

}