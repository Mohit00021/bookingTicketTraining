import { Component, OnInit } from '@angular/core';
import { Trains } from '../../models/trains.model';
import { TrainsService } from 'src/app/services/trains.service';
import { Seat } from 'src/app/models/seat.model';
import { SeatService } from 'src/app/services/seat.service';
import { DashboardComponent } from 'src/app/dashboard/dashboard.component';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.css']
})
export class SlotsComponent implements OnInit {

  trains$: Trains[];
  seats$: Seat[];
  sessionValue: string = "";
  slotService: any;
  allTrains: any;


  private tosend: number;

  constructor(
    private trainsService: TrainsService,
    private seatService: SeatService,
    private dashboardComponent: DashboardComponent,
    private _http: HttpClient,
    private router: Router

  ) { }

  ngOnInit() {
    this.dashboardComponent.checkLogin();
    this.loadTrains();
    this.loadSeat();
    this.getRoutes();
    this.tosend = 1000;
  }


  loadTrains() {

    return this.trainsService.getTrains()
      .subscribe(data => this.trains$ = data)

  }

  getRoutes() {
    return this._http.get('http://localhost:8080/trains/route').subscribe(data => this.allTrains = data['route'])
  }


  loadSeat() {
    return this.seatService.getSeats()
      .subscribe(data => this.seats$ = data)
  }

  goto2(trainid: number) {
    
    // this.router.navigate([], { state: { data: this.trains$ } })
    this.router.navigateByUrl('/dashboard/bookslot/'+trainid,{ state: { data: this.trains$ } } );
  }

}
