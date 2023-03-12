package com.yysw.user.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yysw.aimodels.AIModel;
import com.yysw.cart.ShoppingCartItem;
import com.yysw.order.Order;
import com.yysw.user.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends User implements Serializable {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<Order>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<ShoppingCartItem> cart;

    public Customer() {}

    public Customer(String username, String passwd) {
        super(username, passwd);
    }

    public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }

    public List<ShoppingCartItem> getCart() { return cart; }

    public void setCart(List<ShoppingCartItem> cart) { this.cart = cart; }
}
