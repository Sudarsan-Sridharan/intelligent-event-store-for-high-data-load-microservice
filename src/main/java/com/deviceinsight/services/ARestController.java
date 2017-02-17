package com.deviceinsight.services;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.jet.Jet;
import com.hazelcast.jet.JetInstance;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class ARestController {

    private HazelcastInstance client;

    @PostConstruct
    public void post() {
/*        Config config = new Config();
        config.getGroupConfig().setName("name");
        config.getGroupConfig().setPassword("pwd");
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("172.17.0.3:5701");
        Hazelcast.newHazelcastInstance(config);*/
        client=(HazelcastInstance) new GenericXmlApplicationContext("applicationContext.xml").getBean("client");
    }


    @RequestMapping(value = "/events/{NODE_ID}", method = RequestMethod.GET)
    public Set<String> getEvents(@PathVariable("NODE_ID") Long nodeId) throws ExecutionException, InterruptedException {


        IMap<Long, Set<String>> map = client.getMap("openedEventsNodeIdMapping");
        Set<String> openedEvents = map.get(nodeId);
        if (openedEvents == null) {
            return Collections.emptySet();
        } else {
            return openedEvents;
        }
    }



        @RequestMapping(value = "/createEvent", method = RequestMethod.POST)
    public String test(@RequestBody PanamaEventDto panamaEventDto) throws ExecutionException, InterruptedException {

        IMap<Long, Set<String>> map = client.getMap("openedEventsNodeIdMapping");
        Set<String> openedEvents = map.get(panamaEventDto.getNodeId());
        if(panamaEventDto.getOnCome()) {


//            System.out.println("#######  CLIENT BEGIN #######");


            if (openedEvents == null) {
                openedEvents = new HashSet<>();
                map.put(panamaEventDto.getNodeId(), openedEvents);
            }
            openedEvents.add(panamaEventDto.getEventKey());
            map.put(panamaEventDto.getNodeId(), openedEvents);
            IMap<Long, Set<String>> resultMap = client.getMap("openedEventsNodeIdMapping");
            Set<String> openEventKeys = resultMap.get(panamaEventDto.getNodeId());
            System.out.println(openEventKeys.toString());
           // System.out.println("#######  CLIENT END #######");


        } else {

            if(openedEvents!=null) {
                openedEvents.remove(panamaEventDto.getEventKey());
                map.put(panamaEventDto.getNodeId(), openedEvents);
                System.out.println(openedEvents.toString());
            }

        }


            return "test";
    }

    @RequestMapping("/docker")
    public String docker() throws ExecutionException, InterruptedException {


        return "test";


    }

    @RequestMapping("/test2")
    public String test2() throws ExecutionException, InterruptedException {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        HazelcastInstance client = (HazelcastInstance) context.getBean("client");

        IMap<String, String> map = client.getMap("map");
        System.out.println("City: " + map.get("city"));
        return "ok";
    }
}