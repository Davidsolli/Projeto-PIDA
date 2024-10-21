import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { NgFor, NgIf } from '@angular/common';
import { OrderService } from '../../services/order.service';
import { Page } from '../../models/page.model';
import { Order } from '../../models/order.model';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [HeaderComponent, NgIf, NgFor],
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss',
})
export class OrderComponent {
  orders: Order[] = [];
  page = 0;
  size = 3;
  totalElements = 0;
  totalPages = 0;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    this.loadOrders();
  }

  loadOrders(): void {
    this.orderService.getOrders(this.page, this.size).subscribe((data: Page<Order>) => {
      this.orders = data.content;
      this.totalElements = data.totalElements;
      this.totalPages = data.totalPages;
    });
  }  

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadOrders();
    }
  }

  nextPage(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadOrders();
    }
  }
}
