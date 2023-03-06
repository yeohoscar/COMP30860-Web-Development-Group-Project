package com.yysw.order;

import com.yysw.site.AiModel;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;

    @ElementCollection
    @MapKeyColumn(name="model_id")
    @Column(name="price_at_order")
    @CollectionTable(name="order_history_model_price", joinColumns = @JoinColumn(name="order_id"))
    private Map<Integer, Double> orderedModels = new HashMap<>();

    private State state;

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Map<Integer, Double> getOrderedModels() {
        return orderedModels;
    }

    public void setOrderedModels(Map<Integer, Double> orderedModels) {
        this.orderedModels = orderedModels;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
