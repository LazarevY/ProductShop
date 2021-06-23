import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";
import {FormControl, FormGroup} from "@angular/forms";
import {Product, StoreProduct} from "../../models/products";

@Component({
  selector: 'app-product-add',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  constructor() { }

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

  addStoreProduct(){
    console.log(this.StoreProductFormValue)
  }

  onDelete(storeProduct: StoreProduct){
    if(confirm("Are you sure to delete storeProduct?")) {
      this.deleteStoreProduct(storeProduct)
    }
  }

  deleteStoreProduct(storeProduct: StoreProduct){
    console.log("delete storeProduct")
  }

  storeProducts: Array<StoreProduct> =
    [
      {
        product_id: 1,
        store_id: 1,
        product: {id: 1, name: '1111', description: 'fdfgdg', weight: 1, calories: 10},
        metadata: {productFileName: '123'},
        count: 220,
        price: 10,
        stockStore: {id: 1, type: {id: 2, type: 'fdvf'}, values: {T: {id: 1, item: {id: 1, value: 'rtyny'}}}}
      },
      {
        product_id: 2,
        store_id: 2,
        product: {id: 2, name: '222', description: '3we4f234', weight: 2, calories: 15},
        metadata: {productFileName: '321'},
        count: 5,
        price: 330,
        stockStore: {id: 2, type: {id: 1, type: '4f4g'}, values: {T: {id: 2, item: {id: 2, value: 'ghnt'}}}}
      }
    ]

  ngOnInit(): void {
  }

}
