import {Injectable, signal} from '@angular/core';
import {Denomination} from '../models/payment';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private insertedMoney = signal<number[]>([]);
  private denominations = signal<Denomination[]>([]);

  public insertedMoneySignal = this.insertedMoney.asReadonly();
  public denominationsSignal = this.denominations.asReadonly();
  public totalInserted = signal(0);

  constructor(){
    this.updateTotal();
  }

  setDenominations(denominationValue: number[]):void{
    const denoms: Denomination[] = denominationValue.map(value => ({
      value,
      label: this.getDenominationLabel(value),
      count: 0
    })).sort((a,b) => b.value - a.value);

    this.denominations.set(denoms);
  }

  private updateTotal(): void {
    const total = this.insertedMoney().reduce((sum, amount) => sum + amount, 0);
    this.totalInserted.set(total);
  }

  private getDenominationLabel(value: number): string {
    if(value >= 100){
      return `R${value / 100}`;
    } else {
      return `${value}c`
    }
  }

  clearMoney(): void {
    this.insertedMoney.set([]);
    this.denominations.update(denoms => denoms.map(d => ({...d, count: 0})));
    this.updateTotal();
  }

  getInsertedMoney(): number[]{
    return this.insertedMoney();
  }

  addMoney(amount: number): void {
    const currentMoney = this.insertedMoney();
    this.insertedMoney.set([...currentMoney, amount]);
    this.updateTotal();
    this.updateDenominationCount(amount,1);
  }

  removeMoney(amount: number):void{
    const currentMoney = [...this.insertedMoney()];
    const index = currentMoney.indexOf(amount);

    if(index > -1){
      currentMoney.splice(index, 1);
      this.insertedMoney.set(currentMoney);
      this.updateTotal();
      this.updateDenominationCount(amount, -1);
    }
  }

  private updateDenominationCount(amount: number, change: number): void {
    this.denominations.update(demons => demons.map(d => d.value === amount ? {...d, count: Math.max(0, d.count+change)}:d));
  }
}
