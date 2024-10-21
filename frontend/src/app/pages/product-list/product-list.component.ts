import { Component, OnInit } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { DefaultLoginLayoutComponent } from '../../components/default-login-layout/default-login-layout.component';
import { Page } from '../../models/page.model';
import { Product } from '../../models/products.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from '../../services/products.service';
import { NgFor, NgIf } from '@angular/common';
import { OrderService } from '../../services/order.service';
import { Toast, ToastrService } from 'ngx-toastr';

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
  size = 8;
  totalElements = 0;
  totalPages = 0;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private orderService: OrderService,
    private toastr: ToastrService,
    private router: Router
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

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadProducts();
    }
  }

  nextPage(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadProducts();
    }
  }

  buyProduct(product: Product): void {
    const order = {
      product: {
        id: product.id,
      },
      seller: {
        id: this.sellerId,
      },
      payment: null,
    };

    this.orderService.createOrder(order).subscribe({
      next: () => {
        this.toastr.success('Pedido realizado!');
        this.router.navigate(['orders']);
      },
      error: () => this.toastr.error('Erro ao fazer o pedido!'),
    });
  }
}
