import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Seller } from '../models/seller.model';
import { Page } from '../models/page.model';

@Injectable({
  providedIn: 'root',
})
export class SellerService {
  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  getSellers(page: number, size: number): Observable<Page<Seller>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());

    return this.http.get<Page<Seller>>(`${this.apiUrl}/seller/page`, {
      params,
    });
  }
}
