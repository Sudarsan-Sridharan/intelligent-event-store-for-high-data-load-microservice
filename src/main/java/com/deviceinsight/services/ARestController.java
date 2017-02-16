package com.deviceinsight.services;

import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
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


        JetInstance instance1 = Jet.newJetClient();



        System.out.println(instance1.getConfig().toString());

        return "test";


    }

}