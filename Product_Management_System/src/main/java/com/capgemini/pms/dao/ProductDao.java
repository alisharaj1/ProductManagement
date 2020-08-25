package com.capgemini.pms.dao;
import java.util.List;
import java.util.Optional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.capgemini.pms.entity.Product;



@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	@Query("select p from Product p where p.productName=:productName")
	List<Product> findByproductName(String productName);
}
