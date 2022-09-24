import { Injectable } from '@angular/core';
import { Seat } from '../models/seat.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SeatService {

  apiUrl = 'http://localhost:8080/seat';

  httpOptions = {
    headers :new HttpHeaders({
      'Content-Type':'application/json'
    })    
  }
  
  constructor(private _http: HttpClient) { }

  getSeats(){
    return this._http.get<Seat[]>(this.apiUrl);
  }

  addSeat(seat){
    return this._http.post<Seat>(this.apiUrl+'/add', seat, this.httpOptions)
  }
}
