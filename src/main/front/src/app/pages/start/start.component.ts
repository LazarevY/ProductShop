import {Component, OnInit} from '@angular/core';
import {StoreProduct} from '../../models/products';
import {ProductsService} from '../../services/products/products.service';
import {ApiResponse} from '../../models/api-response';
import {min} from 'rxjs/operators';
import {DataStorageService} from '../../services/storage/data-storage.service';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css']
})
export class StartComponent implements OnInit {

  constructor(private productService: ProductsService, private dataStorage: DataStorageService) {
  }

  products: Array<StoreProduct> = [];
  productsDivided: Array<Array<StoreProduct>> = [];

  ngOnInit(): void {
    const store = this.dataStorage.getParameter('activeStoreId');
    this.productService.getMostPopularProducts(store).subscribe(
      (data: ApiResponse) => {
        this.products = data.parameters.products;
        const arr = [];
        for (let i = 0; i < this.products.length; i += 3) {
            arr.push(this.products.slice(i, Math.min(i + 3, this.products.length)));
        }
        this.productsDivided = arr;
      }
    );
  }

}
