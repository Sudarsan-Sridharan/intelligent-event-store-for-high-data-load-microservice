package com.deviceinsight.services.response;

public class HelloResponse {

    public HelloResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String name) {
        this.message = "hello " + name;
    }

    String message;
}
