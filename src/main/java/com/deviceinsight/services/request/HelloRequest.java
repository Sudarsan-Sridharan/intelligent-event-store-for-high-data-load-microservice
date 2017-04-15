package com.deviceinsight.services.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = HelloRequest.class)
public class HelloRequest extends AbstractRequest {

}
