package com.deviceinsight.services.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public class KafkaOutputBean implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void printKafkaBody(String body) {
        try {
            Notification message = new ObjectMapper().readValue(body, Notification.class);
            System.out.println("KafkaBody result >>>>> " + message.toString());
        } catch (JsonParseException e) {
            System.out.println(body + " cannot be marshalled to a Notification.class");
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}