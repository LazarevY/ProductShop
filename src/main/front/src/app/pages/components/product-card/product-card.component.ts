import {Component, Input, OnInit} from '@angular/core';
import {map, min, tap} from 'rxjs/operators';
import {StoreProduct} from '../../../models/products';
import {colors} from '@angular/cli/utilities/color';
import {ProductsService} from '../../../services/products/products.service';
import {CartService} from '../../../services/cart/cart.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  constructor(public productService: ProductsService, private cartService: CartService) {
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
      calories: 0
    },
    metadata: {
      productFileName: ''
    },
    count: 0,
    price: 0,
    stockStore: null
  };

  count = 1;
  productMax = 0;

  ngOnInit(): void {
    this.productMax = this.product.count;
  }

  decrement(): void {
    this.count = Math.max(this.count - 1, 1);
  }

  increment(): void {
    this.count = Math.min(this.count + 1, this.productMax);
  }

  addToCart(): void {
    this.count = 1;
    this.cartService.loadCartAction();
    this.cartService.addProduct(this.product.product.id, this.count);
    this.cartService.loadCartAction();

  }
}
