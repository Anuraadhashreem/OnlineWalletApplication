import { Component } from '@angular/core';
import {Wallet} from "../../wallet";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  wallet: Wallet = { walletUserName: "", password: "" };
  constructor(private router:Router){}

  displayUserForm(){
    console.log("displayUserForm()");
    console.log(this.wallet);
  }

  submitLoginForm(){
    console.log("submitLoginForm()");

    // if user is authorised navigate to home/dashboard page
    if(this.wallet.walletUserName=="ford" && this.wallet.password=="ford1234")
      this.router.navigateByUrl("displayall");
    else
      this.router.navigateByUrl("register");

  }

}
