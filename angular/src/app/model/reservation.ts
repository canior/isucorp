import { Contact } from './contact';
import { Place } from './place';

export class Reservation {
    id: number;
    contact: Contact;
    place: Place;
    visitDateTime: Date;
    description: string;
    favorite: boolean;
}
