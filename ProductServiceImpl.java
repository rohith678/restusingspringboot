package com.example.restapipractice.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.restapipractice.entities.Product;
import com.example.restapipractice.repos.Repository;
import com.example.restapipractice.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	Repository repo;

	@Override
	public ResponseEntity<Map<String, Object>> createProduct(Product product) {
		repo.save(product);
		Map<String, Object> map = new HashMap<>();
		map.put("success", 1);
		map.put("message", "succesfully created");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getAllProducts() {
		List<Product> products = repo.findAll();
		Map<String, Object> map = new HashMap<>();
		map.put("success", 1);
		map.put("data", products);
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getOneProduct(int id) {

		Optional<Product> product = repo.findById(id);
		Map<String, Object> map = new HashMap<>();
		if (product.isPresent()) {
			map.put("success", 1);
			map.put("data", product);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		map.put("success", 0);
		map.put("message", "product not found");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<Map<String, Object>> updateProduct(int id, Product product) throws Exception {
		Product product1 = repo.findById(id).orElse(null);
		Map<String, Object> map = new HashMap<>();
		if (product1 == null) {
			map.put("success", 0);
			map.put("message", "no product found");
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
		}
		product1.setName(product.getName());
		product1.setPrice(product.getPrice());
		product1.setQuantity(product.getQuantity());
		product1.setCategory(product.getCategory());
		repo.save(product1);
		map.put("success", 1);
		map.put("message", "successfully updated");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> deleteProduct(int id) {
		Product product = repo.findById(id).orElse(null);
		Map<String, Object> map = new HashMap<>();
		if (product != null) {
			map.put("success", 1);
			map.put("message", "deleted successfully");
			repo.delete(product);
			return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NO_CONTENT);
		}
		map.put("success", 0);
		map.put("message", "cannot find product");
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.NOT_FOUND);
	}

}
