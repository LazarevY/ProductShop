import { Component, OnInit } from '@angular/core';
import {UserAddress, UserPayMethod, UserPayMethodFull} from "../../../models/user";
import {ModalDismissReasons, NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-account-cards',
  templateUrl: './account-cards.component.html',
  styleUrls: ['./account-cards.component.css']
})
export class AccountCardsComponent implements OnInit {

  closeResult = '';

  cardFormValue: UserPayMethodFull = {
    id: 0,
    cardEndDate: "",
    card: ""
  };

  constructor(private modalService: NgbModal) {}

  open(content: any) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addAddress(){
    console.log(this.cardFormValue)
  }

  onUpdateOpen(card: UserPayMethodFull){
    this.cardFormValue = card;
  }

  onDelete(card: UserPayMethodFull){
    if(confirm("Are you sure to delete card?")) {
      this.deleteCard(card)
    }
  }

  deleteCard(card: UserPayMethodFull){
    console.log("delete card")
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

  cards: Array<UserPayMethodFull> =
    [
      {
        id: 1,
        cardEndDate: "09/21",
        card: "4444444444444444"
      }
    ]

  ngOnInit(): void {
  }

}
