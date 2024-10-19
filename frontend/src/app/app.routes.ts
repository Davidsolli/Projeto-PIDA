import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { SignupComponent } from './pages/signup/signup.component';
import { SellersListComponent } from './pages/sellers-list/sellers-list.component';
import { AuthGuardService } from './services/auth-guard.service';
import { OrderComponent } from './pages/order/order.component';
import { ProfileComponent } from './pages/profile/profile.component';
import { ProductListComponent } from './pages/product-list/product-list.component';

export const routes: Routes = [
    {
        path: "",
        component: LoginComponent
    },
    {
        path: "signup",
        component: SignupComponent
    }, 
    {
        path: "sellers",
        component: SellersListComponent,
    },
    {
        path: "orders",
        component: OrderComponent,
        canActivate: [AuthGuardService]
    },
    {
        path: "profile",
        component: ProfileComponent,
        canActivate: [AuthGuardService]
    },
    {
        path: "products/page/:id",
        component: ProductListComponent
    }
];
