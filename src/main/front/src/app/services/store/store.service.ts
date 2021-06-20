import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiResponse} from '../../models/api-response';
import {AppConfig} from '../../app.component';

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private http: HttpClient, private conf: AppConfig) {
  }

  getAllStores(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(
      this.conf.createBackendUrl('api/store/get'),
      this.conf.httpCorsOptions
    );
  }

}
