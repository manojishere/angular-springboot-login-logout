import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountInfoComponent } from './component/account-info/account-info.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { LoginComponent } from './component/login/login.component';
import { RegisterComponent } from './component/register/register.component';
import { AuthGaurd } from './services/auth.guard';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'register' },
  { path: "register", component: RegisterComponent },
  { path: "dashboard", component: DashboardComponent, canActivate: [AuthGaurd] },
  { path: "account-info", component: AccountInfoComponent, canActivate: [AuthGaurd] },
  {path: "login", component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
