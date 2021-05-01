import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { RegistrationUser} from "./models/user";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient){ }

  registerUser(data: RegistrationUser): any{
    const body =
      {
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
        phone: data.phone,
        password: data.password,
      };

    return this.http.post(
      'https://localhost:8080/reg',
      body
    )

  }
}
