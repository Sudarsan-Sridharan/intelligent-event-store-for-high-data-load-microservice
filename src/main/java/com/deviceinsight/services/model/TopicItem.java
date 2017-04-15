package com.deviceinsight.services.model;

import com.deviceinsight.services.model.dao.BaseEntity;

import javax.persistence.Entity;

@Entity
@javax.persistence.Table(name = "topic_items")
public class TopicItem extends BaseEntity implements IProduct {

    public TopicItem() {
        super();
    }

    public void getAdminDtp() {

    }

    private String name;
    private int isActive;
    private String title;
    private String first_name;
    private String last_name;
    private int employee_id;
    private int isUpdated;
    private String imagePath;
    private float credit;
    private String item;
    private int last_bid;
    private int role;
    private int finished;
    private String identifier;
    private String last_bidder;
    private float base_price;
    private String mode;
    private int real_bids;
    private float ends_on_price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(int isUpdated) {
        this.isUpdated = isUpdated;
    }

    public float getEnds_on_price() {
        return ends_on_price;
    }

    public void setEnds_on_price(float ends_on_price) {
        this.ends_on_price = ends_on_price;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getLast_bid() {
        return last_bid;
    }

    public void setLast_bid(int last_bid) {
        this.last_bid = last_bid;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public float getBase_price() {
        return base_price;
    }

    public void setBase_price(float base_price) {
        this.base_price = base_price;
    }

    public int getReal_bids() {
        return real_bids;
    }

    public void setReal_bids(int real_bids) {
        this.real_bids = real_bids;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
