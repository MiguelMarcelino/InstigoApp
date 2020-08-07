import { Identifiable } from './identifiable';

export interface FeedbackModel extends Identifiable {
    userName: string;
    feedback: string;
}