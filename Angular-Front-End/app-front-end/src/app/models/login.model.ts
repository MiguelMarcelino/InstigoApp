import { Identifiable } from './identifiable';
import { UserModel } from './user.model';

export interface LoginModel extends Identifiable{
    user: UserModel;
    msg: string;
}