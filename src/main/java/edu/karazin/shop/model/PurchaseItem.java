package edu.karazin.shop.model;

import javax.persistence.*;

@Entity
public class PurchaseItem {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String imageName;
    private double cost;
    private String date;
    private long purchaseItemAmount;
    private double countOfCost;


    @ManyToOne
    @JoinColumn(name="uid", nullable=false)
    private User uid;

    public PurchaseItem() {
    }

    public double getCountOfCost() {
        return countOfCost;
    }

    public void setCountOfCost(double countOfCost) {
        this.countOfCost = countOfCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public long getPurchaseItemAmount() {
        return purchaseItemAmount;
    }

    public void setPurchaseItemAmount(long purchaseItemAmount) {
        this.purchaseItemAmount = purchaseItemAmount;
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

}



