package com.example.restapipractice.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.restapipractice.entities.Product;

public interface ProductService {

	public ResponseEntity<Map<String, Object>> createProduct(Product product);
	
	public ResponseEntity<Map<String,Object>> getAllProducts();
	
	public ResponseEntity<Map<String,Object>> getOneProduct(int id);
	
	public  ResponseEntity<Map<String,Object>> updateProduct(int id,Product product) throws Exception;
	
	public  ResponseEntity<Map<String,Object>> deleteProduct(int id);
}
