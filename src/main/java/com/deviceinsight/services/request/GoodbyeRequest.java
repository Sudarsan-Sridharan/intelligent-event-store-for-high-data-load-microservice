package com.deviceinsight.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = GoodbyeRequest.class)
public class GoodbyeRequest extends AbstractRequest {

    String fullname;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
