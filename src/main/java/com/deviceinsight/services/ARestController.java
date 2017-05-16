package com.deviceinsight.services;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;


//@SpringBootApplication
@RestController
public class ARestController {

    private HazelcastInstance client;


    protected static ArangoDB arangoDB;

    protected static final String DB_NAME = "eventstore";
    protected static final String COLLECTION_NAME = "json_example_collection";


    protected static ArangoDatabase db;
    protected static ArangoCollection collection;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @PostConstruct
    public void post() {
/*        Config config = new Config();
        config.getGroupConfig().setName("name");
        config.getGroupConfig().setPassword("pwd");
        config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        config.getNetworkConfig().getJoin().getTcpIpConfig().addMember("172.17.0.3:5701");
        Hazelcast.newHazelcastInstance(config);*/
        ////////////// client = (HazelcastInstance) new GenericXmlApplicationContext("applicationContext.xml").getBean("client");
    }


    @Transactional
    @RequestMapping(value = "/hwl", method = RequestMethod.GET)
    public String getEsssvents() throws ExecutionException, InterruptedException {

        Session sess = sessionFactory.getCurrentSession();

        SessionFactory sessionFactory = hibernateTemplate.getSessionFactory();

        sessionFactory.getCurrentSession().createQuery("FROM Client").list();

        return "db works";
    }

    @RequestMapping(value = "/events/{NODE_ID}", method = RequestMethod.GET)
    public Set<String> getEvents(@PathVariable("NODE_ID") Long nodeId) throws ExecutionException, InterruptedException {
     /*   arangoDB = new ArangoDB.Builder().user("root").password("Ovjv3FrdKvf3CVfj").build();
        try {
            arangoDB.db(DB_NAME).drop();
        } catch (final ArangoDBException e) {
        }
        arangoDB.createDatabase(DB_NAME);
        db = arangoDB.db(DB_NAME);
        db.createCollection(COLLECTION_NAME);
        collection = db.collection(COLLECTION_NAME);
*/
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
        if (panamaEventDto.getOnCome()) {
            if (openedEvents == null) {
                openedEvents = new HashSet<>();
                map.put(panamaEventDto.getNodeId(), openedEvents);
            }
            openedEvents.add(panamaEventDto.getEventKey());
            map.put(panamaEventDto.getNodeId(), openedEvents);
            IMap<Long, Set<String>> resultMap = client.getMap("openedEventsNodeIdMapping");
            Set<String> openEventKeys = resultMap.get(panamaEventDto.getNodeId());
        } else {
            if (openedEvents != null) {
                openedEvents.remove(panamaEventDto.getEventKey());
                map.put(panamaEventDto.getNodeId(), openedEvents);
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