import { Component, OnInit } from '@angular/core';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from 'src/app/service/reservation.service';
import { ContactService } from 'src/app/service/contact.service';
import { Place } from 'src/app/model/place';
import { PlaceService } from 'src/app/service/place.service';

@Component({
    selector: 'app-update-reservation',
    templateUrl: './update-reservation.component.html',
    styleUrls: ['./update-reservation.component.css']
})
export class UpdateReservationComponent implements OnInit {

    serverErrors: Array<any>;
    reservationForm: FormGroup;
    submitted: boolean;
    id: number;
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
        this.id = this.route.snapshot.params.id;
        this.reservationForm = this.formBuilder.group({
            id: ['', Validators.required],
            contact: this.formBuilder.group({
                id: ['', Validators.required],
                name: ['', Validators.required],
                contactType: ['', Validators.required],
                phone: ['', Validators.required],
                birthday: ['', Validators.required]
            }),
            place: this.formBuilder.group({
                id: ['', Validators.required],
                name: ['', Validators.required],
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
            }, error => {
                console.log(error);
            });

        this.reservationService.getReservation(this.id)
            .subscribe(data => {
                console.log(data);
                this.reservationForm.setValue(data);

                const birthdayArray = data.contact.birthday.split('-');
                (this.reservationForm.controls.contact as FormGroup).controls.birthday.setValue({
                    year: parseInt(birthdayArray[0], 10),
                    month: parseInt(birthdayArray[1], 10),
                    day: parseInt(birthdayArray[2], 10),
                });


                const visitDateTimeArray = data.visitDateTime.split('-');
                this.reservationForm.controls.visitDateTime.setValue({
                    year: parseInt(visitDateTimeArray[0], 10),
                    month: parseInt(visitDateTimeArray[1], 10),
                    day: parseInt(visitDateTimeArray[2], 10),
                });
                console.log(this.reservationForm);
            }, error => {
                console.log(error)
                this.serverErrors = error.error;
            });
    }

    get f() { return this.reservationForm.controls; }

    get c() { return (this.reservationForm.controls.contact as FormGroup).controls; }


    onSubmit() {
        this.submitted = true;

        if (this.reservationForm.invalid) {
            return;
        }

        (this.reservationForm.controls.contact as FormGroup).value.birthday 
            = this.parserFormatter.format((this.reservationForm.controls.contact as FormGroup).value.birthday);
        this.reservationForm.value.visitDateTime = this.parserFormatter.format(this.reservationForm.value.visitDateTime);
        this.reservationService.updateReservation(this.id, this.reservationForm.value)
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
