import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { TrainsService } from 'src/app/services/trains.service';
import { Trains } from 'src/app/models/trains.model';

@Component({
  selector: 'app-add-train',
  templateUrl: './add-train.component.html',
  styleUrls: ['./add-train.component.css']
})
export class AddTrainComponent implements OnInit {

  form = new FormGroup({
    train_name: new FormControl('', Validators.required),
    arrival: new FormControl('', Validators.required),
    departure: new FormControl('', Validators.required),
    trainFrom: new FormControl('', Validators.required),
    trainTo: new FormControl('', Validators.required),
    arrivalTime: new FormControl('', Validators.required),
    departureTime: new FormControl('', Validators.required),
    distanceKm: new FormControl('', Validators.required),
    priceByKm: new FormControl('', Validators.required),
    area: new FormControl('', Validators.required),
  })



  constructor(private trainService: TrainsService, private fb: FormBuilder) { }

  trains$: Trains[];

  ngOnInit(): void {
    this.loadTrains();
  }

  onSubmit() {
    this.trainService.addTrain(JSON.stringify(this.form.value))
      .subscribe((data => {
        if (data == true) {
          alert("Train Added")
        }
        else {
          alert('Something went wrong')
        }
      }))
  }

  loadTrains() {
    return this.trainService.getTrains()
      .subscribe(data => this.trains$ = data)
  }

  createTicket(): FormGroup {

    return this.fb.group({
      name: [null, Validators.required],
      age: [null, Validators.required]
    })
  }


}
