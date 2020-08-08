import { Identifiable } from './identifiable';

export interface UserModel extends Identifiable{
    userName: string;
    firstName: string;
    lastName: string;
    email: string;
    role: string;
    dateLogin: Date;
}