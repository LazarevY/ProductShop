import {Component, Input, OnInit} from '@angular/core';
import {ProductsService} from '../../../../services/products/products.service';
import {CartService} from '../../../../services/cart/cart.service';
import {StoreProduct} from '../../../../models/products';
import {AppConfig} from '../../../../app.component';

@Component({
  selector: 'app-product-mini-card',
  templateUrl: './product-mini-card.component.html',
  styleUrls: ['./product-mini-card.component.css']
})
export class ProductMiniCardComponent implements OnInit {

  constructor(public productService: ProductsService, private cartService: CartService, private conf: AppConfig) {
  }

  @Input('product')
  product: StoreProduct = {
    product_id: 0,
    store_id: 0,
    product: {
      id: 0,
      name: '',
      description: '',
      weight: 0,
      calories: 0,
      metadata: {
        productFileName: ''
      },
    },
    count: 0,
    price: 0,
    actualPrice: 0,
    stock: null
  };

  count = 1;
  productMax = 0;

  ngOnInit(): void {
    this.productService.getProductData({
      productId: this.product.product.id,
      storeId: this.conf.getStoreId(),
    }).subscribe(
      (data: StoreProduct) => {this.productMax = data.count; }
    );
    this.product.actualPrice = this.product.price - this.productService.tryCalcStock(this.product);
  }

  decrement() {
    this.product.count = Math.max(this.product.count - 1, 0);
    this.cartService.setProductCount(this.product.product.id, this.product.count);
    this.cartService.loadCartAction();
  }

  increment() {
    this.product.count = Math.min(this.product.count + 1, this.productMax);
    this.cartService.setProductCount(this.product.product.id, this.product.count);
    this.cartService.loadCartAction();
  }

  removeFromCart() {
    this.product.count = 0;
    this.cartService.setProductCount(this.product.product.id, 0);
    this.cartService.loadCartAction();
  }
}
