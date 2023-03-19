import {Component, OnInit} from '@angular/core';
import {Wallet} from "../../wallet";
import { ActivatedRoute, Router } from '@angular/router';
import {WalletserviceService} from "../../services/walletservice.service";
@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit{
  msg: string = "";
  errorMsg: string = "";

  id: string | null = "";
  wallet: Wallet = new Wallet();

  constructor(private router:Router,private activatedRoute: ActivatedRoute, private walletService: WalletserviceService) { }

  ngOnInit(): void {
    this.id = this.activatedRoute.snapshot.paramMap.get("id");
    console.log("update id:" + this.id);
    this.walletService.getWalletById(this.id).subscribe(

      {
        next: (data) => {
          this.wallet = data;
          console.log(data);

        },
        error: (error) => {
          console.log(Error);

        }
      }

    )

  }

  updateWallet(){
    console.log("Update WALLET:");
    console.log(this.wallet);
    this.walletService.updateWallet(this.wallet).subscribe(
      {
        next:(data)=>{
          this.msg= "Wallet updated successfully";
          this.errorMsg= "";
          this.router.navigateByUrl("displayall");
        },
        error:(err)=>{
          console.log(err.error);
          this.msg= "";
          this.errorMsg= JSON.stringify(err.error);

        }
      }
    )
  }


}
