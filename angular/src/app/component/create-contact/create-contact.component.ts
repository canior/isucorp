import { Component, OnInit } from '@angular/core';
import { ContactService } from '../../service/contact.service';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: 'app-create-contact',
    templateUrl: './create-contact.component.html',
    styleUrls: ['./create-contact.component.css']
})
export class CreateContactComponent implements OnInit {

    serverErrors: Array<any>;
    contactForm: FormGroup;
    submitted: boolean;

    constructor(private parserFormatter: NgbDateParserFormatter,
                private formBuilder: FormBuilder,
                private contactService: ContactService,
                private router: Router) {
    }

    ngOnInit() {
        this.submitted = false;
        this.contactForm = this.formBuilder.group({
            name: ['', Validators.required],
            contactType: ['', Validators.required],
            phone: ['', Validators.required],
            birthday: ['', Validators.required]
        });
    }

    get f() { return this.contactForm.controls }

    onSubmit() {
        this.submitted = true;

        if (this.contactForm.invalid) {
            return;
        }
        this.contactForm.value.birthday = this.parserFormatter.format(this.contactForm.value.birthday);
        this.contactService.createContact(this.contactForm.value)
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
