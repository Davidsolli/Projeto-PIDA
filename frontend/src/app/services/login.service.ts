import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginResponse } from '../types/login-response.type';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(email: string, password: string) {
    return this.http.post<LoginResponse>("http://localhost:8080/auth/login", {email, password}).pipe(
     tap((value) => {
        sessionStorage.setItem("token", value.token)
      })
    );
  }
}
