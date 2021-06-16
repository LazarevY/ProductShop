import {Component, OnInit} from '@angular/core';
import {User} from '../../../models/user';
import {UserControlService} from '../../../services/user-control/user-control.service';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(public userService: UserControlService) {
  }

  submitInfo() {
    console.log('ffffffffffff');
  }

  ngOnInit(): void {
    this.userService.loadUserData();
  }

}
