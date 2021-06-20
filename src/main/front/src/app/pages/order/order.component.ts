import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart/cart.service';
import {UserControlService} from '../../services/user-control/user-control.service';
import {ApiResponse} from '../../models/api-response';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {UserAddress} from '../../models/user';
import {OrderService} from '../../services/order/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(public cartService: CartService,
              public userControl: UserControlService,
              private dataStorage: DataStorageService,
              private orderService: OrderService) {
  }

  addresses: Array<UserAddress> = [];

  addressId = 0;

  ngOnInit(): void {
    if (this.userControl.isUserLogin()) {
      this.userControl.getAddressList({token: this.dataStorage.getParameter('authToken')}).subscribe(
        (data: ApiResponse) => {
          this.addresses = data.parameters.addresses;
          if (this.addresses.length !== 0) {
            this.addressId = this.addresses[0].id;
            this.dataStorage.setParameter('shipmentAddress', this.addressId);
          }
        }
      );
    }
  }

  setAddress(value: any): void {
    this.dataStorage.setParameter('shipmentAddress', value);
  }

  toPayAction(): void {
    this.dataStorage.setParameter('orderCalories', this.cartService.calories);
  }

}
