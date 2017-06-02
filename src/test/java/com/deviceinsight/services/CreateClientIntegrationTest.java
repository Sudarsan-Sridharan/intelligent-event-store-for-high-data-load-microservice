package com.deviceinsight.services;


import com.deviceinsight.services.model.Client;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import com.deviceinsight.services.model.dao.RestaurantDaoImpl;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ObjectToStringHttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class) // SpringRunner
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CreateClientIntegrationTest {




@Mock
    private RestaurantDao restaurantDao ;

    @Autowired
    private TestRestTemplate restTemplate;



    @org.junit.Test
    public void createClient() {

        Restaurant mockedEntry = new Restaurant();
        mockedEntry.setTitle("aha");

        List mockedList = new LinkedList();
        mockedList.add(mockedEntry);



        Restaurant mockedEntry1 = new Restaurant();
        mockedEntry1.setTitle("aha");

        List mockedList1 = new LinkedList();
        mockedList1.add(mockedEntry1);

        Mockito.when(restaurantDao.list()).thenReturn(mockedList1);

restaurantDao.list();

        assertEquals(((Restaurant) restaurantDao.list().get(0)).getTitle(), ((Restaurant) mockedList.get(0)).getTitle());


        /*ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/api/restaurant", String.class);
        assertEquals(VALID_EXPECTED, responseEntity.getBody());
   */ }


}
