import { Component, OnInit } from '@angular/core';
import {ProductsService} from '../../services/products/products.service';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {StoreProduct} from '../../models/products';
import {ApiResponse} from '../../models/api-response';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent implements OnInit {

  constructor(private productsService: ProductsService, private storage: DataStorageService) { }

  products: Array<StoreProduct> = [];

  ngOnInit(): void {
    this.productsService.getProductsWithStocks(this.storage.getParameter('activeStoreId')).subscribe(
      (data: ApiResponse) => this.products = data.parameters.products
    );
  }

}
