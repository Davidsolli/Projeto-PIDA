import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private baseUrl = 'http://localhost:8080/orders'; // Altere para a URL correta da API

  constructor(private http: HttpClient) {}

  createOrder(order: any): Observable<any> {
    const token = sessionStorage.getItem('token'); // Recupera o token do session storage
    const headers = new HttpHeaders({
      Authorization: `Bearer ${token}`, // Adiciona o token no cabeçalho
    });

    return this.http.post<any>(`${this.baseUrl}`, order, { headers });
  }
}
