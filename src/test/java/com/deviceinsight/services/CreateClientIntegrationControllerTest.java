package com.deviceinsight.services;


import com.deviceinsight.services.ecommerce.api.Restaurant2RestController;
import com.deviceinsight.services.ecommerce.api.RestaurantRestController;
import com.deviceinsight.services.model.Restaurant;
import com.deviceinsight.services.model.dao.RestaurantDao;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import sun.jvm.hotspot.HelloWorld;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

//@RunWith(SpringRunner.class)


@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Restaurant2RestController.class)
public class CreateClientIntegrationControllerTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup () {
        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        this.mockMvc = builder.build();
    }

    @org.junit.Test
    public void testUserController () throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/api/restaurant2");
/*                        .header("testHeader",
                                "headerValue")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserInJson("joe",
                                "joe@example.com"));*/
        this.mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String createUserInJson (String name, String email) {
        return "{ \"name\": \"" + name + "\", " +
                "\"emailAddress\":\"" + email + "\"}";
    }

}
