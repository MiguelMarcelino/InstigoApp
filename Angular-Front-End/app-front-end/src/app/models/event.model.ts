import { Identifiable } from './identifiable';

export interface EventModel extends Identifiable{
    name: string;
    start: string;
    end: string;
    cID: string;
    cName: string;
    ownerUserName: string;
}