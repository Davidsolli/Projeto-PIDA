import { Component } from '@angular/core';
import { Seller } from 'src/app/models/seller.model';
import { SellerService } from 'src/app/service/seller.service';

@Component({
  selector: 'app-seller',
  templateUrl: './seller.component.html',
  styleUrls: ['./seller.component.css']
})
export class SellerComponent {
  title = 'frontend';

  sellers: Seller[] = [];
  page = 0; // Página inicial
  size = 10; // Tamanho da página
  totalElements = 0; // Número total de elementos

  constructor(private sellerService: SellerService) {
    this.sellerList(this.page, this.size);
    
  }

  sellerList(page: number, size: number) {
    this.sellerService.getSellers(page, size).subscribe({
      next: (response) => {
        this.sellers = response.content; // Dados da página atual
        this.totalElements = response.totalElements; // Total de vendedores
      },
      error: (err) => {
        console.error('Erro ao buscar vendedores:', err);
      }
    });
  }

  nextPage() {
    if ((this.page + 1) * this.size < this.totalElements) {
      this.page++;
      this.sellerList(this.page, this.size);
    }
  }

  previousPage() {
    if (this.page > 0) {
      this.page--;
      this.sellerList(this.page, this.size);
    }
  }
}
