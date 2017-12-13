package edu.karazin.shop.dto;

import java.util.List;

public class DiscountDto {
    //Для всех пользователей listAll
    private List<UserDto> users;

    private List<ProductDto> products;

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }
}

