package edu.karazin.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class
Product {

	@Id
    @GeneratedValue
	private Long id;

    @Column(unique = true, nullable = false)
	private String title;

    @Column(unique = true, nullable = false)
	private String description;
    private byte[] image;
	private String imageMimeType;
	private long cost;
	private int balance;

	public Product() {
	}

    public Product(Long id, String title, String description, long cost, int balance) {
        this(id, title, description, null, null, cost, balance);
    }

    public Product(Long id, String title, String description, byte[] image, String imageMimeType, long cost, int balance) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.image = image;
		this.imageMimeType = imageMimeType;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getImageMimeType() {
		return imageMimeType;
	}

	public void setImageMimeType(String imageMimeType) {
		this.imageMimeType = imageMimeType;
	}

	public long getCost() {
		return cost;
	}

	public void setCost(long cost) {
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
                ", image=" + Arrays.toString(image) +
                ", imageMimeType='" + imageMimeType + '\'' +
                ", cost=" + cost +
                ", balance=" + balance +
                '}';
    }


}
