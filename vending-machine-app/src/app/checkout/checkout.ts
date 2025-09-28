import {Component, inject} from '@angular/core';
import {CartService} from '../services/cart.service';
import {Router} from '@angular/router';
import { Payment } from '../payment/payment'; // Make sure this import is correct

@Component({
  selector: 'app-checkout',
  imports: [
    Payment
  ],
  template: `
    <div class="checkout-container">
      @if (showPayment) {
        <app-payment
          [cartTotal]="cartService.totalPrice()"
          (paymentCompleted)="onPaymentCompleted($event)"
          (paymentCancelled)="onPaymentCancelled()">
        </app-payment>
      } @else {
        <div class="checkout-content">
          <button (click)="proceedToPayment()" class="primary-btn">
            Proceed to Payment
          </button>
        </div>
      }
    </div>
  `
})
export class Checkout {
  protected cartService = inject(CartService);
  private router = inject(Router);

  showPayment = false;

  proceedToPayment(): void {
    this.showPayment = true;
  }

  onPaymentCompleted(success: any): void {
    const isSuccess = Boolean(success);
    if(isSuccess) {
      this.router.navigate(['/success']);
    }
  }

  onPaymentCancelled(): void {
    this.showPayment = false;
  }
}

export default Checkout
