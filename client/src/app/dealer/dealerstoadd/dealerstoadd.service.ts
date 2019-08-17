import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { from, Observable } from 'rxjs';
import { DealerSto } from './dealersto';
import{Techservice} from 'src/app/techservice/techservice';
import {Apply} from 'src/app/dealers/apply';
import { Globals } from 'src/app/globals';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DealerstoaddService {

  constructor(private http:HttpClient) { }

  // createDealerSto(username:String,sto:DealerSto):Observable<DealerSto>{

  //   return this.http.put<DealerSto>('http://localhost:9501/api/dealer/createtechservices/?username=' + username + '&name='+sto.name + '&address='+ sto.address,sto);

  // }
  //  createDealerSto(username:String,sto:Techservice):Observable<Techservice>{
  //   return this.http.put<Techservice>('http://localhost:9501/api/dealer/createtechservices/?username=' + username + '&name='+sto.name + '&address='+ sto.address,sto);
  // }

  // getAllDealersSto(username:String):Observable<Techservice[]>{
  //   return this.http.get<Techservice[]>('http://localhost:9501/api/dealer/allstos/'+username);
  // }

  getAllStosApply(username:String):Observable<Techservice[]>{
      return this.http.get<Techservice[]>(Globals.baseURL + '/dealer/allstosToApply/'+username);
  }


  // editDealer(dealer: Dealer):Observable<HttpResponse<Dealer>>{
  //   return this.http.post<Dealer>('http://localhost:9501/api/dealer/edit',dealer,{observe: 'response' });
  //
  // }


  addSto(apply:Apply):Observable<HttpResponse<Apply>>{

return this.http.post<Apply>(Globals.baseURL + '/dealer/addStoWithApply',apply,{observe: 'response' });
  }

deleteApply(apply:Apply):Observable<HttpResponse<Apply>>{
return this.http.post<Apply>(Globals.baseURL + '/dealer/ignoreApply',apply,{observe: 'response' })
}

}
