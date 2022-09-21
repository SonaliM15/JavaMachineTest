package com.example.JavaMachineTest.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.JavaMachineTest.model.Category;
@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Integer>{

	
	
	
}
