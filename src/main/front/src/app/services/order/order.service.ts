import {Injectable} from '@angular/core';
import {ProductOrder} from '../../models/products';
import {Observable} from 'rxjs';
import {AppConfig} from '../../app.component';
import {HttpClient} from '@angular/common/http';
import {DataStorageService} from '../storage/data-storage.service';
import {ApiResponse} from '../../models/api-response';


@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private conf: AppConfig, private http: HttpClient, private storage: DataStorageService) {
  }

  shipmentAddress: any;

  processOrder(order: ProductOrder): Observable<any> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/order/new/'),
      order,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    );
  }

  validateOrder(order: ProductOrder): Observable<any> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/order/new/'),
      order,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    );
  }

  getUserOrders(userToken: string): Observable<any> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/user/orders/'),
      userToken,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    );
  }

}
