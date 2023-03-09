package com.yysw.marketplace;

import com.yysw.general.AIModel;

import java.io.Serializable;

public class MarketplaceInformation implements Serializable {
    private AIModel item;
    private int quantity;
    private double price;

    public AIModel getItem() {
        return item;
    }

    public void setItem(AIModel item) {
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
