import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Trains } from '../models/trains.model';

@Injectable({
  providedIn: 'root'
})
export class TrainsService {

  httpOptions = {
    headers :new HttpHeaders({
      'Content-Type':'application/json'
    })    
  }
  
  apiUrl = 'http://localhost:8080/trains';

  constructor(private _http: HttpClient) { }

  getTrains(){    
    return this._http.get<Trains[]>(this.apiUrl);
  }

 


  addTrain(train){
    return this._http.post<Boolean>(this.apiUrl+'/add', train, this.httpOptions ); 
  }
}
