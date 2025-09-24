import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiResponse} from '../models/product';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private productApiUrl = 'http://localhost:9090/api/v1'

  constructor(private http: HttpClient){}

  getProducts(): Observable<ApiResponse>{
    return this.http.get<ApiResponse>(`${this.productApiUrl}/products`);
  }
}
