import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SlotsService } from 'src/app/services/slots.service';
import { Slots } from 'src/app/models/slots.model';
import { TrainsService } from 'src/app/services/trains.service';
import { Trains } from 'src/app/models/trains.model';

@Component({
  selector: 'app-add-slot',
  templateUrl: './add-slot.component.html',
  styleUrls: ['./add-slot.component.css']
})
export class AddSlotComponent implements OnInit {
  
  slots$: Slots[];
  trains$: Trains[];
  form = new FormGroup({
    trainid : new FormControl('', Validators.required),
    slotid : new FormControl('', Validators.required),
    slotno : new FormControl('', Validators.required)
  })
  constructor(private slotService: SlotsService, private trainService : TrainsService) { }

  ngOnInit(): void {
    this.getAllSlots();
    this.loadTrains();
  }
  onSubmit(){
    this.slotService.addSlot(JSON.stringify(this.form.value))
    .subscribe((data => {
      if(data == true){
        alert("Show Added")
      }
      else{
        alert('Something went wrong')
      }
    }))
  }

  getAllSlots(){
    this.slotService.getAllSlots()
    .subscribe(data => this.slots$ = data)
  }

  loadTrains(){
    return this.trainService.getTrains()
    .subscribe(data => this.trains$ = data)
  }
}
