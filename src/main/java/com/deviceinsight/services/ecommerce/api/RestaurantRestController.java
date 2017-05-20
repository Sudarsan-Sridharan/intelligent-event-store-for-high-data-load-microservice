package com.deviceinsight.services.ecommerce.api;

import com.deviceinsight.services.config.JestClientService;
import com.deviceinsight.services.model.Product;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.http.JestHttpClient;
import io.searchbox.core.*;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.PutMapping;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.mapper.DocumentMapper;
import org.elasticsearch.index.mapper.RootObjectMapper;
import org.elasticsearch.index.mapper.StringFieldMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

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






        new Update.Builder(jsonBuilder().startObject().field("title", true).startObject("doc").field("foo", "bar").endObject().endObject().string());





        Product people = new Product();
        people.setTitle("test product");

        String json = "[{\"fname\": \"Bob\",\"lname\": \"Smith\"},{\"fname\": \"Mike\",\"lname\": \"Johnson\"}]";
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        people.setTitle("aaaa");

        Index index = new Index.Builder(people).index("nextcloud").type("aType").build();
        jestHttpClient.execute(index);


/*

        PutMapping putMapping = new PutMapping.Builder("nextcloud",
                "aType",
                "{ \"document\" : { \"properties\" : { \"message\" : {\"type\" : \"string\", \"store\" : \"yes\"} } } }"
        ).build();

        JestResult resulta = jestHttpClient.execute(putMapping);
        assertTrue(resulta.getErrorMessage(), resulta.isSucceeded());
*/




        //////////////////// https://github.com/searchbox-io/Jest/blob/master/jest/README.md

        jestHttpClient.execute(new CreateIndex.Builder("deliverysystem").build());


///


      /*  String settings = "\"settings\" : {\n" +
                "        \"number_of_shards\" : 5,\n" +
                "        \"number_of_replicas\" : 1\n" +
                "    }\n";

        jestHttpClient.execute(new CreateIndex.Builder("articles").settings(Settings.builder().loadFromSource(settings).build().getAsMap()).build());
*/



//


        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards",5);
        settingsBuilder.put("number_of_replicas",1);

        jestHttpClient.execute(new CreateIndex.Builder("articles").settings(settingsBuilder.build().getAsMap()).build());


//

        PutMapping putMapping = new PutMapping.Builder(
                "twitter2",
                "tweet",
                "{ \"tweet\" : { \"properties\" : { \"usera\" : {\"type\": \"string\",\n" +
                        "        \"analyzer\": \"simple\"} } } }"
        ).build();
        jestHttpClient.execute(putMapping);


//


    /* does not work    RootObjectMapper.Builder rootObjectMapperBuilder = new RootObjectMapper.Builder("my_mapping_name").add(
                new StringFieldMapper.Builder("message").store(true)
        );
        DocumentMapper documentMapper = new DocumentMapper.Builder("my_index", null, rootObjectMapperBuilder).build(null);
        String expectedMappingSource = documentMapper.mappingSource().toString();
        PutMapping putMapping2 = new PutMapping.Builder(
                "my_index",
                "my_type",
                expectedMappingSource
        ).build();
        jestHttpClient.execute(putMapping2);
*/



//


        //String source = "{\"user\":\"kimchy\"}";
        String source = jsonBuilder()
                .startObject()
                .field("user", "kimchy")

                .field("usera", "kimchy")

                .field("postDate", "date")
                .field("message", "trying out Elastic Search")
                .endObject().string();

/*
Map<String, String> source = new LinkedHashMap<String,String>();
source.put("user", "kimchy");
 */


/*
Article source = new Article();
source.setAuthor("John Ronald Reuel Tolkien");
source.setContent("The Lord of the Rings is an epic high fantasy novel");
 */


        Index index2 = new Index.Builder(source).index("twitter2").type("tweet").build();
        jestHttpClient.execute(index2);



        Index index3 = new Index.Builder(source).index("twitter2").type("tweet").id("1").build();
        jestHttpClient.execute(index3);

        /////////////// ------------------




