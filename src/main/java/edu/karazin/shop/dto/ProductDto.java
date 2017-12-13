package edu.karazin.shop.dto;

import edu.karazin.shop.model.User;

import java.util.List;

public class ProductDto {

    //TODO
    private Long id;
    private String title;
    private String description;
    private String imageName;
    private double cost;
    private int balance;
    //Dto под модель подвязывать не нужно. Для тех, на кого распространяется скидка
    private Integer discount;
    private List<UserDto> userList;

    public ProductDto() {
    }

    public List<UserDto> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDto> userList) {
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageName='" + imageName + '\'' +
                ", cost=" + cost +
                ", balance=" + balance +
                ", discount=" + discount +
                '}';
    }
}
