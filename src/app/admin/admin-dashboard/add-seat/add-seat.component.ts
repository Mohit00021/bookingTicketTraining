import { Component, OnInit } from '@angular/core';
import { Seat } from 'src/app/models/seat.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SeatService } from 'src/app/services/seat.service';

@Component({
  selector: 'app-add-seat',
  templateUrl: './add-seat.component.html',
  styleUrls: ['./add-seat.component.css']
})
export class AddSeatComponent implements OnInit {

  seats$ : Seat[];
  form = new FormGroup({
    seat_type : new FormControl('', Validators.required),
    cost : new FormControl('', Validators.required)
  })
  constructor(private seatservice : SeatService) { }

  ngOnInit(): void {
    this.loadSeat();
  }

  loadSeat(){
    this.seatservice.getSeats()
    .subscribe(data => this.seats$ = data) 
  }

  onSubmit(){
    this.seatservice.addSeat(JSON.stringify(this.form.value))
    .subscribe(data => console.log(data))
    alert('Seating Added')
    location.reload();
  }
}
