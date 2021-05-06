import { Component, OnInit } from '@angular/core';
import {colors} from "@angular/cli/utilities/color";

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor() { }

  submitInfo(){
    console.log('ffffffffffff');
  }

  ngOnInit(): void {
  }

}
