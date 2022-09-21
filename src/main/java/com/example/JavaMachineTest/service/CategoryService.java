package com.example.JavaMachineTest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.JavaMachineTest.model.Category;
import com.example.JavaMachineTest.model.Product;
import com.example.JavaMachineTest.repository.CategoryRepository;
import com.example.JavaMachineTest.repository.ProductRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;

	public Page<Category> getAll(Pageable pageable) {

		return this.categoryRepository.findAll(pageable);
	}
	
	

	public Category getById(Integer id) {

		return this.categoryRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
		});

	}

	public boolean checkId(Integer id) {
		return this.categoryRepository.existsById(id);
	}

	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	public Category updateCategory(Integer id, Category category) {
		this.categoryRepository.findById(id).orElseThrow(() -> {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
		});
		category.setCatId(id);
		return this.categoryRepository.save(category);
	}

	public void deleteCategory(Integer id) {

		this.categoryRepository.deleteById(id);

	}

	public Product addProdCat(Integer id, Product product) {
		Category foundCat = this.getById(id);
		product.setProdCategory(foundCat);
		Product savedProd = this.productRepository.save(product);
		return savedProd;
	}

	public List<Product> getProd(Integer id) {
		Category foundCat = this.getById(id);
		return foundCat.getProducts();
	}

}
