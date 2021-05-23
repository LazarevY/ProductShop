import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {RegistrationUser} from '../../models/user';
import {AppConfig} from '../../app.component';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient, private conf: AppConfig) {
  }

  public registerUser(data: RegistrationUser): any {


    return this.http.post(
      this.conf.createBackendUrl('api/reg'),
      data,
      this.conf.httpCorsOptions
    );

  }
}
