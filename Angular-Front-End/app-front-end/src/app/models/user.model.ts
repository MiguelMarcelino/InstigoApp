import { Identifiable } from './identifiable';

export interface UserModel extends Identifiable{
    name: string;
    firstName: string;
    lastName: string;
    email: string;
    dateLogin: Date;
}