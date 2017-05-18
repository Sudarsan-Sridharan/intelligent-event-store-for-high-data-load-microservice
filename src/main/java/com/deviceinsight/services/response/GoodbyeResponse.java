package com.deviceinsight.services.response;

public class GoodbyeResponse {

    String message;

    public GoodbyeResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String name) {
        this.message = "goodbye " + name;
    }
}
