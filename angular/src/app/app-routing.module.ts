import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContactListComponent } from './component/contact-list/contact-list.component';
import { CreateContactComponent } from './component/create-contact/create-contact.component';
import { UpdateContactComponent } from './component/update-contact/update-contact.component';
import { ReservationListComponent } from './component/reservation-list/reservation-list.component';
import { CreateReservationComponent } from './component/create-reservation/create-reservation.component';
import { UpdateReservationComponent } from './component/update-reservation/update-reservation.component';


const routes: Routes = [
    { path: '', redirectTo: 'listReservations', pathMatch: 'full' },
    { path: 'listContacts', component: ContactListComponent },
    { path: 'createContact', component: CreateContactComponent },
    { path: 'updateContact/:id', component: UpdateContactComponent },
    { path: 'listReservations', component: ReservationListComponent },
    { path: 'createReservation', component: CreateReservationComponent },
    { path: 'updateReservation/:id', component: UpdateReservationComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
