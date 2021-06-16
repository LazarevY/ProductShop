import {Component, OnInit} from '@angular/core';
import {Gender, UserCalorieData} from '../../../models/user';
import {FormControl, FormGroup} from '@angular/forms';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {ApiResponse} from '../../../models/api-response';

@Component({
  selector: 'app-calories-data',
  templateUrl: './calories-data.component.html',
  styleUrls: ['./calories-data.component.css']
})
export class CaloriesDataComponent implements OnInit {

  constructor(private userControl: UserControlService) {
  }

  userCalorieFormControl: FormGroup =
    new FormGroup({
      age: new FormControl(''),
      weight: new FormControl(''),
      gender: new FormGroup({
        id: new FormControl(''),
        name: new FormControl('')
      }),
      growth: new FormControl(''),
      funcEnable: new FormControl('')
    });

  genders: Array<Gender> = [];

  calorieData: any;

  ngOnInit(): void {
    this.userControl.getGenders().subscribe(
      (data: ApiResponse) => {
        this.genders = data.parameters.genders;
        this.userCalorieFormControl.value.gender = this.genders[0];
      }
    );
    this.loadCalorieData();
  }

  loadCalorieData(): void {
    this.userControl.getUserCalorieData().subscribe(
      (data: ApiResponse) => {
        const c = data.parameters.calorie;
        this.userCalorieFormControl.setValue({
          age: c.age,
          weight: c.weight,
          growth: c.growth,
          gender: {id: c.genderId, name: ''},
          funcEnable: c.funcEnable
        });
        this.calorieData = data.parameters.calorie;
      }
    );
  }

  update() {
    console.log(this.userCalorieFormControl.value);
    this.userControl.updateUserCalorieData(this.userCalorieFormControl.value).subscribe(
      (_ => this.loadCalorieData())
    );
  }
}
