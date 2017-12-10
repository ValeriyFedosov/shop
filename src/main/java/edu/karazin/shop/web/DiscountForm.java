package edu.karazin.shop.web;

import java.util.Date;

public class DiscountForm {

    private Double discount;
    private Date from;
    private Date to;

    public DiscountForm() {
    }

    public DiscountForm(Double discount, Date from, Date to) {
        this.discount = discount;
        this.from = from;
        this.to = to;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
