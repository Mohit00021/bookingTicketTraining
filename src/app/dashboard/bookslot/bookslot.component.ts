import { Component, OnInit, Input, Directive, HostListener } from '@angular/core';
import { BookingsService } from 'src/app/services/bookings.service';
import { Router, ActivatedRoute } from '@angular/router';
import { SeatService } from 'src/app/services/seat.service';
import { Seat } from 'src/app/models/seat.model';
import { SlotsService } from 'src/app/services/slots.service';
import { Slots } from 'src/app/models/slots.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
// import {SelectModule} from '@angular/core'
import { Trains } from 'src/app/models/trains.model';
import { HttpClient } from '@angular/common/http';
import { Bookings } from 'src/app/models/bookings.model';

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
  dropOptions: string[]
  sourceRouteOptions : Array<string> = []
  destinationRouteOptions : Array<string> = []
  selectedSource: any = ''
  selectedDestination: any = ''
  isEnable : boolean = false
  strikeCheckout:any = null;
  price : any;

  @Input() bookingdetails = {
    'email': '',
    'trainid': '',
    'seat_type': '',
    'departDate': '',
    'numberOfSeats': '',
    'trainFrom' : '',
    'trainTo' : ''
  }

  constructor(
    private bookings: BookingsService,
    private slotsService: SlotsService,
    private seatService: SeatService,
    public actRoute: ActivatedRoute,
    public router: Router,
    private http : HttpClient) { }

  ngOnInit(): void {
    this.load = false;
    this.getSeats();
    this.getSlotById();
    this.trainName = history.state.data[0]['train_name']
    this.trains = history.state.data[0]
    this.dropOptions =  this.trains.route
    this.getDropOptions();
    this.stripePaymentGateway();
    
  }
  validateOptions(){
    
    if (this.bookingdetails.seat_type == '' || this.bookingdetails.departDate == '' || this.bookingdetails.numberOfSeats == '') {
      alert('Kindly fill all the data')
      return
    } 
      else if(this.bookingdetails.trainFrom ==  this.bookingdetails.trainTo){
      alert("Source and destination should not be same!")
      console.log(this.selectedDestination);    
      
      }
      else if(this.bookingdetails.trainTo == null || this.bookingdetails.trainFrom == null){
        alert("Please Select a source and destination")
        return
      }
      else if(this.bookingdetails.trainTo == null && this.bookingdetails.trainFrom){
        alert("Please Select a source and destination")
        return
      }
      else{
        this.getPrice();
      }

  }
  
  getDropOptions(){
    for (let i = 0; i < this.dropOptions.length; i++) {
      this.sourceRouteOptions.push(this.dropOptions[i]['via']);  
      this.destinationRouteOptions.push(this.dropOptions[i]['via']);
    }
    this.sourceRouteOptions.push(this.trains.trainFrom);  
    this.destinationRouteOptions.push(this.trains.trainTo);  
  }
  getSeats() {
    return this.seatService.getSeats()
      .subscribe(data => this.seats$ = data)
  }
  getSlotById() {
    return this.slotsService.getSlotById(this.trainid)
      .subscribe(data => this.slots$ = data)
  }

  getPrice(){
    console.log(this.trainid);
    
     this.bookings.getPrice(this.trainid,this.bookingdetails).subscribe((data)=>{
     this.price = data
     console.log(this.price+"Price");
     });
  }

  addBooking() {
    this.getPrice();
    this.validateOptions();
    this.load = true;
    this.bookings.addBooking(this.trainid, this.bookingdetails)
      .subscribe((data: {}) => {
        this.price = data
        this.router.navigate(['/dashboard/bookings'])
        
      })
      this.checkout(this.price)
  }
  
 
  checkout(amount) {
   
    const strikeCheckout = (<any>window).StripeCheckout.configure({
      key: 'pk_test_51LlkQ2SGx4qmTsYrOLw5M6vAcOzoM5vyR4KKqX2xFTe1O0mCNQGnaG0wk1qXBB1cQr9OW9nux6VBbqFFEL6XIsPU00lucaqaPs',
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log(stripeToken)
        alert('Stripe token generated!');
      }
    });
  
    strikeCheckout.open({
      name: 'RemoteStack',
      description: 'Payment widgets',
      amount: amount * 100
    });
  }
  
  stripePaymentGateway() {
    if(!window.document.getElementById('stripe-script')) {
      const scr = window.document.createElement("script");
      scr.id = "stripe-script";
      scr.type = "text/javascript";
      scr.src = "https://checkout.stripe.com/checkout.js";

      scr.onload = () => {
        this.strikeCheckout = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51LlkQ2SGx4qmTsYrOLw5M6vAcOzoM5vyR4KKqX2xFTe1O0mCNQGnaG0wk1qXBB1cQr9OW9nux6VBbqFFEL6XIsPU00lucaqaPs',
          locale: 'auto',
          token: function (token: any) {
            console.log(token)
            alert('Payment via stripe successfull!');
          }
        });
      }
        
      window.document.body.appendChild(scr);
    }
  }



}
