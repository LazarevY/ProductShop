import {Component, OnInit} from '@angular/core';
import {User, UserUpdateData} from '../../../models/user';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {FormControl, FormGroup} from '@angular/forms';
import {DataStorageService} from '../../../services/storage/data-storage.service';
import {ApiResponse} from '../../../models/api-response';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})
export class AccountInfoComponent implements OnInit {

  constructor(public userService: UserControlService, private dataStorage: DataStorageService) {
  }

  userInfoForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phone: new FormControl(''),
    token: new FormControl('')
  });

  submitInfo(): void {
    const req: UserUpdateData = this.userInfoForm.value;
    req.token = this.dataStorage.getParameter('authToken');
    this.userService.updateUserData(req).subscribe(
      _ => this.ngOnInit()
    );
  }

  ngOnInit(): void {
    this.userService.loadUserData();
    this.userService.getUserData().subscribe((data: ApiResponse) => {
      const user = data.parameters.data;
      this.userInfoForm.patchValue({
        firstName: user.firstName,
        lastName: user.lastName,
        phone: user.phone,
        token: this.dataStorage.getParameter('authToken')
      });
    });

  }

}
