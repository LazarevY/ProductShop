import {Component, OnInit} from '@angular/core';
import {CartService} from '../../services/cart/cart.service';
import {UserControlService} from '../../services/user-control/user-control.service';
import {ApiResponse} from '../../models/api-response';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {UserAddress} from '../../models/user';
import {OrderService} from '../../services/order/order.service';
import {StoreProduct} from '../../models/products';

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

  overNorm = 0;

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
    this.isOverNorm();
  }

  setAddress(value: any): void {
    this.dataStorage.setParameter('shipmentAddress', value);
  }

  toPayAction(): void {
    this.dataStorage.setParameter('orderCalories', this.cartService.calories);
  }

  isOverNorm(): void {
    this.overNorm = 0;
    if (!this.userControl.isUserLogin()) {
      return;
    }
    this.userControl.tryGetUserCalorieNorm(this.dataStorage.getParameter('authToken')).subscribe(
      (data: ApiResponse) => {
        console.log(data);
        if (data.parameters.funcEnable) {
          this.cartService.getCalories().subscribe((src: Array<StoreProduct>) => {
            let calories = 0;
            for (const p of src) {
              // @ts-ignore
              p.count = this.cartService.cartChoose.get(p.product.id);
              calories += p.count * (p.product.weight / 100) * p.product.calories; // mass /100g * calories on 100g
            }
            this.overNorm = calories - data.parameters.norm;
          });
        }
      }
    );
  }
}
