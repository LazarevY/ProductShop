import {Component, OnInit} from '@angular/core';
import {StoreProduct} from "../../../models/products";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-stock-product',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css']
})
export class StockProductComponent implements OnInit {

  constructor() {
  }

  StoreProductFormValue: StoreProduct = {
    product_id: 0,
    store_id: 0,
    product: {id: 0, name: '', description: '', weight: 0, calories: 0},
    metadata: {productFileName: ''},
    count: 0,
    price: 0,
    stockStore: {id: 0, type: {id: 0, type: ''}, values: {T: {id: 0, item: {id: 0, value: ''}}}}
  }

  StoreProductFormControl: FormGroup =
    new FormGroup({
      product_id: new FormControl(''),
      store_id: new FormControl(''),
      product: new FormGroup({
        id: new FormControl(''),
        name: new FormControl(''),
        description: new FormControl(''),
        weight: new FormControl(''),
        calories: new FormControl('')
      }),
      metadata: new FormGroup({
        productFileName: new FormControl('')
      }),
      count: new FormControl(''),
      price: new FormControl(''),
      stockStore: new FormGroup({
        id: new FormControl(''),
        type: new FormGroup({
          id: new FormControl(''),
          type: new FormControl('')
        }),
        values: new FormGroup({
          T: new FormGroup({
            id: new FormControl(''),
            item: new FormGroup({
              id: new FormControl(''),
              value: new FormControl('')
            }),
          }),
        }),
      }),
    });

  addInfo() {
    console.log(this.StoreProductFormControl.value);
  }

  ngOnInit(): void {
  }

}
