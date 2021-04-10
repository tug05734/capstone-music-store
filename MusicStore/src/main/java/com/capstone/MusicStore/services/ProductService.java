package com.capstone.MusicStore.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.MusicStore.entities.Product;
import com.capstone.MusicStore.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Iterable<Product> GetAllMusic()
    {
        return productRepository.findAll();
    }
	
	public Iterable<Product> SearchProductByKeyword(String keyword)
    {
       
        List<Product> result = new ArrayList<Product>();


        for (Product p : productRepository.findAll()) {
            if (p.getProductName().toLowerCase().contains(keyword.toLowerCase()) && !result.contains(p)) {
                result.add(p);
            }
            if(p.getCondition().toLowerCase().contains(keyword.toLowerCase()) && !result.contains(p)) {
            	result.add(p);
            }
            if(p.getPrice().toString().contains(keyword) && !result.contains(p)) {
            	result.add(p);
            }
            if(p.getCategory().getName().toLowerCase().contains(keyword.toLowerCase()) && !result.contains(p)) {
            	result.add(p);
            }
            if(p.getGenre().getName().toLowerCase().contains(keyword.toLowerCase()) && !result.contains(p)) {
            	result.add(p);
            }
        }
        return result;
    }
	
	public Product AddProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void DeleteProduct(Product product) {
		productRepository.deleteById(product.getProductID());
	}
	
	public Product FindProductByName(String name) {
		return productRepository.findProductByName(name);
	}
	
	public Product FindProductByPrice(BigDecimal price) {
		return productRepository.findProductByPrice(price);
	}
	
	public Optional<Product> FindRepositoryById(int id) {
		return productRepository.findById(id);
	}
	
	
}
