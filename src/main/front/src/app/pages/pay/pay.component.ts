import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart/cart.service';
import {OrderPayService} from '../../services/order-pay/order-pay.service';
import {UserControlService} from '../../services/user-control/user-control.service';
import {Router} from '@angular/router';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {OrderService} from '../../services/order/order.service';
import {ApiResponse} from '../../models/api-response';

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

  ngOnInit(): void {
    if (!this.userControl.isUserLogin()) {
      this.router.navigate(['/order']);
    }
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
    this.pay.getOrderPrice(order);
  }

}
