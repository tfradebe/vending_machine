import {Component, inject, Input, OnInit, output} from '@angular/core';
import {ApiService} from '../services/api.service';
import {PaymentService} from '../services/PaymentService';
import {CommonModule} from '@angular/common';
import {Denomination, TFPaymentRequest, TFPaymentResponse} from '../models/payment';

@Component({
  selector: 'app-payment',
  imports: [
    CommonModule
  ],
  templateUrl: './payment.html',
  styleUrl: './payment.css'
})
export class Payment implements OnInit{
  private apiService = inject(ApiService);
  private paymentService = inject(PaymentService);

  @Input() cartTotal: number = 0;

  paymentCompleted = output<boolean>();
  paymentCancelled = output<void>();

  denominations = this.paymentService.denominationsSignal;
  insertedMoney = this.paymentService.insertedMoneySignal;
  totalInserted = this.paymentService.totalInserted;

  loading = false;
  error: string | null = null;
  changeCalculation: TFPaymentResponse | null = null;
  paymentProcessing = false;

  ngOnInit(): void {
    this.loadDenominations();
  }

  loadDenominations(): void {
    this.loading = true;
    this.apiService.getDenominations().subscribe({
      next: (response) => {
        this.paymentService.setDenominations(response.data.changeAsDenominators);
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Failed to load payment denominations';
        this.loading = false;
        console.error('Error loading denominations:', error);
      }
    });
  }

  insertMoney(denomination: Denomination): void {
    this.paymentService.addMoney(denomination.value);
    this.error = null;
    this.changeCalculation = null;
  }

  removeMoney(amount: number): void {
    this.paymentService.removeMoney(amount);
  }

  clearMoney(): void {
    this.paymentService.clearMoney();
    this.changeCalculation = null;
    this.error = null;
  }

  calculateChange(): void {
    if(this.totalInserted() < this.cartTotal){
      this.error = `Insufficient funds. Please insert at least R${this.cartTotal}`;
      return;
    }

    this.loading = true;
    const paymentRequest: TFPaymentRequest = {
      cartTotal: this.cartTotal,
      moneyInserted: this.paymentService.getInsertedMoney()
    }

    this.apiService.processPayment(paymentRequest).subscribe({
      next: (response) => {
        this.changeCalculation = response;
        this.loading = false;
        this.error = null;
      },
      error: (error) => {
        this.error = error.error?.error || 'Failed to calculate change';
        this.loading = false;
        console.error('Error calculating change', error);
      }
    });
  }

  confirmPayment(): void {
    this.paymentProcessing = true;
    setTimeout(() => {
      this.paymentProcessing = false;
      this.paymentCompleted.emit(true);
      this.paymentService.clearMoney();
    }, 2000);
  }

  cancelPayment(): void {
    this.paymentService.clearMoney();
    this.paymentCancelled.emit();
  }

  get changeAmount(): number {
    return this.changeCalculation?.data.change || 0;
  }

  formatAmount(amount: number): string {
    return `R${amount}`;
  }

  get canCalculateChange(): boolean {
    return this.changeCalculation !== null && this.changeCalculation.status === 'SUCCESS';
  }

  get canConfirmPayment(): boolean {
    return this.changeCalculation !== null && this.changeCalculation.status === 'SUCCESS';
  }

  get changeDenominations(): number[] {
    return this.changeCalculation?.data.changeAsDenominators || [];
  }

  getChangeDenominationGroups(): {value: number; count: number}[]{
    const groups: {[key: number]: number} = {};

    this.changeDenominations.forEach(denom => {
      groups[denom] = (groups[denom] || 0) + 1;
    });

    return Object.entries(groups).map(([value, count]) => ({
      value: parseInt(value),
      count
    })).sort((a,b) => b.value - a.value);
  }

}
