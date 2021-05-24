import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegistrationComponent } from './pages/registration/registration.component';
import { RegistrationFormComponent } from './forms/registration-form/registration-form.component';
import { LoginFormComponent } from './forms/login-form/login-form.component';
import { LoginComponent } from './pages/login/login.component';
import {StartComponent} from "./pages/start/start.component";
import { HeaderComponent } from './pages/components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { FooterComponent } from './pages/components/footer/footer.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ArticleComponent } from './pages/article/article.component';
import { AccountInfoComponent } from './pages/account/account-info/account-info.component';
import { AccountComponent } from './pages/account/account/account.component';
import { ProductsComponent } from './pages/products/products.component';
import { CaloriesDataComponent } from './pages/account/calories-data/calories-data.component';
import { AccountOrdersComponent } from './pages/account/account-orders/account-orders.component';
import { AccountAddressesComponent } from './pages/account/account-addresses/account-addresses.component';
import { AccountCardsComponent } from './pages/account/account-cards/account-cards.component';
import { CartComponent } from './pages/components/cart/cart.component';
import { ProductCardComponent } from './pages/components/product-card/product-card.component';

@NgModule({
  declarations: [
    AppComponent,
    RegistrationFormComponent,
    RegistrationComponent,
    LoginFormComponent,
    LoginComponent,
    StartComponent,
    HeaderComponent,
    FooterComponent,
    ArticleComponent,
    AccountInfoComponent,
    AccountComponent,
    ProductsComponent,
    CaloriesDataComponent,
    AccountOrdersComponent,
    AccountAddressesComponent,
    AccountCardsComponent,
    CartComponent,
    ProductCardComponent
  ],
    imports: [
        AppRoutingModule,
        BrowserModule,
        RouterModule.forRoot(
            [
                {path: 'reg', component: RegistrationComponent},
                {path: 'login', component: LoginComponent},
                {path: 'start', component: StartComponent},
                {path: 'account/info', component: AccountInfoComponent},
                {path: 'account/calorie-data', component: CaloriesDataComponent},
                {path: 'account/orders', component: AccountOrdersComponent},
                {path: 'account/addresses', component: AccountAddressesComponent},
                {path: 'account/cards', component: AccountCardsComponent},
                {path: 'products', component: ProductsComponent},
                {path: 'article', component: ArticleComponent},
                {path: '', redirectTo: '/start', pathMatch: 'full'}
            ]
        ),
        HttpClientModule,
        ReactiveFormsModule,
        BrowserAnimationsModule,
        LayoutModule,
        MatToolbarModule,
        MatButtonModule,
        MatSidenavModule,
        MatIconModule,
        MatListModule,
        NgbModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent, HeaderComponent, FooterComponent]
})
export class AppModule { }
