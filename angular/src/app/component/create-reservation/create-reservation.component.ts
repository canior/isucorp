import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Place } from 'src/app/model/place';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { ReservationService } from 'src/app/service/reservation.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ContactService } from 'src/app/service/contact.service';
import { PlaceService } from 'src/app/service/place.service';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-create-reservation',
  templateUrl: './create-reservation.component.html',
  styleUrls: ['./create-reservation.component.css']
})
export class CreateReservationComponent implements OnInit {

  serverErrors: Array<any>;
    reservationForm: FormGroup;
    submitted: boolean;
    places: Place[];
    public Editor = ClassicEditor;

    constructor(private parserFormatter: NgbDateParserFormatter,
                private formBuilder: FormBuilder,
                private reservationService: ReservationService,
                private route: ActivatedRoute,
                private router: Router,
                private contactService: ContactService,
                private placeService: PlaceService) {
    }

    ngOnInit() {
        this.submitted = false;
        this.reservationForm = this.formBuilder.group({
            id: [''],
            contact: this.formBuilder.group({
                id: [''],
                name: ['', Validators.required],
                contactType: ['', Validators.required],
                phone: ['', Validators.required],
                birthday: ['', Validators.required]
            }),
            place: this.formBuilder.group({
                id: ['', Validators.required],
                name: [''],
                placeImageUrl: [''],
                ranking: ['']
            }),
            visitDateTime: ['', Validators.required],
            favorite: ['', Validators.required],
            description: ['', Validators.required]
        });

        console.log(this.reservationForm);

        this.placeService.getPlaceList()
            .subscribe(data => {
                console.log(data);
                this.places = data;
                (this.reservationForm.controls.place as FormGroup).setValue(data[0]);
            }, error => {
                console.log(error);
            });
    }

    get f() { return this.reservationForm.controls; }

    get c() { return (this.reservationForm.controls.contact as FormGroup).controls; }

    get p() { return (this.reservationForm.controls.place as FormGroup).controls; }


    onSubmit() {
        console.log('ready to submit');
        this.submitted = true;

        if (this.reservationForm.invalid) {
            return;
        }

        (this.reservationForm.controls.contact as FormGroup).value.birthday
            = this.parserFormatter.format((this.reservationForm.controls.contact as FormGroup).value.birthday);
        this.reservationForm.value.visitDateTime = this.parserFormatter.format(this.reservationForm.value.visitDateTime);
        this.reservationService.createReservation(this.reservationForm.value)
            .subscribe(data => {
                console.log(data);
                this.gotoList();
            }, error => {
                console.log(error)
                this.serverErrors = error.error;
            });
    }


    searchOneContact() {
        this.contactService.findContact((this.reservationForm.controls.contact as FormGroup).controls.name.value)
        .subscribe(data => {
            console.log(data);
            (this.reservationForm.controls.contact as FormGroup).setValue(data);
            const birthdayArray = data.birthday.split('-');
                (this.reservationForm.controls.contact as FormGroup).controls.birthday.setValue({
                    year: parseInt(birthdayArray[0], 10),
                    month: parseInt(birthdayArray[1], 10),
                    day: parseInt(birthdayArray[2], 10),
                });
        }, error => {
            console.log(error)
        });
    }

    gotoList() {
        this.router.navigate(['/listReservations']);
    }

}
