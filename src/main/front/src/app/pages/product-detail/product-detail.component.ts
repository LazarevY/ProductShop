import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ProductsService} from '../../services/products/products.service';
import {StoreProduct} from '../../models/products';
import {ApiResponse} from '../../models/api-response';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  constructor(private activeRouter: ActivatedRoute, private productService: ProductsService) {
  }

  productId = 0;

  product: any;

  productsPopular: Array<Array<StoreProduct>> = [];

  ngOnInit(): void {
    this.activeRouter.params.subscribe(value => this.productId = value.id);
    this.productService.getProductData({productId: this.productId, storeId: 1}).subscribe(
      value => {
        this.product = value;
        this.product.actualPrice = this.product.price - this.productService.tryCalcStock(this.product);
      }
    );
    this.productService.getMostPopularProductsForProduct(1, this.productId).subscribe(
      (data: ApiResponse) => {
        const arr = [];
        for (let i = 0; i < data.parameters.products.length; i += 3) {
          arr.push(data.parameters.products.slice(i, Math.min(i + 3, data.parameters.products.length)));
        }
        this.productsPopular = arr;
      }
    );

  }

}
