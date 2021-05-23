import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {RegistrationService} from "../../services/reg/registration.service";


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
      {
        error: (error: any) => {
          console.error('There was an error!', error);
        }
      }
    );
  }
}
