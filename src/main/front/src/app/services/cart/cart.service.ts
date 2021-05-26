import {Injectable} from '@angular/core';
import {ProductsService} from '../products/products.service';
import {DataStorageService} from '../storage/data-storage.service';
import {StoreProduct} from '../../models/products';
import {AppConfig} from '../../app.component';
import {tap} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartChoose: Map<number, number> = new Map();

  cart: Map<number, StoreProduct> = new Map();

  constructor(private productService: ProductsService, private dataStorage: DataStorageService, private conf: AppConfig) {

  }

  loadCartChoose(): void {
    this.cartChoose = this.dataStorage.getParameter('cart') ? new Map(JSON.parse(this.dataStorage.getParameter('cart'))) :
      new Map<number, number>();
  }

  loadCartAction(): void {
    this.loadCartChoose();
    this.loadCart(this.conf.getStoreId(), Array.from(this.cartChoose.keys()));
  }

  loadCart(storeId: number, productIds: Array<number>): void {
    if (productIds.length === 0) {
      return;
    }
    this.productService.getProductsData({
      storeId,
      productIds,
    }).subscribe(
      (data: Array<StoreProduct>) => {
        this.cart.clear();
        this.loadCartChoose();
        for (const p of data) {
          // @ts-ignore
          p.count = this.cartChoose.get(p.product.id);
          this.cart.set(p.product.id, p);
        }
      }
    );
  }

  updateCartChoose(): void {
    this.dataStorage.setParameter('cart', JSON.stringify(Array.from(this.cartChoose.entries())));
  }

  addProduct(id: number, count: number): void {
    this.loadCartChoose();
    // @ts-ignore
    const old: number = this.cartChoose.get(id) ? this.cartChoose.get(id) : 0;
    const n = Math.max(0, old + count);
    if (n === 0) {
      this.cartChoose.delete(id);
    } else {
      this.cartChoose.set(id, n);
    }
    this.updateCartChoose();
  }

  setProductCount(id: number, count: number): void {
    this.loadCartChoose();
    // @ts-ignore
    const n = Math.max(0, count);
    if (n === 0) {
      this.cartChoose.delete(id);
    } else {
      this.cartChoose.set(id, n);
    }
    this.updateCartChoose();
  }
}
