package com.yysw.user.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yysw.order.Order;
import com.yysw.user.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends User implements Serializable {
    @OneToMany
    private List<Order> orders = new ArrayList<Order>();

    public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }
}
