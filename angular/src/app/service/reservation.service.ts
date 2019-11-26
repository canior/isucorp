import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../model/reservation';
import {PageRequest} from '../model/pageRequest';

@Injectable({
    providedIn: 'root'
})
export class ReservationService {

    private baseUrl = 'http://localhost:8080/reservation';
    constructor(private http: HttpClient) { }

    getReservationLists(pageRequest: PageRequest): Observable<any> {
        let pageRequestString = 'pageNumber=' + pageRequest.pageNumber + '&pageLimit=' + pageRequest.pageLimit;
        if (pageRequest.orderBy && pageRequest.orderType) {
          pageRequestString += '&orderBy=' + pageRequest.orderBy + '&orderType=' + pageRequest.orderType;
        }
        return this.http.get(`${this.baseUrl}/list?` + pageRequestString);
    }

    createReservation(reservation: Reservation): Observable<any> {
        return this.http.post(`${this.baseUrl}`, reservation);
    }

    getReservation(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    updateReservation(id: number, reservation: Reservation): Observable<any> {
        console.log(reservation);
        return this.http.put(`${this.baseUrl}/${id}`, reservation);
    }



}
