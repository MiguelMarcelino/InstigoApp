import { Identifiable } from './identifiable';

export interface UserSignUpModel extends Identifiable{
    userName: string;
    firstName: string;
    lastName: string;
    email: string;
    password: Date;
}