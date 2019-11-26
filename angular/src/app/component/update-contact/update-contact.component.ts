import { Component, OnInit } from '@angular/core';
import { ContactService } from '../../service/contact.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-update-contact',
    templateUrl: './update-contact.component.html',
    styleUrls: ['./update-contact.component.css']
})
export class UpdateContactComponent implements OnInit {

    serverErrors: Array<any>;
    contactForm: FormGroup;
    submitted: boolean;
    id: number;

    constructor(private parserFormatter: NgbDateParserFormatter,
                private formBuilder: FormBuilder,
                private contactService: ContactService,
                private route: ActivatedRoute,
                private router: Router) {
    }

    ngOnInit() {
        this.submitted = false;
        this.id = this.route.snapshot.params.id;
        this.contactForm = this.formBuilder.group({
            id: [''],
            name: ['', Validators.required],
            contactType: ['', Validators.required],
            phone: ['', Validators.required],
            birthday: ['', Validators.required]
        });

        console.log(this.contactForm);

        this.contactService.getContact(this.id)
            .subscribe(data => {
                console.log(data);
                this.contactForm.setValue(data);
                const birthDateArray = data.birthday.split('-');
                this.contactForm.controls.birthday.setValue({
                    year: parseInt(birthDateArray[0], 10),
                    month: parseInt(birthDateArray[1], 10),
                    day: parseInt(birthDateArray[2], 10),
                });
                console.log(this.contactForm);
            }, error => {
                console.log(error)
                this.serverErrors = error.error;
        });
    }

    get f() { return this.contactForm.controls }


    onSubmit() {
        this.submitted = true;

        if (this.contactForm.invalid) {
            return;
        }
        this.contactForm.value.birthday = this.parserFormatter.format(this.contactForm.value.birthday);
        this.contactService.updateContact(this.id, this.contactForm.value)
            .subscribe(data => {
                console.log(data);
                this.gotoList();
            }, error => {
                console.log(error)
                this.serverErrors = error.error;
            });
    }

    gotoList() {
        this.router.navigate(['/listContacts']);
    }

}
