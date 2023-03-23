import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { map, tap } from "rxjs/operators";
import { User } from "../model/user";


@Injectable({
  providedIn:'root'
  })

export class LoginService {

  constructor(private http: HttpClient) {
  }

  login(): Observable<User> {
    const user = { "userName": "msingh", "password": "msingh1" };
    return this.http.post<User>('http://localhost:8080/login', user).pipe(
      tap((user) => { console.log("user from springboot : " + JSON.stringify(user)) }),
      map((user: User) => {
        console.log('mapping the data : ' + JSON.stringify( user.firstName));
        if ( user.email ) {
          this.loggedIn(1, user.email);
        } else {
          this.loggedIn(2, "" );
        }
        return user;
      })
    )
  }


  isLogginSubject = new BehaviorSubject<number>(this.hasToken());

  isLoggedIn(): Observable<number> {
    return this.isLogginSubject.asObservable();
  }

  loggedIn( status: number , jwtToken: string ): void{
    console.log('loggedIn');
    localStorage.setItem('token', jwtToken);
    console.log('loggedIn status : jwtToken :: ' + status + ' : ' + localStorage.getItem('token') ); 
    this.isLogginSubject.next( status );
  }

  loggedOut(): void {
    console.log('loggedOut');
    localStorage.removeItem('token');
    this.isLogginSubject.next(2);
  }

  private hasToken(): number { 
    const token = localStorage.getItem('token') != null ? 1 : 2;
    console.log('hasToken : ' + token );
    return token;
  }
}
