import { Component, OnInit } from '@angular/core';
import {CartService} from '../../../services/cart/cart.service';
import {StoreProduct} from '../../../models/products';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  constructor(public cartService: CartService) { }

  products: Map<number, StoreProduct> = new Map();

  ngOnInit(): void {
    this.cartService.loadCartAction();
  }

}
