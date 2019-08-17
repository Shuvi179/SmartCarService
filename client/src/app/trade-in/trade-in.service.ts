import { Injectable } from '@angular/core';
import {Dealer } from 'src/app/dealer/dealer';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, from } from 'rxjs';
import {Car} from 'src/app/cars/car';
import {TradeIn} from './tradein';
import { Globals } from '../globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};
@Injectable({
  providedIn: 'root'
})
export class TradeInService {

  constructor(private http:HttpClient) { }

getAllDealers():Observable<Dealer[]>{
return this.http.get<Dealer[]>(Globals.baseURL + '/dealer/getAll');
}

getAllCarsByDealerEdr(edr:String):Observable<Car[]>{
return this.http.get<Car[]>( Globals.baseURL + '/dealer/getAllCars/'+edr);
}
getAllCars():Observable<Car[]>{
  return this.http.get<Car[]>( Globals.baseURL + '/dealer/allCars');
    }

  sendTradeIn(newCarvin: String,UsedCarvin: String):Observable<TradeIn>{
return this.http.post<TradeIn>( Globals.baseURL + '/dealer/createTradeIn/?vinNewCar='+ newCarvin+'&vinBCar='+UsedCarvin,httpOptions);
    }

}
