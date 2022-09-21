package com.example.JavaMachineTest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.JavaMachineTest.model.Category;
import com.example.JavaMachineTest.model.Product;
import com.example.JavaMachineTest.service.CategoryService;
import com.example.JavaMachineTest.service.ProductService;

@RestController
@RequestMapping("/api")
public class JmtController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;

	@PostMapping("/categories")
	public Category addCategory(@RequestBody Category category) {
		return this.categoryService.addCategory(category);
	}

	@GetMapping("/categories")

	public Page<Category> getCategory(Pageable pageable) {

		return this.categoryService.getAll(pageable);
	}

	
	@GetMapping("/categories/{id}")
	public ResponseEntity<?> getCatById(@PathVariable Integer id) {

		return new ResponseEntity<>(this.categoryService.getById(id), HttpStatus.OK);

	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody Category category) {

		return new ResponseEntity<>(this.categoryService.updateCategory(id, category), HttpStatus.OK);

	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable Integer id) {

		if (this.categoryService.checkId(id)) {
			this.categoryService.deleteCategory(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
		}
	}

	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product) {
		return this.productService.addProduct(product);
	}

	@GetMapping("/products")

	public Page<Product> getProduct(Pageable pageable) {
		return this.productService.getAll(pageable);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<?> getProdById(@PathVariable Integer id) {

		return new ResponseEntity<>(this.productService.getProdWithCatById(id), HttpStatus.OK);

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {

		return new ResponseEntity<>(this.productService.updateProduct(id, product), HttpStatus.OK);

	}

	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable Integer id) {

		if (this.productService.checkId(id)) {
			this.productService.deleteProduct(id);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
		}
	}

	@GetMapping("/categories/{id}/product")
	public ResponseEntity<?> getProd(@PathVariable Integer id) {

		return new ResponseEntity<>(this.categoryService.getProd(id), HttpStatus.OK);

	}

	@PostMapping("/categories/{id}/product")
	public ResponseEntity<?> addProdCat(@PathVariable Integer id, @RequestBody Product product) {
		return new ResponseEntity<>(this.categoryService.addProdCat(id, product), HttpStatus.OK);

	}

}
