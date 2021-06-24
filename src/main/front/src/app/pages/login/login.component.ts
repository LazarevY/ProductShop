import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login/login.service';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormControl, FormGroup} from '@angular/forms';
import {ApiResponse} from '../../models/api-response';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService, private storageService: DataStorageService,
              private route: ActivatedRoute, private router: Router) {
  }

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  message = new FormControl('');

  ngOnInit(): void {
  }

  onSubmit(): void {
    console.log('Login...');
    console.log(this.loginForm.value);
    this.loginService.loginUser(this.loginForm.value).subscribe(
      (data: ApiResponse) => {
        if (data.parameters.authSuccess) {
          this.storageService.setParameter('authToken', data.parameters.token);
          const booleanPromise = this.router.navigate(['/']);
          this.message.setValue('');
        } else {
          this.message.setValue('Неверный email или пароль');
        }
      }
    );
  }
}
