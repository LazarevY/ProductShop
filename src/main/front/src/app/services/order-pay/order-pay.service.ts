import {Injectable} from '@angular/core';
import {ProductOrder} from '../../models/products';
import {ApiResponse} from '../../models/api-response';
import {HttpClient} from '@angular/common/http';
import {DataStorageService} from '../storage/data-storage.service';
import {AppConfig} from '../../app.component';

@Injectable({
  providedIn: 'root'
})
export class OrderPayService {

  orderPrice = 0;
  orderStock = 0;

  constructor(private conf: AppConfig, private http: HttpClient, private storage: DataStorageService) {
  }

  getOrderPrice(req: ProductOrder): void {
    this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/order/price'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    ).subscribe(
      (data: ApiResponse) => {
          this.orderStock = data.parameters.stock;
          this.orderPrice = data.parameters.price - this.orderStock;
      }
    );
  }

}