/*
        String query = "{\n" +
                "    \"query\": {\n" +
                "        \"filtered\" : {\n" +
                "            \"query\" : {\n" +
                "                \"query_string\" : {\n" +
                "                    \"query\" : \"test\"\n" +
                "                }\n" +
                "            },\n" +
                "            \"filter\" : {\n" +
                "                \"term\" : { \"user\" : \"kimchy\" }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";

        Search search = new Search.Builder(query)
                // multiple index or types can be added.
                .addIndex("twitter2")
                .addType("tweet")
                .build();

        SearchResult result = client.execute(search);

*/
/*
String query = "{\n" +
            "    \"id\": \"myTemplateId\"," +
            "    \"params\": {\n" +
            "        \"query_string\" : \"search for this\"" +
            "    }\n" +
            "}";

Search search = new Search.TemplateBuilder(query)
                // multiple index or types can be added.
                .addIndex("twitter2")
                .addType("tweet")
                .build();

SearchResult result = client.execute(search);
 */




        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("user", "kimchy"));

        Search search = new Search.Builder(searchSourceBuilder.toString())
                // multiple index or types can be added.
                .addIndex("twitter2")
                .addType("tweet")
                .build();

        SearchResult result9 = jestHttpClient.execute(search);




        /*
//
        SearchResult result8 = jestHttpClient.execute(search);
        List<SearchResult.Hit<Article, Void>> hits = result8.getHits(Article.class);
// or
        List<Article> articles = result8.getSourceAsObjectList(Article.class);
*/


        Get get = new Get.Builder("twitter2", "1").type("tweet").build();

        JestResult result8 = jestHttpClient.execute(get);




// update
        String script = "{\n" +
                "    \"script\" : \"ctx._source.tags += tag\",\n" +
                "    \"params\" : {\n" +
                "        \"tag\" : \"blue\"\n" +
                "    }\n" +
                "}";

        jestHttpClient.execute(new Update.Builder(script).index("twitter2").type("tweet").id("1").build());











        String source6 = jsonBuilder()
                .startObject()
                .field("user", "kimchyaaaa")
                .field("postDate", "date")
                .field("message", "trying out Elastic Search")
                .endObject().string();


        Index index63 = new Index.Builder(source6).index("twitter2").type("tweet").id("1").build();
        jestHttpClient.execute(index63);


        Get get2 = new Get.Builder("twitter2", "1").type("tweet").build();

        JestResult result82 = jestHttpClient.execute(get2);





        /* // DELETE
        client.execute(new Delete.Builder("1")
                .index("twitter2")
                .type("tweet")
                .build());
         */

        /* bulk 1
        Bulk bulk = new Bulk.Builder()
    .defaultIndex("twitter2")
    .defaultType("tweet")
    .addAction(new Index.Builder(article1).build())
    .addAction(new Index.Builder(article2).build())
    .addAction(new Delete.Builder("1").index("twitter2").type("tweet").build())
    .build();

client.execute(bulk);
         */

        /* bulk 2
        String article1 = "tweet1";
String article2 = "tweet2";

Bulk bulk = new Bulk.Builder()
                .defaultIndex("twitter2")
                .defaultType("tweet")
                .addAction(Arrays.asList(
                    new Index.Builder(article1).build(),
                    new Index.Builder(article2).build()))
                .build();

client.execute(bulk);
         */


/////////////////////////






        String query22 = "{\n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"usera\": {\n" +
                "        \"query\": \"kimche\",\n" +
                "        \"fuzziness\": 3,\n" +
                "        \"prefix_length\": 3\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search.Builder searchBuilder6 = new Search.Builder(query22).addIndex("twitter2");
        SearchResult result6 = jestHttpClient.execute(searchBuilder6.build());





        for(int i=1; i<=result6.getHits(ESExampleObject.class).size(); i++) {
            System.out.println("fuzzy search result (usera) "+i+" ====> "+((LinkedTreeMap<String, String>) result6.getHits(Object.class).get(i-1).source).get("usera"));
        }












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
