package com.example.JavaMachineTest.repository;



import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.example.JavaMachineTest.projection.ProdWithCat;
import com.example.JavaMachineTest.model.Product;
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

	
	Optional<ProdWithCat> 
	findProjectedByprodId(Integer id);
	
}
