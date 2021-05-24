import {Injectable} from '@angular/core';
import {DataStorageService} from '../storage/data-storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserControlService {

  constructor(private storage: DataStorageService) {
  }

  isUserLogin(): boolean {
    return this.storage.getParameter('authToken') != null;
  }

}
