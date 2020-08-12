import { Identifiable } from './identifiable';

export interface FeedbackModel extends Identifiable {
    id: string;
    username: string;
    datePublished: string;
    feedback: string;
}