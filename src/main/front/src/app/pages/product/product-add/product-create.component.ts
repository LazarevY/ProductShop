import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";
import {FormControl, FormGroup} from "@angular/forms";
import {Product} from "../../../models/products";
import {UserAddress, UserPayMethodFull} from "../../../models/user";

@Component({
  selector: 'app-product-add',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent implements OnInit {

  constructor() { }

  productFormValue: Product = {id: 0, name: '', description: '', weight: 0, calories: 0}

  productFormControl: FormGroup =
    new FormGroup({
      id: new FormControl(''),
      name: new FormControl(''),
      description: new FormControl(''),
      weight: new FormControl(''),
      calories: new FormControl('')
    });

  addProduct(){
    console.log(this.productFormValue)
  }

  onDelete(product: Product){
    if(confirm("Are you sure to delete product?")) {
      this.deleteProduct(product)
    }
  }

  deleteProduct(product: Product){
    console.log("delete product")
  }

  products: Array<Product> =
    [
      {
        id: 1,
        name: "banana",
        description: "55555555",
        weight: 1,
        calories: 100
      }
    ]

  ngOnInit(): void {
  }

}
