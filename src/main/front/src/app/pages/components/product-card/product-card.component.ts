import {Component, Input, OnInit} from '@angular/core';
import {min} from "rxjs/operators";
import {StoreProduct} from "../../../models/products";
import {colors} from "@angular/cli/utilities/color";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  constructor() { }

  @Input('product')
  product: StoreProduct = {
    product: {
      id: 1,
      metadata: {
        filename: 'https://images.aif.by/007/645/abd0a9c390a92692126fb313980eebe9.jpg'
      },
      name: 'Яйца, 20 шт',
      description: 'desd',
      weight: 100,
      calories: 160
    },
    countOfProduct: 100,
    price: 70,
    stockStore: null
  };

  count =  1;

  ngOnInit(): void {
    console.log(this.product)
  }

  decrement() {
    this.count = Math.max(this.count - 1, 1)
  }

  increment() {
    this.count = Math.min(this.count + 1, 10)
  }
}
