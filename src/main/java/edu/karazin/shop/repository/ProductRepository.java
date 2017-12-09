package edu.karazin.shop.repository;

import edu.karazin.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{

    List<Product> findAll();

    Product getProductById(Long id);

    @Query(value = "select p from Product p where p.description like :searchText or p.title like :searchText")
    List<Product> findByText(@Param("searchText") String searchText);

    Product save(Product prod);

    void deleteById(Long id);

}

