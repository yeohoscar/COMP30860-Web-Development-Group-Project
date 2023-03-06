package com.yysw.site;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class AiModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long model_id;

    private String name;

    private Double trained_price;

    private Double untrained_price;

    private boolean available;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel_id(Long id) {
        this.model_id = id;
    }

    public Long getModel_id() {
        return model_id;
    }

    public Double getTrained_price() {
        return trained_price;
    }

    public void setTrained_price(Double trained_price) {
        this.trained_price = trained_price;
    }

    public Double getUntrained_price() {
        return untrained_price;
    }

    public void setUntrained_price(Double untrained_price) {
        this.untrained_price = untrained_price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
