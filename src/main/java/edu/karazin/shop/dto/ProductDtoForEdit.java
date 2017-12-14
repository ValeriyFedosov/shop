package edu.karazin.shop.dto;

public class ProductDtoForEdit {

    private Long id;
    private String title;
    private String description;
    private String cost;
    private String balance;
    private String imageName;

    public ProductDtoForEdit() {
    }

    public ProductDtoForEdit(Long id, String title, String description, String cost, String balance, String imageName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.balance = balance;
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
