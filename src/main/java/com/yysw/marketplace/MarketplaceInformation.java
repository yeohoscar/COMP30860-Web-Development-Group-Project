package com.yysw.marketplace;

import com.yysw.general.AiModel;

import java.io.Serializable;

public class MarketplaceInformation implements Serializable {
    private AiModel item;
    private int quantity;
    private double price;

    public AiModel getItem() {
        return item;
    }

    public void setItem(AiModel item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
