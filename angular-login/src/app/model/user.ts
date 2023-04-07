import { Address } from "./address";

export class User {
    id: number | any;
    userName: string ="";
    password: string ="";
    firstName: string ="";
    lastName: string ="";
    dateOfBirth: Date | any;
    phoneNumber: number | any;
    phoneNumber2: number | any;
    email:string="";
    role:string="";
    token: string = "";
    address: Address | undefined;
}
