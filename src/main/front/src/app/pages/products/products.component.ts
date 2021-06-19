import {Component, OnInit} from '@angular/core';
import {ProductCategory, StoreProduct} from '../../models/products';
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

  categories: Array<ProductCategory> = [];
  categoriesChecks: Map<number, boolean> = new Map<number, boolean>();

  productFilterForm = new FormGroup({
    priceLow: new FormControl(0),
    priceHigh: new FormControl(3000),
    namePattern: new FormControl('')
  });

  constructor(private productsService: ProductsService, private storage: DataStorageService) {
  }


  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productsService.getProductCategories().subscribe(
      (data: ApiResponse) => {
        this.categories = data.parameters.categories;
        const saved = this.storage.getParameter('product-filter-ctgs');
        if (saved != null){
          this.categoriesChecks = new Map(JSON.parse(saved));
        }
        else {
          this.categories.forEach(value => this.categoriesChecks.set(value.id, false));
        }
      }
    );
    console.log(this.categoriesChecks);
    const ctgs: number[] = [];

    this.categoriesChecks.forEach((value, key) => {
      if (value) {
        ctgs.push(key);
      }
    });

    console.log(ctgs);

    this.productsService.getCatalog(
      {
        userId: 0,
        categories: ctgs,
        namePattern: this.productFilterForm.value.namePattern,
        priceHigh: this.productFilterForm.value.priceHigh,
        priceLow: this.productFilterForm.value.priceLow,
        storeId: 1,
        token: this.storage.getParameter('authToken')
      }
    ).subscribe(
      (data: ApiResponse) => {
        this.products = data.parameters.catalog;
      }
    );
  }

  categoryCheckEvent(id: number): void {
    this.categoriesChecks.set(id, !this.categoriesChecks.get(id));
    this.storage.setParameter('product-filter-ctgs', JSON.stringify(Array.from(this.categoriesChecks.entries())));
  }


}
