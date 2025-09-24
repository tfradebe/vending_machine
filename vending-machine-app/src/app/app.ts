import { Component, signal } from '@angular/core';
import {ProductList} from './product-list/product-list';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-root',
  imports: [ ProductList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('vending-machine-app');
}
