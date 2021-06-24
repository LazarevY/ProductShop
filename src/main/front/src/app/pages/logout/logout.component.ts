import { Component, OnInit } from '@angular/core';
import {DataStorageService} from '../../services/storage/data-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private storage: DataStorageService, private router: Router) { }

  ngOnInit(): void {
    this.storage.deleteParameter('authToken');
    this.router.navigate(['/']);
  }

}
