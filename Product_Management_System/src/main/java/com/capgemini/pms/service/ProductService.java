package com.capgemini.pms.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Selection;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.capgemini.pms.entity.Product;
import com.capgemini.pms.exception.ProductManagementSystemException;
import com.capgemini.pms.dao.ProductDao;

/***************************************************************************************************************************
 *  @author          ALISHA RAJ
 *  Description      It is a Service class that provides the services for adding a new product,showing all the products,
                     finding product by productID and deleting product by productId,updating product,view product by productName. 
 *  Version          1.0
 *  Created Date     
 **************************************************************************************************************************/


@Service
public class ProductService{
	
	@Autowired
	ProductDao productDao;


	@Transactional
	public List<Product> viewAllProducts() {



		return (List<Product>) productDao.findAll();
	}
    @Transactional
	public Product addProduct(Product product) {
    	
		int productId = 0;
		product.setProductId(productId);
		productDao.save(product);
		return product;
	}
    @Transactional
	public boolean editProduct(Product product) {
		//Optional is a data type 
		Optional<Product> find = productDao.findById(product.getProductId());
		if(find.isPresent()) {
			productDao.save(product);
			return true;
		}
		 
		return false;
	}


    @Transactional
	public boolean deleteProduct(int productId) {
    	Optional<Product> find = productDao.findById(productId);
		if(find.isPresent()) {
		productDao.deleteById(productId); 
		return true;}
		else
		return false;
		
	}
    @Transactional
    public List<Product>  getProductByName(String productName){
  return  (List<Product>) productDao.findByproductName(productName);
	}
	

    @Transactional
    public Optional<Product> getProductById(int productId){
		return productDao.findById(productId);
	}
	
}

