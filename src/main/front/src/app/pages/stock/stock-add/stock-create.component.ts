import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";
import {Product, Stock, StockClause, StockType} from "../../../models/products";
import {ParameterMap} from "../../../models/types";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-stock-add',
  templateUrl: './stock-create.component.html',
  styleUrls: ['./stock-create.component.css']
})
export class StockCreateComponent implements OnInit {

  constructor() { }

  stockFormValue: Stock = {id: 0, type: {id: 0, type: ''}, values: {T: {id: 0, item: {id: 0, value: ''}}}}

  stockFormControl: FormGroup =
    new FormGroup({
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
    });

  addStock(){
    console.log(this.stockFormValue)
  }

  onDelete(stock: Stock){
    if(confirm("Are you sure to delete stock?")) {
      this.deleteStock(stock)
    }
  }

  deleteStock(stock: Stock){
    console.log("delete stock")
  }

  stocks: Array<Stock> =
    [
      {
        id: 1,
        type: {id: 1, type: 'type'},
        values: {T: {id: 1, item: {id: 1, value: '1234'}}}
      }
    ]

  ngOnInit(): void {
  }

}
