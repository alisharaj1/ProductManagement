package com.capgemini.pms.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pms.dao.ProductDao;
import com.capgemini.pms.entity.Product;
import com.capgemini.pms.exception.ProductManagementSystemException;
import com.capgemini.pms.service.ProductService;
/***************************************************************************************************************************
 *  @author         ALISHA RAJ
 *  Description      It is a Controller class that provides the Control for Adding new product,viewing all the products,
                     view by id, view by name, updating product and deleting a product. 
 *  Version          1.0
 *  Created Date     
 **************************************************************************************************************************/

@RestController
@RequestMapping
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;
	ProductDao productDao;
	
	@GetMapping("/viewallproducts")
	List<Product> viewAllProducts() {
		return productService.viewAllProducts();
	}
	
/***************************************************************************************************************************
*  Method       :addProduct
*  Description  :To add a new product
*  @returns String:It will return product added successfully if product is added and return error statement if not added
*  @throw ProductManagementSystemException :It is raised due to invalid price,invalid quantity
*  Created By   :@author ALISHA RAJ
*  Created Date :   
***************************************************************************************************************************/


	@PostMapping("/addproduct")
	public ResponseEntity<String> addProduct(@RequestBody Product product, BindingResult result) {
	String errorMessage = "";
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors)
				errorMessage += error.getDefaultMessage() + "<br/>";
			throw new ProductManagementSystemException(errorMessage);
		}
		try {
			Product products = productService.addProduct(product);

			return new ResponseEntity<String>(
					"product added successfully: " + products.getProductId() + " " + " is your product id", new HttpHeaders(),
					HttpStatus.OK);

		} catch (DataIntegrityViolationException ex) {
			throw new ProductManagementSystemException(ex.getMessage());
		}
	}
	/***************************************************************************************************************************
	*  Method       :deleteProduct
	*  Description  :To delete a product
	*  @returns String:It will return product deleted successfully if product is deleted and no such product present if id does not exists
	                   and return error statement if productId is  not valid
	*  @throw ProductManagementSystemException :It is raised due to invalid productId
	*  Created By   :@author ALISHA RAJ
	*  Created Date :   
	***************************************************************************************************************************/


	@DeleteMapping("/deleteproduct/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable int productId)  {
		try {
			
			boolean Result=productService.deleteProduct(productId);
			if(Result){
			return new ResponseEntity<String>("Product deleted!", HttpStatus.OK);}
			else {return new ResponseEntity<String>("No product with this product Id exists", new HttpHeaders(),HttpStatus.OK);
			}}

			
		 catch (DataIntegrityViolationException ex) {
			 
			throw new ProductManagementSystemException(ex.getMessage());
		}
	}
	/***************************************************************************************************************************
	*  Method       :editProduct
	*  Description  :To update a product
	*  @returns String:It will return product updated successfully if product is updated and return error statement if not updated
	*  @throw ProductManagementSystemExceptionn :It is raised due to invalid price,invalid quantity
	*  Created By   :@author ALISHA RAJ
	*  Created Date :   
	***************************************************************************************************************************/


	@PostMapping("/editproduct/{productId}")
	public ResponseEntity<String> editProduct(@Valid @RequestBody Product product, BindingResult result)
	 {
		String errorMessage = "";
		if (result.hasErrors()) {
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors)
				errorMessage += error.getDefaultMessage() + "<br/>";
			throw new ProductManagementSystemException(errorMessage);
		}
		try {
			if (productService.editProduct(product)) {
				return new ResponseEntity<String>("Product updated successfully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Update Operation Unsuccessful,Provided Id does not exist", HttpStatus.OK);
			}

		} catch (DataIntegrityViolationException ex) {
			throw new ProductManagementSystemException(ex.getMessage());
		}
	}
	/***************************************************************************************************************************
	*  Method       :getProductByName
	*  Description  :To find product by its name 
	*  @PathVariable productName: It will take productName given by a product master during adding of product
	*  @throws ProductManagementSystemException:It is raised due to invalid name      

	*  @returns list:It will return list of products having the given name and null if no such product is there in the database
	*  Created By   :@author ALISHA RAJ
	*  Created Date :   
	***************************************************************************************************************************/
		

	@GetMapping("/getproductbyname/{productName}")
	public List<Product>  getProductByName(@PathVariable String productName) {
		try{List<Product> list=productService.getProductByName(productName);
		if(list!=null && !list.isEmpty())
		{return list;}
		else
		{
		return list;
		}}
		catch(Exception e)
		{
			throw new ProductManagementSystemException(e.toString());}
	}
	/***************************************************************************************************************************
	*  Method       :getProductById
	*  Description  :To find product by its id
	*  @PathVariable productId: It will take productId
	*  @throws ProductManagementSystemException:It is raised due to invalid id 

	*  @returns Product:It will return product having the given id and null if no such product is there in the database
	
	*  Created By   :@author ALISHA RAJ
	*  Created Date :   
	***************************************************************************************************************************/
		

	@GetMapping("/getproductbyid/{productId}")
	public Optional<Product> getProductById(@PathVariable int productId)  {
		try {
			return productService.getProductById(productId);
		} catch (Exception ex) {
			throw new ProductManagementSystemException(ex.getMessage());
		}
	}
	
}
	
	

