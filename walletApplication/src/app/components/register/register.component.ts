import { Component } from '@angular/core';
import {Wallet} from "../../wallet";
import {WalletserviceService} from "../../services/walletservice.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  wallet:Wallet = new Wallet();

  constructor(private walletserviceService:WalletserviceService) {
  }

  onSubmit(){
    console.log(this.wallet);
    this.walletserviceService.addWallet(this.wallet);

    let empPost: Observable<any> =this.walletserviceService.addWallet(this.wallet);
    empPost.subscribe(
      {
        next:(data)=>{ // executes when back end reposnds with success status code
          console.log(data);
        },
        error:(error)=>{ // executes when back end responds with error status code
          console.log(error);
        },
        complete:()=>{ //
          console.log("Post request Completed...")
        }
      }
    )
  }
}
