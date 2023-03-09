package com.yysw.user.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yysw.general.AIModel;
import com.yysw.order.Order;
import com.yysw.user.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Customer")
public class Customer extends User implements Serializable {
    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<Order>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<AIModel> cart;

    public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }
}
