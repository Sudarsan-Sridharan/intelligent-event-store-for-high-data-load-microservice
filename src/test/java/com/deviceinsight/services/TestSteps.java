package com.deviceinsight.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

@SpringBootTest(classes=Application.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class TestSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<HashMap> response;

    @Given("call GET on (.*)")
    public void getOnUrl(String url) {
        this.response = this.restTemplate.getForEntity(url, HashMap.class,new HashMap<>());
    }

    @Then("^the client receives status code of (\\d+)$")
    public void theResponseStatusIs(int status) throws Throwable {
        assertEquals(status,response.getStatusCode().value());
    }

    @And("^the response body must contain (.*) with (.*)$")
    public void theResponseBodyMustContain(String field,String value) throws Throwable {
        assertTrue(response.getBody().containsKey(field));
        assertTrue(((Map) response.getBody().get(field)).containsKey(value));
    }

}
