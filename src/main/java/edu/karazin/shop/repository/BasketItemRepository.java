package edu.karazin.shop.repository;

import edu.karazin.shop.model.BasketItem;
import edu.karazin.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {

    List<BasketItem> findAllBy(User user);

}
