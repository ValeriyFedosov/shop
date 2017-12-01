package edu.karazin.shop.model;

public class BasketItem {

    private Product product;
    private long countOfCost;
    private int countOfProducts;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getCountOfCost() {
        return countOfCost;
    }

    public void setCountOfCost(long countOfCost) {
        this.countOfCost = countOfCost;
    }

    public int getCountOfProducts() {
        return countOfProducts;
    }

    public void setCountOfProducts(int countOfProducts) {
        this.countOfProducts = countOfProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BasketItem that = (BasketItem) o;

        return product != null ? product.equals(that.product) : that.product == null;
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}
