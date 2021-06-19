import {Injectable} from '@angular/core';
import {AppConfig} from '../../app.component';
import {CatalogRequest, ProductDataRequest, ProductsDataRequest} from '../../models/requests';
import {Observable} from 'rxjs';
import {Stock, StoreProduct} from '../../models/products';
import {HttpClient} from '@angular/common/http';
import {ApiResponse} from '../../models/api-response';
import {DataStorageService} from '../storage/data-storage.service';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  products: Map<number, Map<number, StoreProduct>> = new Map();
  counter = 0;

  constructor(private conf: AppConfig, private http: HttpClient, private storage: DataStorageService) {
  }

  getProductData(req: ProductDataRequest): Observable<any> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/catalog/product/info'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    ).pipe(
      map((data: ApiResponse) => data.parameters.product)
    );
  }

  getProductsData(req: ProductsDataRequest): Observable<any> {
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/catalog/products/info'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    ).pipe(
      map((data: ApiResponse) => data.parameters.products)
    );
  }

  tryCalcStock(p: StoreProduct): number {
    if (p.stock == null || p.stock.stockClauses.length === 0) {
      return 0;
    }
    let stock = 0;
    p.stock.stockClauses.forEach(value => {
      if (value.stockClauseItem.name === 'percent') {
        const percent = parseInt(value.clauseValue, 10);
        stock = percent / 100 * p.price;
      }
    });
    return stock;
  }

  getStockTextRepr(stock: Stock | null): string {
    if (stock == null || stock.stockClauses.length !== 1) {
      return '';
    }
    let text = '';
    stock.stockClauses.forEach(value => {
      if (value.stockClauseItem.name === 'percent') {
        text = '-' + value.clauseValue + '%';
      } else {
        const t = value.clauseValue.split('t');
        text = t[0] + '+' + t[1];
      }
    });
    return text;
  }


  getCatalog(req: CatalogRequest): Observable<any> {
    return this.http.post(
      this.conf.createBackendUrl('api/catalog/main'),
      req,
      this.conf.getHeadersWithCorsAndJWTToken(this.storage.getParameter('authToken'))
    );
  }

  getProductCategories(): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(
      this.conf.createBackendUrl('api/products/categories'),
      this.conf.httpCorsOptions
    );
  }

  getProductsWithStocks(storeId: number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/products/stocks/get'),
      storeId,
      this.conf.httpCorsOptions
    );
  }

  getMostPopularProducts(storeId: number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/catalog/popular'),
      storeId,
      this.conf.httpCorsOptions
    );
  }

  getMostPopularProductsForProduct(storeId: number, productId: number): Observable<ApiResponse>{
    return this.http.post<ApiResponse>(
      this.conf.createBackendUrl('api/catalog/product-detail/popular'),
      {storeId, productId},
      this.conf.httpCorsOptions
    );
  }


}
