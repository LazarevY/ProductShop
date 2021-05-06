import {Component, Injectable} from '@angular/core';

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
  backendAddress = "localhost:8080"

  createBackendUrl(address: string): string{
    return "http://" + this.backendAddress + "/" + address;
  }
}
