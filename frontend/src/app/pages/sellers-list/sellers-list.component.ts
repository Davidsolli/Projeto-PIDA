import { Component } from '@angular/core';
import { HeaderComponent } from "../../components/header/header.component";

@Component({
  selector: 'app-sellers-list',
  standalone: true,
  imports: [HeaderComponent],
  templateUrl: './sellers-list.component.html',
  styleUrl: './sellers-list.component.scss'
})
export class SellersListComponent {

}
