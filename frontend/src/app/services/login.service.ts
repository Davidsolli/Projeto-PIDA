import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap, catchError } from 'rxjs';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {
  apiUrl: string = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  login(login: string, password: string) {
    return this.http
      .post<LoginResponse>(`${this.apiUrl}/auth/login`, { login, password })
      .pipe(
        tap((value) => {
          sessionStorage.setItem('token', value.token);
        })
      );
  }

  signup(login: string, password: string, name: string, userRole: string) {
    return this.http.post<LoginResponse>(`${this.apiUrl}/auth/register`, {
      login,
      password,
      name,
      userRole,
    });
  }
}
