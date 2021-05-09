import {Component, Input, OnInit} from '@angular/core';
import {min} from "rxjs/operators";

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  constructor() { }

  @Input('name')
  name = '';

  @Input('calories')
  calories = 0;

  @Input('weight')
  weight = 0;

  @Input('imageUrl')
  imageUrl = ''

  @Input('price')
  price = 0;

  count =  1;


  ngOnInit(): void {
  }

  decrement() {
    this.count = Math.max(this.count - 1, 1)
  }

  increment() {
    this.count = Math.min(this.count + 1, 10)
  }
}
