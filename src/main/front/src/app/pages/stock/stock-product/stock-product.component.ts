import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";

@Component({
  selector: 'app-account-info',
  templateUrl: './stock-product.component.html',
  styleUrls: ['./stock-product.component.css']
})
export class StockProductComponent implements OnInit {

  constructor() { }

  addInfo(){
    console.log('ffffffffffff');
  }

  ngOnInit(): void {
  }

}
