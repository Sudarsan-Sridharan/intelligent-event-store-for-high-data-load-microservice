package com.deviceinsight.services.model;

import com.deviceinsight.services.model.dao.BaseEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "topic_items")
public class TopicItem extends BaseEntity implements IProduct {

    private String title;
    private String first_name;
    private String imagePath;
    private float credit;
    private int finished;
    private String identifier;
    private String last_bidder;
    public TopicItem() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLast_bidder() {
        return last_bidder;
    }

    public void setLast_bidder(String last_bidder) {
        this.last_bidder = last_bidder;
    }
}
