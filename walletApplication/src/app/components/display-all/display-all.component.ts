import {Component, OnInit} from '@angular/core';
import {WalletserviceService} from "../../services/walletservice.service";
import {Wallet} from "../../wallet";
import { Router } from '@angular/router';

@Component({
  selector: 'app-display-all',
  templateUrl: './display-all.component.html',
  styleUrls: ['./display-all.component.css']
})
export class DisplayAllComponent implements OnInit{

  msg="";
  errorMsg=""
  wallets:Wallet[]=[];
  constructor(private router: Router,private wallet:WalletserviceService ){}
  ngOnInit(): void {
    this.wallet.getAllWallets().subscribe(
      {
        next: (data) => {
          console.log(data);
          this.wallets= data;
        },
        error: (err) => {
          console.log(err);

        },
        complete: () => { }
      }
    )

  }

  deleteWalletById(id?: number): void {
    console.log("Delete wallet id:" + id);
    if (window.confirm("Do you want to delete Wallet?"))
      this.wallet.deleteWalletById(id).subscribe(
        {
          next: (data) => { // success
            this.msg = "Wallet Deleted Successfully . Id:" + id;
            this.errorMsg = "";
            // to filter the employee having deleted id

            this.wallets = this.wallets.filter((emp) => {
                console.log("wallet.id:" + emp.walletId);
                console.log("id:" + id);

                if (emp.walletId != id) {
                  console.log("true :" + emp.walletId);
                  return true;
                }

                else {
                  console.log("false :" + emp.walletId);
                  return false;

                }

              }
            );
            console.log(this.wallets);
            // reload all employees
          },
          error: () => {
            this.errorMsg = "Wallet Could not be deleted.";
            this.msg = "";
          },
          complete: () => { }
        }
      )

  }

  updateWallet(emp: Wallet) {
    console.log("Update emp");
    console.log(emp);
    //this.router.navigate(['details',this.name]);
    this.router.navigate(['update', emp.walletId]);
  }


}
