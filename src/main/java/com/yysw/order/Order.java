package com.yysw.order;

import com.yysw.user.customer.Customer;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Customer customer;

    @ElementCollection
    private List<OrderedModel> orderedModels = new ArrayList<OrderedModel>();

    private State state;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;


    public Long getId() {
        return id;
    }

    public void setId(Long order_id) {
        this.id = order_id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderedModel> getOrderedModels() {
        return orderedModels;
    }

    public void setOrderedModels(List<OrderedModel> orderedModels) {
        this.orderedModels = orderedModels;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int quantityOfOrder() {
        return this.getOrderedModels().size();
    }
    public Double totalPriceOfOrderedModels() {
        Double totalPrice=0.0;
        for(OrderedModel a: this.getOrderedModels())
        {
            totalPrice += a.getPrice();
        }
        return totalPrice;
    }
}