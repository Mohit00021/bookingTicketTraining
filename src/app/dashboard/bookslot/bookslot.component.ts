import { Component, OnInit, Input, Directive, HostListener } from '@angular/core';
import { BookingsService } from 'src/app/services/bookings.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SeatService } from 'src/app/services/seat.service';
import { Seat } from 'src/app/models/seat.model';
import { SlotsService } from 'src/app/services/slots.service';
import { Slots } from 'src/app/models/slots.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Trains } from 'src/app/models/trains.model';

@Component({
  selector: 'app-bookslot',
  templateUrl: './bookslot.component.html',
  styleUrls: ['./bookslot.component.css']
})
export class BookslotComponent implements OnInit {
  trains: Trains;
  load: boolean;
  seats$: Seat[];
  slots$: Slots[];
  trainid = this.actRoute.snapshot.params['trainid'];
  trainSource = this.actRoute.snapshot.params['trainFrom'];
  //trainname = this.actRoute.snapshot.params['train_image'];
  currentDate = new Date();
  date = this.currentDate.getDate() + '-' + this.currentDate.getMonth() + '-' + this.currentDate.getFullYear()
  trainName: string
  

  @Input() bookingdetails = {
    'email': '',
    'trainid': '',
    'seat_type': '',
    'date': '',
    'numberOfTickets': 0,
  }
  constructor(
    private bookings: BookingsService,
    private slotsService: SlotsService,
    private seatService: SeatService,
    public actRoute: ActivatedRoute,
    public router: Router) { }

  ngOnInit(): void {
    this.load = false;
    this.getSeats();
    this.getSlotById();
    this.trainName = history.state.data[0]['train_name']
    this.trains = history.state.data[0]
    console.log(this.trains);
    
  }

  getSeats() {
    return this.seatService.getSeats()
      .subscribe(data => this.seats$ = data)
  }
  getSlotById() {
    return this.slotsService.getSlotById(this.trainid)
      .subscribe(data => this.slots$ = data)
  }

  addBooking() {
    if (this.bookingdetails.seat_type == '' || this.bookingdetails.date == '' || this.bookingdetails.numberOfTickets == 0) {
      alert('Kindly fill all the data')
      return
    }
    /*if (!this.checkTime(this.bookingdetails.time)){
      alert("OOPS!! Try booking 2 hours earlier..")
      return
    }*/
    this.load = true;
    this.bookings.addBooking(this.trainid, this.bookingdetails)
      .subscribe((data: {}) => {
        alert('Show Booked');
        this.router.navigate(['/dashboard/bookings'])
      })
  }
  /*checkTime(bookingTime){

  var currentTime = new Date();
  
  var ISTTime = new Date(currentTime.getTime() + (330 + currentTime.getTimezoneOffset())*60000)
  ISTTime.setHours(ISTTime.getHours()+2)
  
  var time = bookingTime.split(':')
  var h = parseInt(time[0])
  var m = parseInt(time[1])
  if (h<ISTTime.getHours() || (h == ISTTime.getHours() && m < ISTTime.getMinutes())){
      return false
  }
    return true;
  }*/
}
