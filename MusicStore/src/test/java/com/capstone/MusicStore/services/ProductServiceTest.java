package com.capstone.MusicStore.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.capstone.MusicStore.entities.Product;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ProductService productService;
	
	
	@Test
	public void whenSearchByName_checkProductReturned() {
		Product product = new Product();
		product.setProductName("CD1");
		product.setCondition("New");
		product.setPrice(BigDecimal.valueOf(19));
		entityManager.persist(product);
		entityManager.flush();
		
		Product found = productService.FindProductByName(product.getProductName());
		
		assertEquals(found.getProductName(), product.getProductName());
		assertEquals(found.getCondition(), product.getCondition());
		assertEquals(found.getPrice(), product.getPrice());
	}
	
	@Test
	public void whenSearchByPrice_checkProductReturned() {
		Product product = new Product();
		product.setProductName("Piano");
		product.setCondition("Good");
		product.setPrice(BigDecimal.valueOf(319));
		entityManager.persist(product);
		entityManager.flush();
		
		Product found = productService.FindProductByPrice(product.getPrice());
		
		assertEquals(found.getProductName(), product.getProductName());
		assertEquals(found.getCondition(), product.getCondition());
		assertEquals(found.getPrice(), product.getPrice());
	}
	
	@Test
	public void whenSearchByKeyword_checkProductReturned() {
		Product product = new Product();
		product.setProductName("Record");
		product.setCondition("New");
		product.setPrice(BigDecimal.valueOf(30));
		entityManager.persist(product);
		entityManager.flush();
		
		Iterable<Product> found = productService.SearchProductByKeyword("rec");
		
		for(Product p : found) {
			System.out.println(p.getProductID());
			if(p.getProductName().equals(product.getProductName())) {
				assertEquals(p.getProductName(), product.getProductName());
				assertEquals(p.getCondition(), product.getCondition());
				assertEquals(p.getPrice(), product.getPrice());
			}
		}
	}
	
	@Test
	public void addProduct_checkProductReturned() {
		Product product = new Product();
		product.setProductName("test");
		product.setCondition("New");
		product.setPrice(BigDecimal.valueOf(19));
		productService.AddProduct(product);
		
		Product found = productService.FindProductByName(product.getProductName());
		
		assertEquals(found.getProductName(), product.getProductName());
		assertEquals(found.getCondition(), product.getCondition());
		assertEquals(found.getPrice(), product.getPrice());
	}
	
	@Test
	public void deleteProduct_checkProductReturned() {
		Product product = new Product();
		product.setProductName("Album3");
		product.setCondition("New");
		product.setPrice(BigDecimal.valueOf(19));
		entityManager.persist(product);
		entityManager.flush();
		
		Product found = productService.FindProductByName(product.getProductName());
		
		assertEquals(found.getProductName(), product.getProductName());
		assertEquals(found.getCondition(), product.getCondition());
		assertEquals(found.getPrice(), product.getPrice());
		
		productService.DeleteProduct(found);
		
		assertNull(found);
	}
}
