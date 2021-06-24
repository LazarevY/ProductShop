import {Component, OnInit} from '@angular/core';
import {RegistrationService} from '../../services/reg/registration.service';
import {FormControl, FormGroup} from '@angular/forms';
import {ApiResponse} from '../../models/api-response';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phone: new FormControl(''),
    email: new FormControl(''),
    password: new FormControl(''),
  });

  message = new FormControl('');

  ngOnInit(): void {
  }

  constructor(private registrationService: RegistrationService, private router: Router) {
  }


  onSubmit(): void {
    this.registrationService.registerUser(this.registrationForm.value).subscribe(
      (data: ApiResponse) => {
        if (data.parameters.regSuccess) {
          console.log('reg success');
          this.router.navigate(['/login']);
          this.message.setValue('');
        } else {
            this.message.setValue('Пользователь с таким email уже существует');
        }
      }
    );
  }

}
