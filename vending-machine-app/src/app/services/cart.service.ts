import {BehaviorSubject} from 'rxjs';
import {Injectable} from '@angular/core';
import {Product} from '../models/product';

export interface CartItem {
  id: number;
  name: string;
  location: string;
  price: number;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = new BehaviorSubject<CartItem[]>([]);
  public cartItems$ = this.cartItems.asObservable();

  addToCart(product: Product): void{
    const currentItems = this.cartItems.value;

    const  existingItem = currentItems.find(item => item.id === product.id);

    if(existingItem){
      existingItem.quantity += 1;
    } else {
      currentItems.push({...product, quantity: 1});
    }
    this.cartItems.next([...currentItems]);
  }

  removeFromCart(productId: number): void{
    const currentItems = this.cartItems.value.filter(item => item.id !== productId);
    this.cartItems.next([... currentItems]);
  }

  getTotalItems(): number{
    return this.cartItems.value.reduce((total, item) => total + item.quantity, 0);
  }

  getTotalPrice(): number {
    return this.cartItems.value.reduce((sum,item)=>sum+(item.price*item.quantity), 0);
  }

  clearCart(){
    this.cartItems.next([]);
  }
}
