package com.yysw.cart;

import com.yysw.aimodels.AIModel;
import com.yysw.user.customer.Customer;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ShoppingCartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private AIModel item;

    private boolean trainedModelOrNot;

//    private boolean untrainedModel;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Customer customer;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }
    public AIModel getItem() { return item; }

    public void setItem(AIModel item) { this.item = item; }

    public boolean isTrainedModel() { return trainedModelOrNot; }

    public void setTrainedModel(boolean trainedModel) { this.trainedModelOrNot = trainedModel; }

//    public boolean isUntrainedModel() { return untrainedModel; }

//    public void setUntrainedModel(boolean untrainedModel) { this.untrainedModel = untrainedModel; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Customer getCustomer() { return customer; }

    @Override
    public String toString() {
        return "ShoppingCartItem{" +
                "id=" + id +
                ", item=" + item +
                ", trainedModel=" + trainedModelOrNot +
//                ", untrainedModel=" + untrainedModel +
                ", price=" + price +
                ", customer=" + customer +
                '}';
    }
}
