package com.deviceinsight.services.ecommerce.api;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/restaurant2")
public class Restaurant2RestController {
    private static final Logger LOG = LogManager.getLogger(Restaurant2RestController.class);


    @RequestMapping(method = RequestMethod.GET)
    public String get() {

        return "{\"name\":\"companyName\"}";




    }
}
