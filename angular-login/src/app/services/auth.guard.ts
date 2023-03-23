import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGaurd implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let isUserLogged: boolean = false;
    this.authService.isLoggedIn().subscribe({
      
      next: (status: number) => {
        console.log('AuthGaurd status : ' + status)
        if (status == 1) {
          isUserLogged = true;
        } else {
          this.router.navigate(['\login']);
          isUserLogged = false;
        }
      }
    })

    console.log('AuthGaurd isUserLogged : ' + isUserLogged)
    return isUserLogged;
  }
  
}
