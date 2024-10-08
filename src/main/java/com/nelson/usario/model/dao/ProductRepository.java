package com.nelson.usario.model.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.nelson.usario.model.entity.Product;



@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, CrudRepository<Product, Long> {
    
    boolean existsBySku(String sku);
    
    @SuppressWarnings("null")
	Page<Product> findAll(Pageable pageable);

}
