import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {CartItem, CartService} from '../services/cart.service';

@Component({
  selector: 'app-cart',
  imports: [],
  templateUrl: './cart.html',
  styleUrl: './cart.css'
})
export class Cart {
  private cartService = inject(CartService);
  private router = inject(Router);

  cartItems = this.cartService.cartItemsSignal;
  totalPrice = this.cartService.totalPrice;
  totalItems = this.cartService.totalItems;

  incrementQuantity(item: CartItem): void {
    this.cartService.updateQuantity(item.product.id, item.quantity + 1);
  }

  decrementQuantity(item: CartItem): void {
    this.cartService.updateQuantity(item.product.id, item.quantity - 1);
  }

  removeItem(productId: number): void {
    this.cartService.removeFromCart(productId);
  }

  proceedToCheckout(): void {
    if (this.totalItems() > 0) {
      this.router.navigate(['/checkout']);
    }
  }

  continueShopping(): void {
    this.router.navigate(['/products']);
  }

  isCartEmpty(): boolean {
    return this.totalItems() === 0;
  }
}
