import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/products.model';
import { Page } from '../models/page.model';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private apiUrl = 'http://localhost:8080/product/page';

  constructor(private http: HttpClient) {}

  getProductsBySeller(
    sellerId: number,
    page: number,
    size: number
  ): Observable<Page<Product>> {
    return this.http.get<Page<Product>>(
      `${this.apiUrl}/${sellerId}?page=${page}&size=${size}`
    );
  }
}
