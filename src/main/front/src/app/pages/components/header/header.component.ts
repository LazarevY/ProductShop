import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {CartService} from '../../../services/cart/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {


  constructor(public userControl: UserControlService,
              public cartService: CartService) {}

}
