import {Component, OnInit} from '@angular/core';
import {OrderService} from '../../../services/order/order.service';
import {DataStorageService} from '../../../services/storage/data-storage.service';
import {UserOrder} from '../../../models/user';
import {ApiResponse} from '../../../models/api-response';

@Component({
  selector: 'app-account-orders',
  templateUrl: './account-orders.component.html',
  styleUrls: ['./account-orders.component.css']
})
export class AccountOrdersComponent implements OnInit {

  constructor(private orderService: OrderService, private dataStorage: DataStorageService) {
  }

  orders: Array<UserOrder> = [];

  ngOnInit(): void {
    this.orderService.getUserOrders(this.dataStorage.getParameter('authToken')).subscribe(
      (data: ApiResponse) => this.orders = data.parameters.userOrders
    );
  }

}
