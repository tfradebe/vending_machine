import { Component, signal } from '@angular/core';
import {ProductList} from './product-list/product-list';
import {RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [ProductList, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('vending-machine-app');
}
