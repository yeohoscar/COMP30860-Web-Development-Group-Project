package com.yysw.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedModel {
    private Long id;

    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}