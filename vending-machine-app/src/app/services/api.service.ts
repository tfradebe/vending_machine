import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ApiResponse} from '../models/product';
import {Observable} from 'rxjs';
import {TFPaymentRequest, TFPaymentResponse} from '../models/payment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private productApiUrl = 'http://localhost:9090/api/v1'

  constructor(private http: HttpClient){}

  getProducts(): Observable<ApiResponse>{
    return this.http.get<ApiResponse>(`${this.productApiUrl}/products`);
  }

  checkout(orderData: any): Observable<any> {
    return this.http.post(`${this.productApiUrl}/checkout`, orderData);
  }

  processPayment(paymentRequest: TFPaymentRequest): Observable<TFPaymentResponse> {
    return this.http.post<TFPaymentResponse>(`${this.productApiUrl}/payment`, paymentRequest);
  }

  getDenominations(): Observable<TFPaymentResponse>{
    return this.http.get<TFPaymentResponse>(`${this.productApiUrl}/payment`);
  }
}
