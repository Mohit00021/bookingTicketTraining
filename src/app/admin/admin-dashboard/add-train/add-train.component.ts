import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TrainsService } from 'src/app/services/trains.service';
import { Trains } from 'src/app/models/trains.model';

@Component({
  selector: 'app-add-train',
  templateUrl: './add-train.component.html',
  styleUrls: ['./add-train.component.css']
})
export class AddTrainComponent implements OnInit {

  form = new FormGroup({
    train_name : new FormControl('', Validators.required),
    area : new FormControl('', Validators.required),
    cast : new FormControl('', Validators.required),
    bannerimage : new FormControl('', Validators.required)
  })
  
  constructor(private trainService: TrainsService) { }

  trains$ : Trains[];

  ngOnInit(): void {
    this.loadTrains();
  }

   onSubmit(){
    this.trainService.addTrain(JSON.stringify(this.form.value))
    .subscribe((data => {
      if(data == true){
        alert("Movie Added")
      }
      else{
        alert('Something went wrong')
      }
    }))
   }

   loadTrains(){
    return this.trainService.getTrains()
    .subscribe(data => this.trains$ = data)
  }
}
