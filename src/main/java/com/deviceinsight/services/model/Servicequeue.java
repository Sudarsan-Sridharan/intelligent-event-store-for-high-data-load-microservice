package com.deviceinsight.services.model;


import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.persistence.Table;

/**
 * Created by ebners on 18/01/16.
 */
@Entity
@Table(name = "servicequeues")
public class Servicequeue {


    @Id
    private int id;

    @JsonIgnore
    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Product servicesession;
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

   /* public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    private int table_id;
*/

    public void setAccept_ticket(String accept_ticket) {
        this.accept_ticket = accept_ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getServicesession() {
        return servicesession;
    }

    public void setServicesession(Product servicesession) {
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
