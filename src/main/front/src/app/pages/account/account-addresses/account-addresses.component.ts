import {Component, OnInit} from '@angular/core';
import {UserAddAddress, UserAddress, UserPayMethodFull} from '../../../models/user';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {ApiResponse} from '../../../models/api-response';
import {DataStorageService} from '../../../services/storage/data-storage.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-account-addresses',
  templateUrl: './account-addresses.component.html',
  styleUrls: ['./account-addresses.component.css']
})
export class AccountAddressesComponent implements OnInit {

  closeResult = '';

  addressForm = new FormGroup({
    id: new FormControl(''),
    address: new FormControl(''),
    token: new FormControl('')
  });

  constructor(private modalService: NgbModal, private userControl: UserControlService, private dataStorage: DataStorageService) {
  }

  addresses: Array<UserAddress> = [];


  ngOnInit(): void {
    this.userControl.getAddressList({token: this.dataStorage.getParameter('authToken')}).subscribe(
      (data: ApiResponse) => this.addresses = data.parameters.addresses
    );
  }

  addNewAddressOpen(content: any): void {
    this.addressForm.patchValue({id: 0, address: ''});
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  open(content: any): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addAddress(): void {
    this.addressForm.patchValue({token: this.dataStorage.getParameter('authToken')});
    const addr: UserAddAddress = this.addressForm.value;
    console.log(addr);
    if (addr.id === 0){
      this.userControl.addUserAddress(addr).subscribe(
        _ => this.ngOnInit()
      );
    }
    else {
      this.userControl.updateUserAddress(addr).subscribe(
        _ => this.ngOnInit()
      );
    }
  }

  onUpdateOpen(address: UserAddress, content: any): void {
    this.addressForm.patchValue({address: address.address, id: address.id});
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  onDelete(address: UserAddress): void {
    if (confirm('Are you sure to delete address ' + address.address + '?')) {
      this.deleteAddress(address);
    }
  }

  deleteAddress(address: UserAddress): void {
    this.userControl.deleteUserAddress({id: address.id, token: this.dataStorage.getParameter('authToken')}).subscribe(
      _ => this.ngOnInit()
    );
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }


}
