import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataStorageService {

  constructor() {
  }

   setParameter(key: string, value: any): void{
    localStorage.setItem(key, value);
   }
   getParameter(key: string): any{
    return localStorage.getItem(key);
   }
   deleteParameter(key: string): any{
     const i = localStorage.getItem(key);
     localStorage.removeItem(key);
     return i;
   }


}
