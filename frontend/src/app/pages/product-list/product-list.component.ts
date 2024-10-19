import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { Page } from '../../models/page.model';
import { Product } from '../../models/products.model';
import { ActivatedRoute } from '@angular/router';
import { ProductsService } from '../../services/products.service';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [HeaderComponent, DefaultLoginLayoutComponent, NgIf, NgFor],
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  sellerId = 0;
  page = 0;
  size = 10;
  totalElements = 0;
  totalPages = 0;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.sellerId = id ? +id : 0;
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService
      .getProductsBySeller(this.sellerId, this.page, this.size)
      .subscribe((data: Page<Product>) => {
        this.products = data.content;
        this.totalElements = data.totalElements;
        this.totalPages = data.totalPages;
        this.size = data.size;
        this.page = data.number;
      });
  }

  // Função para carregar a página anterior
  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadProducts();
    }
  }

  // Função para carregar a próxima página
  nextPage(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadProducts();
    }
  }
}
