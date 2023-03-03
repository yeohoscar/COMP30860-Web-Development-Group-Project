package com.yysw.customer;

import java.io.Serializable;
import com.yysw.order.Order;

import java.util.ArrayList;

public class Customer implements Serializable {
    private String username, passwd;
    private final ArrayList<Order> orderHistory = new ArrayList<>();


}
