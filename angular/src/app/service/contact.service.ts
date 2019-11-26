import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Contact } from '../model/contact';
import {PageRequest} from '../model/pageRequest';

@Injectable({
    providedIn: 'root'
})
export class ContactService {

    private baseUrl = 'http://localhost:8080/contact';

    constructor(private http: HttpClient) { }

    getContactLists(pageRequest: PageRequest): Observable<any> {
        let pageRequestString = 'pageNumber=' + pageRequest.pageNumber + '&pageLimit=' + pageRequest.pageLimit;
        if (pageRequest.orderBy && pageRequest.orderType) {
          pageRequestString += '&orderBy=' + pageRequest.orderBy + '&orderType=' + pageRequest.orderType;
        }
        return this.http.get(`${this.baseUrl}/list?` + pageRequestString);
    }

    getContact(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    deleteContact(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`);
    }

    createContact(contact: Contact): Observable<any> {
        return this.http.post(`${this.baseUrl}`, contact);
    }

    updateContact(id: number, contact: Contact): Observable<any> {
        return this.http.put(`${this.baseUrl}/${id}`, contact);
    }

    findContact(name: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/search?name=${name}`);
    }
}
