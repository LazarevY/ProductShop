import { Component, OnInit } from '@angular/core';
import {Gender, UserCalorieData} from "../../../models/user";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-calories-data',
  templateUrl: './calories-data.component.html',
  styleUrls: ['./calories-data.component.css']
})
export class CaloriesDataComponent implements OnInit {

  constructor() { }

  userCalorieForm: UserCalorieData = {age: 0, weight: 0, currentNorm: 0, gender: {id: 0, name:''}, growth: 0}
  userCalorieFormControl: FormGroup =
    new FormGroup({
      age: new FormControl(''),
      weight: new FormControl(''),
      gender: new FormGroup({
        id: new FormControl(''),
        name: new FormControl('')
      }),
      growth: new FormControl('')
    });

  genders: Array<Gender> =
    [
      {
        id: 1,
        name: 'Male'
      },
      {
        id: 2,
        name: 'Female'
      }
    ]

  ngOnInit(): void {
  }

  update() {
    console.log(this.userCalorieFormControl.value)
  }
}
