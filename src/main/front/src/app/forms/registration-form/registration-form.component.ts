import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {RegistrationService} from "../../services/reg/registration.service";
import {AppConfig} from '../../app.component';
import {ApiResponse} from '../../models/api-response';


@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent{

  constructor(private registrationService: RegistrationService) {
  }

  registrationForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });


  onSubmit(): void {
    this.registrationService.registerUser(this.registrationForm.value).subscribe(
      (data: ApiResponse) => {
          if (data.code === 'OK'){
            console.log('Reg success');
          }
      }
    );
  }
}
