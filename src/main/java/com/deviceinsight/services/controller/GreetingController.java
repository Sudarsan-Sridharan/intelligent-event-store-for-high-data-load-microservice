package com.deviceinsight.services.controller;

import com.deviceinsight.services.request.AbstractRequest;
import com.deviceinsight.services.request.GoodbyeRequest;
import com.deviceinsight.services.response.GoodbyeResponse;
import com.deviceinsight.services.response.HelloResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")//value specifies the base url for our REST calls
public class GreetingController {

    //single endpoint accepting an abstract object hence allowing the user to send a json request
    // representing a java object that extends AbstractRequest
    @RequestMapping(value =  "/greeting",  method = RequestMethod.POST)
    public ResponseEntity<Object> greeting(@RequestBody AbstractRequest abstractRequest) {

        String type = abstractRequest.getClass().getTypeName() ;
        Object res;

        switch (type){
            case "GoodbyeRequest" :
                GoodbyeResponse goodbyeResponse  = new GoodbyeResponse();
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
