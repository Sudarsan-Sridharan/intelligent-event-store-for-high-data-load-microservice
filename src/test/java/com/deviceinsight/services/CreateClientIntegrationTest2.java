package com.deviceinsight.services;


import com.deviceinsight.services.model.Client;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateClientIntegrationTest2 {

    private static String VALID_EXPECTED = "[{\"uuid\":\"5aea4c5a-afd8-448b-a256-af3d93ca6828\",\"title\":\"1\",\"name\":null,\"price\":0.0},{\"uuid\":\"363ce096-30e8-48fe-9b9b-5e5d945fb6a5\",\"title\":\"2\",\"name\":null,\"price\":0.0},{\"uuid\":\"e176491a-5b15-412a-b26d-adc75a5c2985\",\"title\":\"3\",\"name\":null,\"price\":0.0},{\"uuid\":\"b77d8fe6-c61b-49a9-9edd-6c627fcec6c7\",\"title\":\"4\",\"name\":null,\"price\":0.0},{\"uuid\":\"fffd8fe6-c61b-49a9-9edd-6c627fcec6c7\",\"title\":\"5\",\"name\":null,\"price\":0.0}]";



    @Autowired
    private RestaurantDao restaurantDao;

    @Autowired
    private TestRestTemplate restTemplate;
    @org.junit.Test
    public void createClient() {


        Restaurant mockedEntry = new Restaurant();
        mockedEntry.setTitle("aha");

        List mockedList = new LinkedList();
        mockedList.add(mockedEntry);

//        Mockito.when(restaurantDao.list()).thenReturn(mockedList);



      //  assertEquals(restaurantDao.list(), mockedList);


        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/api/restaurant", String.class);
        assertEquals(VALID_EXPECTED, responseEntity.getBody());
    }


}
