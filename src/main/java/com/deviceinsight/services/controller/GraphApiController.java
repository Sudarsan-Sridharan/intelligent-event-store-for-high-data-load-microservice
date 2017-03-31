package com.deviceinsight.services.controller;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.model.CollectionsReadOptions;
import com.deviceinsight.services.request.AbstractRequest;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import io.prometheus.client.*;
import io.prometheus.client.exporter.PushGateway;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Controller
public class GraphApiController {

    static final Counter requests = Counter.build()
            .name("hello_worlds_total")
            .help("Number of hello worlds served.").register();

    static final Histogram time = Histogram.build()
            .name("hello_worlds_total2")
            .help("Number of hello worlds served.2").register();

    protected static ArangoDB arangoDB;

    protected static final String DB_NAME = "event_store";
    protected static final String COLLECTION_NAME = "eventTransition";

    protected static ArangoDatabase db;
    protected static ArangoCollection collection;

    private HazelcastInstance client;

    @RequestMapping(value =  "/graph",  method = RequestMethod.GET)
    public ResponseEntity<Object> greeting() throws IOException {
        FriendsGraphDoc friendsGraphDoc=null;

time.startTimer();



     /*   CollectorRegistry registry = new CollectorRegistry();
        Gauge duration = Gauge.build()
                .name("my_batch_job_duration_seconds")
                .help("Duration of my batch job in seconds.")
                .register(registry);
        Gauge.Timer durationTimer = duration.startTimer();
       */
            // Your code here.
            requests.inc();



        arangoDB = new ArangoDB.Builder().user("root").password("NyUX4DzD2ncPTxsg").build();
        try {
            //arangoDB.db(DB_NAME).drop();
        } catch (final ArangoDBException e) {
        }
        //arangoDB.createDatabase(DB_NAME);
        db = arangoDB.db(DB_NAME);
        //db.createCollection(COLLECTION_NAME);
        CollectionsReadOptions collectionsReadOptions = new CollectionsReadOptions();

        collection = db.collection(COLLECTION_NAME);
//        friendsGraphDoc = collection.getDocument("testDoc", FriendsGraphDoc.class);


        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        time.collect();

        Summary.build().name("hello_worlds_total2").create();

        return new ResponseEntity<>(friendsGraphDoc, HttpStatus.OK);

    }

}
