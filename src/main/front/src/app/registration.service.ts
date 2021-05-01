import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { RegistrationUser} from "./models/user";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient){ }

  public registerUser(data: RegistrationUser): any{
    const body =
      {
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        phone: data.phone,
        password: data.password,
      };

    console.log(body);


    const ans = this.http.post(
      'http://localhost:8080/reg',
      body
    ).subscribe({
      error: error => {
        console.error('There was an error!', error);
      }
    });

    console.log(ans);

    return ans;

  }
}
