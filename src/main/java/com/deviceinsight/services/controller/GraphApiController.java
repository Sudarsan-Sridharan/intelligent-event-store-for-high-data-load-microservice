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
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;


@Controller
public class GraphApiController {

    protected static ArangoDB arangoDB;

    protected static final String DB_NAME = "event_store";
    protected static final String COLLECTION_NAME = "eventTransition";

    protected static ArangoDatabase db;
    protected static ArangoCollection collection;

    private HazelcastInstance client;

    @RequestMapping(value =  "/graph",  method = RequestMethod.GET)
    public ResponseEntity<Object> greeting() {
        arangoDB = new ArangoDB.Builder().user("root").password("Lli3Zh2CbwQtvR4y").build();
        try {
            //arangoDB.db(DB_NAME).drop();
        } catch (final ArangoDBException e) {
        }
        //arangoDB.createDatabase(DB_NAME);
        db = arangoDB.db(DB_NAME);
        //db.createCollection(COLLECTION_NAME);
        CollectionsReadOptions collectionsReadOptions = new CollectionsReadOptions();

        collection = db.collection(COLLECTION_NAME);
        FriendsGraphDoc friendsGraphDoc = collection.getDocument("testDoc", FriendsGraphDoc.class);






        return new ResponseEntity<>(friendsGraphDoc, HttpStatus.OK);

    }

}
