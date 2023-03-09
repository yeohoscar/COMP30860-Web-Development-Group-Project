package com.yysw.marketplace;

import com.yysw.general.AIModel;

import java.io.Serializable;

public class MarketplaceInformation implements Serializable {
    private AIModel item;
    private int quantity;

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
}
