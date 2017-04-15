package com.deviceinsight.services.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {
    @Id
    private int id;
    private int capacity;

    private int workingarea;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkingarea() {
        return workingarea;
    }

    public void setWorkingarea(int workingarea) {
        this.workingarea = workingarea;
    }

}
