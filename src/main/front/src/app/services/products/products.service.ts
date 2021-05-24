import {Injectable} from '@angular/core';
import {AppConfig} from '../../app.component';
import {CatalogRequest} from '../../models/requests';
import {Observable} from 'rxjs';
import {StoreProduct} from '../../models/products';
import {HttpClient} from '@angular/common/http';
import {ApiResponse} from '../../models/api-response';
import {DataStorageService} from '../storage/data-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private conf: AppConfig, private http: HttpClient, private storage: DataStorageService) {
  }


  getCatalog(req: CatalogRequest): Observable<any> {
    return this.http.post(
      this.conf.createBackendUrl('api/catalog/main'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    );
  }


}
