import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Product} from '../models/product';
import {ApiService} from '../services/api.service';
import {CartService} from '../services/cart.service';
import {NgFor, NgIf} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [
    NgIf,
    NgFor
  ],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductList implements OnInit {

  products: Product[] = [];
  loading = true;
  error: string | null = null;

  constructor(private apiService: ApiService
              , protected cartService: CartService
              , private router: Router
    , private changeDetectorRef: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.loading = true;
    this.error = null;

    this.apiService.getProducts().subscribe({
      next: (response) => {
        if (Array.isArray(response.data)) {
          this.products = response.data;
          console.log("Products loaded successfully:", this.products);
          this.changeDetectorRef.markForCheck();
        } else {
          this.error = "Invalid data format";
          console.error("API response data is not an array:", response.data)
        }
        this.loading = false;
      },
      error: (error) => {
        this.error = 'Failed to load products. Please try again.';
        this.loading = false;
        console.error('Error loading products:', error);
      }
    });
  }

  addToCart(product: Product): void {
    if (product.quantity > 0) {
      this.cartService.addToCart(product);
    }
  }

  isOutOfStock(product: Product): boolean {
    return product.quantity <= 0;
  }

  viewCart(): void {
    this.router.navigate(['/cart']);
  }

}
