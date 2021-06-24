import {Component, OnInit} from '@angular/core';
import {StoreProduct} from '../../models/products';
import {ProductsService} from '../../services/products/products.service';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {ApiResponse} from '../../models/api-response';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Array<StoreProduct> = [];

  productFilterForm = new FormGroup({
    priceLow: new FormControl(''),
    priceHigh: new FormControl(''),
  });

  constructor(private productsService: ProductsService, private storage: DataStorageService) {
  }

  ngOnInit(): void {
    this.productsService.getCatalog(
      {
        userId: 0,
        categories: [],
        namePattern: '',
        priceHigh: 10000,
        priceLow: 0,
        storeId: 1,
        token: this.storage.getParameter('authToken')
      }
    ).subscribe(
      (data: ApiResponse) => {
        this.products = data.parameters.catalog;
      }
    );
  }

}
