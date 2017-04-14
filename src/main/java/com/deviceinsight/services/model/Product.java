package com.deviceinsight.services.model;

import com.deviceinsight.services.model.dao.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity implements IProduct {

    public Product() {
        super();
    }

    private String name;
    private float price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    private String title;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
