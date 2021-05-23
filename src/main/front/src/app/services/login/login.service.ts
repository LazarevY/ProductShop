import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {AppConfig} from '../../app.component';
import {LoginUser} from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient, private conf: AppConfig) {
  }

  public loginUser(data: LoginUser): any {


    return this.http.post(
      this.conf.createBackendUrl('api/log'),
      data,
      this.conf.httpCorsOptions
    );
  }
}
