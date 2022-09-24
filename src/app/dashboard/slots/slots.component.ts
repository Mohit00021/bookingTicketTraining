import { Component, OnInit } from '@angular/core';
import { Trains } from '../../models/trains.model';
import { TrainsService } from 'src/app/services/trains.service';
import { Seat } from 'src/app/models/seat.model';
import { SeatService } from 'src/app/services/seat.service';
import { DashboardComponent } from 'src/app/dashboard/dashboard.component';

@Component({
  selector: 'app-slots',
  templateUrl: './slots.component.html',
  styleUrls: ['./slots.component.css']
})
export class SlotsComponent implements OnInit {

  trains$: Trains[];
  seats$: Seat[];
  sessionValue : string = "";
  slotService: any;

  constructor(
    private trainsService: TrainsService,
    private seatService: SeatService,
    private dashboardComponent: DashboardComponent) { }

  ngOnInit() {
    this.dashboardComponent.checkLogin();
    this.loadTrains();
    this.loadSeat();
  }


  loadTrains(){
    return this.trainsService.getTrains()
    .subscribe(data => this.trains$ = data)
  }

  loadSeat(){
    return this.seatService.getSeats()
    .subscribe(data => this.seats$ = data)
  }
}
