import { Component, OnInit, ViewChild } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-addproduct',
  templateUrl: './addproduct.component.html',
  styleUrls: ['./addproduct.component.css']
})
export class AddproductComponent implements OnInit {

  product: Product = new Product();
  msg: string;
  errorMsg: string;
  @ViewChild('frm', {static: false}) frm: NgForm;
  constructor(private productService: ProductService, private router: Router) { }

  ngOnInit(): void {
  }
  addproduct()
  {
    this.productService.addproduct(this.product).subscribe((data) => {
      console.log( 'data' , data);
      this.msg = data;
      this.errorMsg = undefined;
      this.product = new Product();
      this.router.navigate(['/addproduct']);
      this.product = new Product();
      this.frm.reset();
    } ,
      error => {this.msg = undefined;
                this.errorMsg = 'Please enter the valid details'; } );

}}
