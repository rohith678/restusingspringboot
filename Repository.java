package com.example.restapipractice.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restapipractice.entities.Product;

public interface Repository extends JpaRepository<Product, Integer> {

}
