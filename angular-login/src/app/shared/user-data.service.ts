import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Address } from '../model/address';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserDataService  implements InMemoryDbService{
  constructor() { }

  createDb() {
      let users: User[] = [
          {
              id: '1',
              userName: 'manojishere',
              password: 'admin',
              firstName: 'Manoj',
              dateOfBirth:'2012-10-23',
              lastName: 'Singh',
              token: '',
              email: 'manojishere@gmail.com',
              phoneNumber: 1111111111,
              phoneNumber2: '',
              role: 'Admin',
              address: new Address()
            },
            {
              id: '2',
              userName: 'tellneetu',
              password: 'admin',
              firstName: 'Neetu',
              dateOfBirth:'2012-10-23',
              lastName: 'Singh',
              token: '',
              email: 'tellneetu@gmail.com',
              phoneNumber: 2222222222,
              phoneNumber2: '',
              role: 'Admin',
              address: new Address()
            },
            {
              id: '3',
              userName: 'tellsanskriti',
              password: 'admin',
              firstName: 'Sanskriti',
              dateOfBirth:'2012-10-23',
              lastName: 'Singh',
              token: '',
              email: 'tellsanskriti@gmail.com',
              phoneNumber: 3333333333,
              phoneNumber2: '',
              role: 'Admin',
              address: new Address()
            }                            
      ]

      return { users };
  }

  genId( users : User[]): number {
    return users.length > 0 ? Math.max(...users.map(user => user.id)) + 1 : 11;
  }  

}
