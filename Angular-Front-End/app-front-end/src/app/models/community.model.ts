import { Identifiable } from './identifiable';

export interface CommunityModel extends Identifiable{
    name: string;
    description: string;
    isSubscribed?: boolean;
}