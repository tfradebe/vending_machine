import {Injectable, signal} from '@angular/core';
import {Product} from '../models/product';

export interface CartItem {
  product: Product;
  quantity: number;
}
@Injectable({
  providedIn: 'root'
})
export class CartService {
  private cartItems = signal<CartItem[]>([]);

  public cartItemsSignal = this.cartItems.asReadonly();

  public totalPrice = signal(0);

  public totalItems = signal(0);

  constructor() {
    this.updateComputedSignals();
  }

  addToCart(product: Product): void {
    const currentItems = this.cartItems();
    const existingItemIndex = currentItems.findIndex(item => item.product.id === product.id);

    if (existingItemIndex > -1) {
      const updatedItems = [...currentItems];
      updatedItems[existingItemIndex] = {
        ...updatedItems[existingItemIndex],
        quantity: updatedItems[existingItemIndex].quantity + 1
      };
      this.cartItems.set(updatedItems);
    } else {
      this.cartItems.set([...currentItems, { product, quantity: 1 }]);
    }
    this.updateComputedSignals();
  }
  removeFromCart(productId: number): void {
    const filteredItems = this.cartItems().filter(item => item.product.id !== productId);
    this.cartItems.set(filteredItems);
    this.updateComputedSignals();
  }
  updateQuantity(productId: number, quantity: number): void {
    if (quantity <= 0) {
      this.removeFromCart(productId);
      return;
    }
    const updatedItems = this.cartItems().map(item =>
      item.product.id === productId ? { ...item, quantity } : item
    );

    this.cartItems.set(updatedItems);
    this.updateComputedSignals();
  }

  clearCart(): void {
    this.cartItems.set([]);
    this.updateComputedSignals();
  }

  private updateComputedSignals(): void {
    const items = this.cartItems();
    this.totalPrice.set(this.calculateTotalPrice(items));
    this.totalItems.set(this.calculateTotalItems(items));
  }

  private calculateTotalPrice(items: CartItem[]): number {
    return items.reduce((total, item) => total + (item.product.price * item.quantity), 0);
  }

  private calculateTotalItems(items: CartItem[]): number {
    return items.reduce((total, item) => total + item.quantity, 0);
  }

  getCartItems(): CartItem[] {
    return this.cartItems();
  }
}
