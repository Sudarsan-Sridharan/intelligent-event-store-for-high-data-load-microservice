package com.deviceinsight.services.controller;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.model.CollectionsReadOptions;
import com.hazelcast.core.HazelcastInstance;
import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class GraphApiController {

    protected static final String DB_NAME = "event_store";
    protected static final String COLLECTION_NAME = "eventTransition";
    static final Counter requests = Counter.build()
            .name("hello_worlds_total")
            .help("Number of hello worlds served.").register();
    static final Histogram time = Histogram.build()
            .name("hello_worlds_total2")
            .help("Number of hello worlds served.2").register();
    protected static ArangoDB arangoDB;
    protected static ArangoDatabase db;
    protected static ArangoCollection collection;

    private HazelcastInstance client;

    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public ResponseEntity<Object> greeting() throws IOException {
        FriendsGraphDoc friendsGraphDoc = null;
        time.startTimer();
        requests.inc();
        arangoDB = new ArangoDB.Builder().user("root").password("NyUX4DzD2ncPTxsg").build();
        try {
        } catch (final ArangoDBException e) {
        }
        db = arangoDB.db(DB_NAME);
        CollectionsReadOptions collectionsReadOptions = new CollectionsReadOptions();
        collection = db.collection(COLLECTION_NAME);
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
