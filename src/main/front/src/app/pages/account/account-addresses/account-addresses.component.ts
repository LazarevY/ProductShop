import { Component, OnInit } from '@angular/core';
import {UserAddress, UserPayMethodFull} from "../../../models/user";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {colors} from "@angular/cli/utilities/color";

@Component({
  selector: 'app-account-addresses',
  templateUrl: './account-addresses.component.html',
  styleUrls: ['./account-addresses.component.css']
})
export class AccountAddressesComponent implements OnInit {

  closeResult = '';

  addressFormValue: UserAddress = {addressId: 0, address: ''};

  constructor(private modalService: NgbModal) {}

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addAddress(){
    console.log(this.addressFormValue)
  }

  onUpdateOpen(address: UserAddress){
    this.addressFormValue = address;
  }

  onDelete(address: UserAddress){
    if(confirm("Are you sure to delete address " + address.address + "?")) {
      this.deleteAddress(address)
    }
  }

  deleteAddress(address: UserAddress){
    console.log("delete address")
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

  addresses: Array<UserAddress> =
    [
      {
        addressId: 1,
        address: "dfdfdfdfdf"
      }
    ];

  ngOnInit(): void {
  }

}
