import {Injectable} from '@angular/core';
import {DataStorageService} from '../storage/data-storage.service';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {ApiResponse} from '../../models/api-response';
import {AppConfig} from '../../app.component';
import {DeleteUserAddress, User, UserAddAddress, UserCalorieData, UserUpdateData} from '../../models/user';
import {UserAddressRequest} from '../../models/requests';

@Injectable({
  providedIn: 'root'
})
export class UserControlService {

  constructor(private storage: DataStorageService, private http: HttpClient, private conf: AppConfig) {
  }

  user: User = new User();

  isUserLogin(): boolean {
    return this.storage.getParameter('authToken') != null;
  }

  loadUserData(): void {
    this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/data/'),
      this.storage.getParameter('authToken'),
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')))
      .subscribe(
        (data: ApiResponse) => this.user = data.parameters.data
      );
  }

  getUserData(): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/data/'),
      this.storage.getParameter('authToken'),
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  getUserCalorieData(): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/calories-data/get'),
      this.storage.getParameter('authToken'),
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  getAddressList(req: UserAddressRequest): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/address/get'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  updateUserCalorieData(req: UserCalorieData): Observable<any> {
    req.token = this.storage.getParameter('authToken');
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/calories-data/update'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  updateUserData(req: UserUpdateData): Observable<any> {
    req.token = this.storage.getParameter('authToken');
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/data/update'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  getGenders(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(
      this.conf.createBackendUrl('api/data/genders'),
      this.conf.httpCorsOptions);
  }

  addUserAddress(req: UserAddAddress): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/address/add'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  updateUserAddress(req: UserAddAddress): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/address/update'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

  deleteUserAddress(req: DeleteUserAddress): Observable<ApiResponse> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/address/delete'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken')));
  }

}
