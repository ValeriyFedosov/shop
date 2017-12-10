package edu.karazin.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Discount {

    @Id
    @GeneratedValue
    private Long discount_id;

    private Double discountValue;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product_discount")
    private List<Product> products;

    public Discount() {
    }

    public Discount(Double discountValue, List<Product> products) {
        this.discountValue = discountValue;
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getDiscount_id() {
        return discount_id;
    }

    public void setDiscount_id(Long discount_id) {
        this.discount_id = discount_id;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

}
