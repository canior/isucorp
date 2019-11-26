import { Component, OnInit } from '@angular/core';
import { Reservation } from 'src/app/model/reservation';
import { ReservationService } from 'src/app/service/reservation.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PageRequest} from '../../model/pageRequest';

@Component({
    selector: 'app-reservation-list',
    templateUrl: './reservation-list.component.html',
    styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {

    reservations: Reservation[];
    page: number;
    totalPages: number;
    pageRequest: PageRequest;

    constructor(private reservationService: ReservationService, private router: Router, private route: ActivatedRoute) {

    }

    ngOnInit() {
        console.log('init');
        this.page = this.route.snapshot.params.page ? this.route.snapshot.params.page : 1;
        this.pageRequest = new PageRequest();
        this.pageRequest.pageNumber = this.page;
        this.reloadData();
    }

    reloadData() {
        console.log('reload reservation list');
        this.reservationService.getReservationLists(this.pageRequest).subscribe(
            data => {
                console.log(data);
                this.reservations = data.content;
                this.page = data.pageable.pageNumber + 1;
                this.totalPages = data.totalPages;
            },
            error => console.log(error));
    }

    updateReservation(id: number) {
        this.router.navigate(['/updateReservation/' + id]);
    }

    createReservation() {
      this.router.navigate(['/createReservation/']);
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
