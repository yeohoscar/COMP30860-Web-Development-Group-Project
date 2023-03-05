package com.yysw.user.customer;

import java.io.Serializable;
import com.yysw.order.Order;

import java.util.ArrayList;

public class Customer extends User implements Serializable {
    private final ArrayList<Order> orderHistory = new ArrayList<>();
}
