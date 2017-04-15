package com.deviceinsight.services.model;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "servicequeues")
public class Servicequeue {

    @Id
    private int id;

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private TopicItem servicesession;
    private String accept_ticket;

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    private Long created;

    private int action;
    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private com.deviceinsight.services.model.Table table;

    public String getAccept_ticket() {
        return accept_ticket;
    }

    public void setAccept_ticket(String accept_ticket) {
        this.accept_ticket = accept_ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TopicItem getServicesession() {
        return servicesession;
    }

    public void setServicesession(TopicItem servicesession) {
        this.servicesession = servicesession;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public com.deviceinsight.services.model.Table getTable() {
        return table;
    }

    public void setTable(com.deviceinsight.services.model.Table table) {
        this.table = table;
    }
}
