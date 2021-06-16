import { Component, OnInit } from '@angular/core';
import {CartService} from '../../services/cart/cart.service';
import {OrderPayService} from '../../services/order-pay/order-pay.service';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css']
})
export class PayComponent implements OnInit {

  constructor(public cart: CartService,  public pay: OrderPayService) { }

  ngOnInit(): void {
    this.cart.saveCurrentCalories();
    this.pay.getOrderPrice(this.cart.getProductOrder());
  }

}
