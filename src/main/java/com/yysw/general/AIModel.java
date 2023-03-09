package com.yysw.general;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class AIModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long model_id;

    private String model_name;

    private double trainedPrice;

    private double untrainedPrice;

    private String description;

    private String photoURL;

    private boolean available;

    public Long getModel_id() { return model_id; }

    public void setModel_id(Long model_id) { this.model_id = model_id; }

    public String getModel_name() { return model_name; }

    public void setModel_name(String model_name) {
        this.model_name = model_name;
    }

    public double getTrainedPrice() { return trainedPrice; }

    public void setTrainedPrice(double trainedPrice) { this.trainedPrice = trainedPrice; }

    public double getUntrainedPrice() { return untrainedPrice; }

    public void setUntrainedPrice(double untrainedPrice) { this.untrainedPrice = untrainedPrice; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }
}
