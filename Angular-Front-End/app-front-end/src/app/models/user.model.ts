import { Identifiable } from './identifiable';

export interface UserModel extends Identifiable{
    userName: String;
    email: String;
    password: String;
}