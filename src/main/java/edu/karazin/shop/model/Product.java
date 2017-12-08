package edu.karazin.shop.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Product {

	@Id
    @GeneratedValue
	private Long id;

    @Column(unique = true, nullable = false)
	private String title;

    @Column(unique = true, nullable = false)
	private String description;
    private String imageName;
	private double cost;
	private int balance;

	public Product() {
	}

	public Product(Long id, String title, String description, double cost, int balance) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.cost = cost;
		this.balance = balance;
	}

	public Product(Long id, String title, String description, String imageName, double cost, int balance) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.imageName = imageName;
		this.cost = cost;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Product setIt(Long id) {
		this.id = id;
		return this;
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

	@Column(unique = true, nullable = false)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!title.equals(product.title)) return false;
        return description.equals(product.description);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", balance=" + balance +
                '}';
    }


}
