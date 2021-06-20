import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {CartService} from '../../../services/cart/cart.service';
import {Store} from '../../../models/store';
import {StoreService} from '../../../services/store/store.service';
import {ApiResponse} from '../../../models/api-response';
import {DataStorageService} from '../../../services/storage/data-storage.service';
import {colors} from '@angular/cli/utilities/color';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


  constructor(public userControl: UserControlService,
              public cartService: CartService,
              public storeService: StoreService,
              private dataStorage: DataStorageService) {
  }

  stores: Array<Store> = [];
  storeId = 0;
  selectedStore = 0;

  ngOnInit(): void {
    this.storeService.getAllStores().subscribe((data: ApiResponse) => {
      this.stores = data.parameters.stores;
      const id = this.dataStorage.getParameter('activeStoreId');
      if (id == null) {
        this.storeId = this.stores[0].id;
        this.dataStorage.setParameter('activeStoreId', this.storeId);
      } else {
        this.storeId = id;
      }
      this.selectedStore = this.storeId;
    });
  }

  setStoreActive(id: any): void {
    if (id !== this.storeId) {
      this.cartService.clearCart();
    }
    this.storeId = id;
    this.selectedStore = id;
    this.dataStorage.setParameter('activeStoreId', this.storeId);
    window.location.reload();
  }

}
