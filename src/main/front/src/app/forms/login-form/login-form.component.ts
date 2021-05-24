import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {LoginService} from '../../services/login/login.service';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {ApiResponse} from '../../models/api-response';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private loginService: LoginService, private storageService: DataStorageService,
              private route: ActivatedRoute, private router: Router) {
  }

  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

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
        }
        else {
          console.log('Auth failed');
        }
      }
    );
  }
}
