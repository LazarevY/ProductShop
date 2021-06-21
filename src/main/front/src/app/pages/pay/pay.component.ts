import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart/cart.service';
import {OrderPayService} from '../../services/order-pay/order-pay.service';
import {UserControlService} from '../../services/user-control/user-control.service';
import {Router} from '@angular/router';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {OrderService} from '../../services/order/order.service';
import {ApiResponse} from '../../models/api-response';
import {ProductOrder} from '../../models/products';
import {UserPayMethodFull} from '../../models/user';

@Component({
  selector: 'app-pay',
  templateUrl: './pay.component.html',
  styleUrls: ['./pay.component.css']
})
export class PayComponent implements OnInit {

  constructor(public cart: CartService,
              private orderService: OrderService,
              public pay: OrderPayService,
              public storage: DataStorageService,
              private userControl: UserControlService,
              private router: Router) {
  }

  address: any;

  order: any;

  payMethods: Array<UserPayMethodFull> = [];

  orderPrice = 0;
  orderStock = 0;

  ngOnInit(): void {
    if (!this.userControl.isUserLogin()) {
      this.router.navigate(['/order']);
      return;
    }
    this.userControl.getPayMethodList({token: this.storage.getParameter('authToken')}).subscribe(
      (data: ApiResponse) => this.payMethods = data.parameters.pays
    );
    this.userControl.getAddress({
      token: this.storage.getParameter('authToken'), id:
        this.storage.getParameter('shipmentAddress')
    }).subscribe(
      (data: ApiResponse) => {
        console.log(data);
        this.address = data.parameters.address;
      }
    );
    this.cart.saveCurrentCalories();
    const order = this.cart.getProductOrder();
    this.order = order;
    this.pay.getOrderPrice(order).subscribe(
      (data: ApiResponse) => {
        this.orderStock = data.parameters.stock;
        this.orderPrice = data.parameters.price - this.orderStock;
      }
    );
  }

  registerOrder(): void {
    this.orderService.processOrder({
      commonPrice: this.orderPrice + this.orderStock,
      stockPrice: this.orderStock,
      products: this.order.products,
      storeId: this.order.storeId,
      token: this.storage.getParameter('authToken')
    }).subscribe(
      _ => this.router.navigate(['/account/orders'])
    );
  }

}
