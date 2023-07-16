package com.example.restapipractice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapipractice.entities.Product;
import com.example.restapipractice.services.ProductService;

import jakarta.validation.Valid;


@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/newProduct")
	public ResponseEntity<Map<String, Object>> addNewProduct(@RequestBody @Valid Product product) {
		return productService.createProduct(product);
	}

	@GetMapping("/allProducts")
	public ResponseEntity<Map<String, Object>> findAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Map<String, Object>> findOneProduct(@PathVariable int id) {
		return productService.getOneProduct(id);
	}

	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable int id, @RequestBody Product product) throws Exception{
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}
}
