import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import { Store } from '@ngxs/store';
import {LoginUser} from "../../models/user";
import {AppConfig} from "../../app.component";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private router: Router, private store: Store,private  httpClient: HttpClient, private conf: AppConfig) {
  }
  signIn(request: LoginUser):Observable<any>{
    return this.httpClient.post<any>(this.conf.createBackendUrl('auth/login'), request, {withCredentials: true});
  }
  refresh():Observable<any>{
    return this.httpClient.post<any>(this.conf.createBackendUrl('auth/refresh'), {withCredentials: true});
  }
  // getUser(): Observable<any> {
  //   return this.httpClient.get<any>(URL_BASE+'/profile/me', {withCredentials: true})
  //     .pipe(
  //       tap(user => {
  //         console.log(user);
  //       })
  //     );
  //   ;
  // }
  logOut() :Observable<any>{
    return this.httpClient.get(this.conf.createBackendUrl('auth/logout'),{withCredentials: true}).pipe(
      tap(res => {
        console.log(res);
        localStorage.clear();
        this.router.navigate(['']);

      })
    )


  }

}
