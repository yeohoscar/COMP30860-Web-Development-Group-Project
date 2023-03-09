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
    private Long id;

    private String name;

    private double trainedPrice;

    private double untrainedPrice;

    private String description;

    private String photoURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTrainedPrice() { return trainedPrice; }

    public void setTrainedPrice(double trainedPrice) { this.trainedPrice = trainedPrice; }

    public double getUntrainedPrice() { return untrainedPrice; }

    public void setUntrainedPrice(double untrainedPrice) { this.untrainedPrice = untrainedPrice; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getPhotoURL() { return photoURL; }

    public void setPhotoURL(String photoURL) { this.photoURL = photoURL; }
}
