import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateContactComponent } from './component/create-contact/create-contact.component';
import { ContactListComponent } from './component/contact-list/contact-list.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { UpdateContactComponent } from './component/update-contact/update-contact.component';
import { ReservationListComponent } from './component/reservation-list/reservation-list.component';
import { CreateReservationComponent } from './component/create-reservation/create-reservation.component';
import { UpdateReservationComponent } from './component/update-reservation/update-reservation.component';
import {NgxPaginationModule} from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    CreateContactComponent,
    ContactListComponent,
    UpdateContactComponent,
    ReservationListComponent,
    CreateReservationComponent,
    UpdateReservationComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    CKEditorModule,
    NgxPaginationModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
