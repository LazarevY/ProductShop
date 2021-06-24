import { Component, OnInit } from '@angular/core';
import {UserControlService} from '../../../services/user-control/user-control.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  constructor(public userService: UserControlService) { }

  ngOnInit(): void {
    this.userService.loadUserData();
  }

}
