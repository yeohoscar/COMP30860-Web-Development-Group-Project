package com.yysw.order;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedModel {
    private Long model_id;

    private Double price;

    public Long getModel_id() {
        return model_id;
    }

    public void setModel_id(Long model_id) {
        this.model_id = model_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}