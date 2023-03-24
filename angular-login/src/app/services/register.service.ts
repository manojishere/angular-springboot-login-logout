import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map, tap, catchError, retry } from 'rxjs/operators';
import { User } from '../model/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  constructor(private http: HttpClient) { }



  registerUser(newUser: User): Observable<User> {
    console.log('RegisterService registerUser() : ' + JSON.stringify( newUser ));
    return this.http.post<User>('http://localhost:8080/register', newUser, httpOptions).pipe(
      tap((user) => { console.log("user from springboot : " + JSON.stringify(user)) }),
      map((user: User) => {
        console.log('registerUser mapping the data : ' + JSON.stringify(user.firstName));
        if (user.id) {
          console.log( 'registerUser successful user id : ' + user.id )
        } else {
          console.log('registerUser successful user id : ' + user.id)
        }
        return user;
      }),
      catchError((error: HttpErrorResponse) => {
        console.log('registerUser HttpErrorResponse 1: ' + error.status);
        console.log( 'registerUser HttpErrorResponse 2: ' + JSON.stringify(error));
        if (error.status === 401) {
          console.error('registerUser 401 Error: Not Found');
        } else {
          console.error('registerUser An error occurred:', error.error.message);
        }
        return throwError('registerUser Something bad happened; please try again later.');
      })
    )
  }





}
