package com.yysw.user.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.yysw.order.Order;
import com.yysw.user.User;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Customer extends User implements Serializable {
    @OneToMany
    private List<Order> order = new ArrayList<Order>();
}
