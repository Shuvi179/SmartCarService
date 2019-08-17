import { Component, OnInit } from '@angular/core';
// import { TokenStorageService } from '../auth/token-storage.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { DealerSto } from './dealersto';
import{DealerstoaddService} from './dealerstoadd.service';
import { from } from 'rxjs';
import { Techservice } from 'src/app/techservice/techservice';
import {Apply} from 'src/app/dealers/apply';
@Component({
  selector: 'app-dealerstoadd',
  templateUrl: './dealerstoadd.component.html',
  styleUrls: ['./dealerstoadd.component.scss']
})
export class DealerstoaddComponent implements OnInit {
  username: String;

// techserviceStub: Techservice = {stoId:-1, name: '', address: '', workers:[], dealer: null, techManager: null};
stos: Techservice[];
apply:Apply;
  constructor(private tokenStorage: TokenStorageService,private dealerstoaddService:DealerstoaddService) { }


  ngOnInit() {
    this.username = this.tokenStorage.getUsername();

    this.dealerstoaddService.getAllStosApply(this.username).subscribe(data=>this.stos=data);

  }

  add(stoId:number){

    this.dealerstoaddService.addSto(new Apply(this.tokenStorage.getUsername(),stoId)).subscribe();
  }
  deleteApply(stoId:number){
    this.dealerstoaddService.deleteApply(new Apply(this.tokenStorage.getUsername(),stoId)).subscribe();

  }


}
