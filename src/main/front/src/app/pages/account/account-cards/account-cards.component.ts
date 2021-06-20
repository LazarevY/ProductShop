import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {UserAddPayMethod, UserAddress, UserPayMethod, UserPayMethodFull} from '../../../models/user';
import {ModalDismissReasons, NgbDatepicker, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NgbDateStruct, NgbCalendar} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DataStorageService} from '../../../services/storage/data-storage.service';
import {UserControlService} from '../../../services/user-control/user-control.service';
import {ApiResponse} from '../../../models/api-response';

@Component({
  selector: 'app-account-cards',
  templateUrl: './account-cards.component.html',
  styleUrls: ['./account-cards.component.css']
})
export class AccountCardsComponent implements OnInit {
  closeResult = '';

  cardForm = new FormGroup({
    id: new FormControl(''),
    card: new FormControl('', [ Validators.required, Validators.minLength(16), Validators.maxLength(16)]),
    token: new FormControl(''),
    date: new FormControl('')
  });


  // @ts-ignore
  model: NgbDateStruct = this.calendar.getToday();

  // @ts-ignore
  date: { year: number, month: number };

  cards: Array<UserPayMethodFull> = [];

  ngOnInit(): void {
    this.userControl.getPayMethodList({token: this.storage.getParameter('authToken')}).subscribe(
      (data: ApiResponse) => this.cards = data.parameters.pays
    );
  }

  // @ts-ignore
  constructor(private modalService: NgbModal,
              private calendar: NgbCalendar,
              private storage: DataStorageService,
              private userControl: UserControlService) {
    this.date = {year: calendar.getToday().year, month: calendar.getToday().month};
  }

  open(content: any): void {
    this.cardForm.patchValue({id: 0});
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addNewCardOpen(content: any): void {
    this.cardForm.patchValue({id: 0});
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  addAddress(): void {
    this.cardForm.patchValue({token: this.storage.getParameter('authToken')});

    const method: UserAddPayMethod = {
      card: this.cardForm.value.card,
      token: this.storage.getParameter('authToken'),
      date: this.date.month + '/' + this.date.year,
      id: this.cardForm.value.id
    };

    if (method.id === 0) {
      this.userControl.addUserPayMethod(method).subscribe(_ => window.location.reload());
    } else {
      this.userControl.updateUserPayMethod(method).subscribe(_ => window.location.reload());
    }
  }

  onUpdateOpen(card: UserPayMethodFull, content: any): void {
    this.cardForm.patchValue({card: card.cardNumber});
    this.cardForm.patchValue({id: card.id});
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  onDelete(card: UserPayMethodFull): void {
    if (confirm('Are you sure to delete card?')) {
      this.deleteCard(card);
    }
  }

  deleteCard(card: UserPayMethodFull): void {
    this.userControl.deleteUserPayMethod({id: card.id, token: this.storage.getParameter('authToken')}).subscribe(_ => window.location.reload());
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
