import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {  BehaviorSubject, Observable, Subject, throwError } from 'rxjs';
import {  map, tap, catchError, retry } from 'rxjs/operators';
import { User } from '../model/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Accept': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  currentUser : User = {
    id: '1',
    userName: 'manojishere',
    password: 'admin',
    firstName: 'Manoj',
    dateOfBirth:'2012-10-23',
    lastName: 'Singh',
    token: '',
    email: '',
    role: 'Admin'
  }
  
  private _loggedInUser : BehaviorSubject<User>;
  // private _loggedInUser: Observable<User> | undefined;
  private _listOfUsers = new BehaviorSubject< User[]>([]);
  private users : User[] = [];

  // private userURL = 'assets/data/users.json';  // URL to web api
  private userURL = 'api/users';  // URL to web api
  //private baseURL = 'api/users';
  private baseURL = 'http://localhost:8085/';
  /*
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json',
    'Access-Control-Allow-Origin':'*',
    'Authorization':'authkey' })
  };
  */

  constructor(private http: HttpClient) { 
    this._loggedInUser = new BehaviorSubject<User> ( new User() );
    // this._loggedInUser = new Observable<User> ( );
  }

  get user() : Observable<User>{
    return this._loggedInUser.asObservable();
  }

  logout(){
    // console.log("logging out user");
    // const _params = {} as any;
    let nulluser = <User>{};
    // let nulluser =  undefined | any;
    this._loggedInUser.next(nulluser);
    this.loggedOut();
  }  

  getSpecificUser( id : number ) : Observable< User > {
    const url = `${this.baseURL}/${id}`;
    console.log( 'Auth Service getSepecificUser : ' + url  );
    return this.http.get<User> ( url );
  }

  addUser( user: User ) : Observable< User >{
    return this.http.post<User>(this.baseURL, user, { 'headers': httpOptions.headers }).pipe(
      tap(( newUser : User ) => console.log(' Added User : ' + newUser.id ) )
    );
  }

  add( user: User ){
    console.log( 'before : ' + this.users.length);
    this.users.push ( user );
    console.log( this.users.length);
  }  
  

  get userList() : Observable<User[]>{
    this._listOfUsers.next( this.users );
    return this._listOfUsers.asObservable();
  }

  /*
  testConnection() {
    console.log('test Connection1')
    this.http.get<any>('http://localhost:8080/').subscribe({
      next: (data) => console.log('just for test '+ data),
      error: (err) => console.log('just for error: ' + err)
    })
  }
  */



  testConnection(){
    const user = { userName: "msingh", password: "1234erer" };
    let locData: any;
    this.http.post<any>('http://localhost:8080/login', user, httpOptions).pipe(
      tap((response) => {
        console.log('Status code :  ${response.status}')

      }),
      map((response) => { locData = response.body; console.log('satu :' + JSON.stringify(locData)); return response; }),
      map((data: User) => { return data; }),
      tap((data: User) => { console.log('tapping the data : ' + data) })
    ).subscribe({
      next: (data) => console.log('just for test ' + data),
      error: (err) => console.log('just for error: ' + err)
    })
  }

  login(userName: string, password: string) : Observable<User>{

    console.log('AuthService login total users : ' + this.users.length);
    const user = { "userName": userName, "password": password };
    return this.http.post<User>('http://localhost:8080/login', user).pipe(
      tap((user) => { console.log("user from springboot : " + JSON.stringify(user)) }),
      map((user: User) => {
        console.log('mapping the data : ' + JSON.stringify(user.firstName));
        if (user.email) {
          this.loggedIn(1, user.email);
          this._loggedInUser.next(user);
        } else {
          this.loggedIn(2, "");
        }
        return user;
      }),
      catchError((error: HttpErrorResponse) => {
        this.loggedIn(2, "");
        console.log('HttpErrorResponse 1: ' + error.status);
        console.log('HttpErrorResponse 2: ' + JSON.stringify(error));
        if (error.status === 401) {
          console.error('401 Error: Not Found');
        } else {
          console.error('An error occurred:', error.error.message);
        }
        return throwError('Something bad happened; please try again later.');
      })
    )
  }


  errorHandler( error: HttpErrorResponse ) {
    console.log( 'server errors : ' + error )
    return throwError( error.message || "Server Error" );
  }

  isLogginSubject = new BehaviorSubject<number>(this.hasToken());

  isLoggedIn(): Observable<number> {
    return this.isLogginSubject.asObservable();
  }

  loggedIn(status: number, jwtToken: string): void {
    console.log('loggedIn');
    localStorage.setItem('token', jwtToken);
    console.log('loggedIn status : jwtToken :: ' + status + ' : ' + localStorage.getItem('token'));
    this.isLogginSubject.next(status);
  }

  loggedOut(): void {
    console.log('loggedOut');
    localStorage.removeItem('token');
    this.isLogginSubject.next(3);
  }

  private hasToken(): number {
    //const token = localStorage.getItem('token') != null ? 1 : 2;
    const token =  localStorage.getItem( 'token' );
    if (token) {
      // return +token; // type casting from string to number;
      return Number(token);
    }
    return 3;
  }

}

