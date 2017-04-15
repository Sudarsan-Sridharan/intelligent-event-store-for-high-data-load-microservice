package com.deviceinsight.services.model;

import com.deviceinsight.services.model.dao.BaseUUIDEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "products")
public class Product extends BaseUUIDEntity implements IProduct {

    private String title;
    private String name;
    private Float price;

    public Product() {
        super();
    }

    public Product(String title, String name, Float price) {
        this.title = title;
        this.name = name;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
