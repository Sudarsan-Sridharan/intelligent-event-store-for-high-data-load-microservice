package com.deviceinsight.services.ecommerce.api;

import com.deviceinsight.services.config.JestClientService;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.searchbox.client.JestClient;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.Update;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/restaurant")
public class RestaurantRestController {
    private static final Logger LOG = LogManager.getLogger(RestaurantRestController.class);

    CamelContext context = new DefaultCamelContext();

    /*@Autowired
    CamelContext camelContext;
*/
    @Autowired
    @Qualifier("KafkaRouteProducer")
    RouteBuilder kafkaRouteProducer;

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    private JestClientService jestClientService;


    @Autowired
    ConsumerTemplate consumerTemplate;

    /*@Autowired
    @Qualifier("KafkaRouteConsumer")
    RouteBuilder kafkaRouteConsumer;
*/




/*
   @Autowired
    KafkaComponent kafkaComponent;

    @Autowired
    @Qualifier("KafkaRouteProducer")
    RouteBuilder kafkaRouteProducer;

    @Autowired
    @Qualifier("KafkaRouteConsumer")
    RouteBuilder kafkaRouteConsumer;

    @EndpointInject(uri = "direct:kafkaRoute")
    ProducerTemplate kafkaProducer;
*/


    @Autowired
    private RestaurantDao<Restaurant> restaurantDao;
//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restaurant> restaurantDao;//    private RestaurantDao<Restau

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public List<Restaurant> get() throws Exception {

        ProducerTemplate template = context.createProducerTemplate();



        JestClient jestHttpClient = (JestClient) jestClientService.getClient();


        String query = "{\n" +
                "    \"query\": {\n" +
                "        \"exists\" : { \"field\" : \"talk\" }\n" +
                "    }\n" +
                "}";
        Search.Builder searchBuilder = new Search.Builder(query).addIndex("nextcloud");
        SearchResult result = jestHttpClient.execute(searchBuilder.build());






        new Update.Builder(XContentFactory.jsonBuilder().startObject().field("title", true).startObject("doc").field("foo", "bar").endObject().endObject().string());





        Product people = new Product();
        people.setTitle("test product");

        String json = "[{\"fname\": \"Bob\",\"lname\": \"Smith\"},{\"fname\": \"Mike\",\"lname\": \"Johnson\"}]";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        people.setTitle("aaaa");

        Index index = new Index.Builder(people).index("nextcloud").type("aType").build();
        jestHttpClient.execute(index);



        kafkaRouteProducer.getContext().start();
        // kafkaRouteProducer.from("direct:start", "test");


//        kafkaRouteProducer.getContext().getEndpoint("direct:start").createProducer().process(new DefaultExchange(kafkaRouteProducer.getContext()));


        //   kafkaRouteProducer.getContext().getEndpoint("direct:a").createProducer().process(new DefaultExchange(kafkaRouteProducer.getContext()));

        producerTemplate.sendBody("direct:start", "s");

        LOG.debug("This will be printed on debug");
        LOG.info("This will be printed on info");
        LOG.warn("This will be printed on warn");
        LOG.error("This will be printed on error");
        LOG.fatal("This will be printed on fatal");

        // LOG.info("Appending string: {}.", "Hello, World");
        return restaurantDao.list();
    }
}
