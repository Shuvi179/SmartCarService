import { Component, OnInit,Input } from '@angular/core';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import{CarsService} from 'src/app/cars/cars.service';
import {Car } from 'src/app/cars/car';
import{Dealer} from 'src/app/dealer/dealer';
import { from, Observable } from 'rxjs';
import { TradeInService } from './trade-in.service';
import {TradeIn} from './tradein';
@Component({
  selector: 'app-trade-in',
  templateUrl: './trade-in.component.html',
  styleUrls: ['./trade-in.component.scss']
})
export class TradeInComponent implements OnInit {
  username: String;
  //  mycar:Car={id:0,brand:'',model:'',graduation_year:'',number:'',price:0,vin:'',end_guarantee:'',diller:null,user:null};
  // mycartmp:Car={id:0,brand:'',model:'',graduation_year:'',number:'',price:0,vin:'',end_guarantee:'',diller:null,user:null};
  mycar:Car;
dealers: Dealer[];
deallerCars:Car[];
allCars:Car[];
tradeIn:TradeIn;
  @Input()
  vin: String;

  constructor(private tradeInService:TradeInService,private carsService:CarsService,private tokenStorage: TokenStorageService,private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
// this.vin=this.getFromRouterParams('vin');

this.route.params.subscribe(params=>{
  this.vin=params["vin"];
})

    this.carsService.getCarByVin(this.vin).subscribe(data=>this.mycar=data);
    this.tradeInService.getAllCars().subscribe(data=>this.allCars=data);

this.tradeInService.getAllDealers().
subscribe(data=>this.dealers=data)



    this.username = this.tokenStorage.getUsername();


  }

  private getFromRouterParams(param: string) {
    return this.route.snapshot.queryParamMap.get(param);
  }

  private getCars(edr:String){
return this.tradeInService.getAllCarsByDealerEdr(edr).subscribe();
  }

  sendTradeIn(newCarvin: String,UsedCarvin: String){
    console.log(newCarvin);
    console.log(UsedCarvin);


    return this.tradeInService.sendTradeIn(newCarvin,UsedCarvin).subscribe();
}

  }
