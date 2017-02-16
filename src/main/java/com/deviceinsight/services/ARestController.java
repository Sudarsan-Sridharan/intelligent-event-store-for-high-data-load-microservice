package com.deviceinsight.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;


@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class ARestController {

    @RequestMapping("/test")
    public String test() throws ExecutionException, InterruptedException {


        return "test";


    }

}