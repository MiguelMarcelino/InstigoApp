import { Identifiable } from './identifiable';

export interface EventModel extends Identifiable{
    id: string;
    name: string;
    start: string;
    end: string;
    cID: string;
    cName: string;
}