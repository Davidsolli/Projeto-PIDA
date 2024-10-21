import { Component } from '@angular/core';
import { HeaderComponent } from '../../components/header/header.component';
import { User } from '../../models/user.model';
import { UserService } from '../../services/user.service';
import { FormsModule, NgModel } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [HeaderComponent, FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  user: User = {
    id: 0,
    name: '',
    email: '',
    age: 0,
    address: '',
    number: '',
    birthDate: '',
    imgUrl: '',
    cpf: '',
    gender: '',
    userRole: '',
  };

  constructor(
    private userService: UserService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.userService.getUser().subscribe({
      next: (user) => {
        this.user = user;
      },
      error: (err) => {
        console.error('Erro ao carregar perfil:', err);
      },
    });
  }

  updateProfile(): void {
    this.userService.updateUser(this.user).subscribe({
      next: (updatedUser) => {
        this.toastr.success('Perfil atualizado!');
      },
      error: (err) => {
        this.toastr.error('Error na atualização de perfil!');
      },
    });
  }
}
