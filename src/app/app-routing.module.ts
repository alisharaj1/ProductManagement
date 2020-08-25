import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { ProductMasterComponent } from './product-master/product-master.component';
import { GetproductbynameComponent } from './getproductbyname/getproductbyname.component';
import { GetproductbyidComponent } from './getproductbyid/getproductbyid.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import { DeleteproductComponent } from './deleteproduct/deleteproduct.component';
import { ViewallproductsComponent } from './viewallproducts/viewallproducts.component';
import { EditproductComponent } from './editproduct/editproduct.component';
import { ViewproductlistComponent } from './viewproductlist/viewproductlist.component';
import { FooterComponent } from './footer/footer.component';
import { FirstpageComponent } from './firstpage/firstpage.component';



const routes: Routes = [
  {path: '', redirectTo: 'firstpage', pathMatch: 'full'},
  {path: 'firstpage', component:FirstpageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
 {path: 'getproductbyname', component: GetproductbynameComponent},
 {path: 'getproductbyid', component: GetproductbyidComponent},
 {path: 'addproduct', component: AddproductComponent},
 {path: 'deleteproduct', component: DeleteproductComponent},
 {path: 'editproduct' , component: EditproductComponent},
 {path: 'viewallproducts', component: ViewallproductsComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'product-master', component: ProductMasterComponent},
  {path: 'viewproductlist', component: ViewproductlistComponent},
  {path: 'footer', component: FooterComponent},
  
  

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
