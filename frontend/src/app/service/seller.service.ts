import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from "@angular/common/http";
import { Seller } from '../models/seller.model';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  private apiUrl = `${environment.api}/seller`;

  constructor(private httpClient: HttpClient) { }

  getSellers(page: number, size: number): Observable<any> {
    let params = new HttpParams()
    .set('page', page.toString())
    .set('size', size.toString());

    return this.httpClient.get<Seller[]>(this.apiUrl, { params });
  }
}
