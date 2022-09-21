package com.example.JavaMachineTest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.JavaMachineTest.model.Product;
import com.example.JavaMachineTest.projection.ProdWithCat;
import com.example.JavaMachineTest.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Page<Product> getAll(Pageable pageable) {
		return this.productRepository.findAll(pageable);
	}

	public Product getProdById(Integer id) {
		
		return this.productRepository.findById(id).orElseThrow(
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
				});
		
	}
	
	

	public boolean checkId(Integer id) {
		return this.productRepository.existsById(id);
	}

	public Product addProduct(Product product) {
		return this.productRepository.save(product);
	}

	public Product updateProduct(Integer id, Product product) {
		this.productRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
		});
		product.setProdId(id);
		return this.productRepository.save(product);
	}

	public void deleteProduct(Integer id) {

		this.productRepository.deleteById(id);

	}
	
	public ProdWithCat getProdWithCatById(Integer id) {
		return this.productRepository.findProjectedByprodId(id).orElseThrow(
				() -> {
					throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
				});
	}
	
}
