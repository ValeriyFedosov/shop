package edu.karazin.shop.repository;

import edu.karazin.shop.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {


}