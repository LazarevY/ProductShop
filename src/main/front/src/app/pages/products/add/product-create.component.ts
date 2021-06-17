import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";
import {FormControl, FormGroup} from "@angular/forms";
import {Product} from "../../../models/products";

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

  addInfo(){
    console.log(this.productFormControl.value);
  }

  ngOnInit(): void {
  }

}
