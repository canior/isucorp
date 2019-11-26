import { Component, OnInit } from '@angular/core';
import { Contact } from '../../model/contact';
import { ContactService } from '../../service/contact.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PageRequest} from '../../model/pageRequest';

@Component({
    selector: 'app-contact-list',
    templateUrl: './contact-list.component.html',
    styleUrls: ['./contact-list.component.css']
})
export class ContactListComponent implements OnInit {

    contacts: Contact[];
    page: number;
    totalPages: number;
    pageRequest: PageRequest;

    constructor(private contactService: ContactService, private router: Router, private route: ActivatedRoute) {

    }

    ngOnInit() {
        this.page = this.route.snapshot.params.page ? this.route.snapshot.params.page : 1;
        this.pageRequest = new PageRequest();
        this.pageRequest.pageNumber = this.page;
        this.reloadData();
    }

    reloadData() {
        console.log('reload contact list');
        this.contactService.getContactLists(this.pageRequest).subscribe(
            data => {
                console.log(data);
                this.contacts = data.content;
                this.page = data.pageable.pageNumber + 1;
                this.totalPages = data.totalPages;
            },
            error => console.log(error));
    }



    deleteContact(id: number) {
        this.contactService.deleteContact(id).subscribe(
            data => {
                console.log(data);
                this.reloadData();
            },
            error => console.log(error));
    }

    updateContact(id: number) {
        this.router.navigate(['/updateContact/' + id]);
    }

    createContact() {
      this.router.navigate(['/createContact/']);
    }

    gotoPage(page: number) {
      console.log('page = ' + page);
      this.pageRequest.pageNumber = page + 1;
      this.pageRequest.pageLimit = 10;
      console.log(this.pageRequest);
      this.reloadData();
    }

    orderTable(orderCondition: string) {
      console.log(orderCondition);
      const orderConditionArray = orderCondition.split('-');
      this.pageRequest.orderBy = orderConditionArray[0];
      this.pageRequest.orderType = orderConditionArray[1];
      this.reloadData();
    }

}
