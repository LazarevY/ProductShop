import {Component, Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {DataStorageService} from './services/storage/data-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GoodFood';
}

@Injectable({
  providedIn: 'root'
})
export class AppConfig {

  constructor(private dataStorage: DataStorageService) {
    this.setStoreId(1);
  }

  setStoreId(id: number): void {
    this.dataStorage.setParameter('store', id);
  }

  getStoreId(): number {
    return this.dataStorage.getParameter('store');
  }

  backendAddress = 'localhost:8080';

  httpCorsOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Credentials': 'true',
      'Access-Control-Allow-Headers': 'Content-Type',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
      key: 'x-api-key',
      value: 'NNctr6Tjrw9794gFXf3fi6zWBZ78j6Gv3UCb3y0x',
    })
  };

  createBackendUrl(address: string): string {
    return 'http://' + this.backendAddress + '/' + address;
  }

  getHeadersWithCorsAndJWTToken(token: string): object {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Credentials': 'true',
        'Access-Control-Allow-Headers': 'Content-Type',
        'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE',
        key: 'x-api-key',
        value: 'NNctr6Tjrw9794gFXf3fi6zWBZ78j6Gv3UCb3y0x',
        Authorization: 'Bearer ' + token,
      })
    };
  }


}
