package com.deviceinsight.services;


import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

assertThat("abaa").contains("b");


        /*ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("/api/restaurant", String.class);
        assertEquals(VALID_EXPECTED, responseEntity.getBody());
   */ }


}
