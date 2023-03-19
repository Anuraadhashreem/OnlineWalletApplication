import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Wallet} from "../wallet";
@Injectable({
  providedIn: 'root'
})
export class WalletserviceService {

  constructor(private httpClient:HttpClient) { }

  getAllWallets():Observable<any>{
    return this.httpClient.get("http://localhost:8010/getThemAll");
  }

  getWalletById(id:string|null):Observable<any>{
    return this.httpClient.get("http://localhost:8010/getWalletById/"+id);
  }

  addWallet(newWallet:Wallet):Observable<any>{
    let url:string = "http://localhost:8010/addingWallet";
    return this.httpClient.post(url,newWallet,{responseType:'json'});
  }

  deleteWalletById(id?:number):Observable<any>{
    return this.httpClient.delete("http://localhost:8010/removeWallet/"+id);
  }

  updateWallet(wallet:Wallet):Observable<any>{
    return this.httpClient.put("http://localhost:8010/updateWallet",wallet);
  }

}
