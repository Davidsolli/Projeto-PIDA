import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { Seller } from '../../models/seller.model';
import { SellerService } from '../../services/seller.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-sellers-list',
  standalone: true,
  imports: [HeaderComponent, NgFor],
  templateUrl: './sellers-list.component.html',
  styleUrl: './sellers-list.component.scss',
})
export class SellersListComponent {
  sellers: Seller[] = [];
  page = 0;
  size = 3;
  totalElements = 0;

  constructor(private sellerService: SellerService) {
    this.sellerList(this.page, this.size);
  }

  sellerList(page: number, size: number) {
    this.sellerService.getSellers(page, size).subscribe({
      next: (response) => {
        this.sellers = response.content;
        this.totalElements = response.totalElements;
        this.page = page; // Atualiza a página atual
      },
      error: (error) => {
        console.error('Erro ao buscar vendedores', error);
      },
    });
  }
}
