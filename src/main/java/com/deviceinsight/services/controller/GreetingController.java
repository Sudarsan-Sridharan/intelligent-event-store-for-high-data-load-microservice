package com.deviceinsight.services.controller;

import com.deviceinsight.services.request.AbstractRequest;
import com.deviceinsight.services.request.GoodbyeRequest;
import com.deviceinsight.services.response.GoodbyeResponse;
import com.deviceinsight.services.response.HelloResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class GreetingController {

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public ResponseEntity<Object> greeting(@RequestBody AbstractRequest abstractRequest) {

        String type = abstractRequest.getClass().getTypeName();
        Object res;

        switch (type) {
            case "GoodbyeRequest":
                GoodbyeResponse goodbyeResponse = new GoodbyeResponse();
                goodbyeResponse.setMessage(((GoodbyeRequest) abstractRequest).getFullname());
                res = goodbyeResponse;
                break;
            default:
                HelloResponse helloResponse = new HelloResponse();
                helloResponse.setMessage(abstractRequest.getName());
                res = helloResponse;
                break;
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
