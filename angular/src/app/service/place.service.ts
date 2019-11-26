import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PlaceService {

    private baseUrl = 'http://localhost:8080/place';

    constructor(private http: HttpClient) { }

    getPlaceList(): Observable<any> {
        return this.http.get(`${this.baseUrl}/list`);
    }
}
