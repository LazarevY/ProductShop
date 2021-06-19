import {Component, Input, OnInit} from '@angular/core';
import {ProductsService} from '../../../services/products/products.service';
import {CartService} from '../../../services/cart/cart.service';
import {StoreProduct} from '../../../models/products';

@Component({
  selector: 'app-product-detail-card',
  templateUrl: './product-detail-card.component.html',
  styleUrls: ['./product-detail-card.component.css']
})
export class ProductDetailCardComponent implements OnInit {

  constructor(public productService: ProductsService, private cartService: CartService) {
  }

  productsPopularDivided: Array<Array<StoreProduct>> = [];

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
  stockText = '';

  ngOnInit(): void {
    this.product.actualPrice = this.product.price - this.productService.tryCalcStock(this.product);
    this.stockText = this.productService.getStockTextRepr(this.product.stock);
    this.productMax = this.product.count;
  }

  decrement(): void {
    this.count = Math.max(this.count - 1, 1);
  }

  increment(): void {
    this.count = Math.min(this.count + 1, this.productMax);
  }

  addToCart(): void {
    this.cartService.loadCartAction();
    this.cartService.addProduct(this.product.product.id, this.count);
    this.cartService.loadCartAction();
    this.count = 1;

  }
}
