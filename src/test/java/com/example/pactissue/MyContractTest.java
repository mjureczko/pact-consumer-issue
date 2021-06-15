package com.example.pactissue;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.example.pactissue.client.MyClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(PactConsumerTestExt.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PactTestFor(providerName = "my", port = "7771")
public class MyContractTest {

    private final String GIVEN_1 = "[1]";
    private final String ACTUAL_1 = "[11]";
    private final String GIVEN_2 = "[2]";
    private final String ACTUAL_2 = "[22]";
    @Autowired
    MyClient myClient;

    @Pact(consumer = "my")
    public RequestResponsePact createPactForPostMap1(PactDslWithProvider builder) {
        return builder
                .given("Map 1")
                .uponReceiving("request for mapping 1")
                .path("/v1/map")
                .body(GIVEN_1)
                .method("POST")
                .willRespondWith()
                .status(SC_OK)
                .body(ACTUAL_1)
                .matchHeader("Content-Type", "application/json")
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForPostMap1")
    public void shouldMap1(MockServer mockServer){
        //`when`
        String actual = myClient.map(GIVEN_1);

        //then
        assertAll(
                () -> assertThat(actual).isEqualTo(ACTUAL_1)
        );
    }

    @Pact(consumer = "my")
    public RequestResponsePact createPactForPostMap2(PactDslWithProvider builder) {
        return builder
                .given("Map 2")
                .uponReceiving("request for mapping 2")
                .path("/v1/map")
                .body(GIVEN_2)
                .method("POST")
                .willRespondWith()
                .status(SC_OK)
                .body(ACTUAL_2)
                .matchHeader("Content-Type", "application/json")
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForPostMap2")
    public void shouldMap2(MockServer mockServer){
        //`when`
        String actual = myClient.map(GIVEN_2);

        //then
        assertAll(
                () -> assertThat(actual).isEqualTo(ACTUAL_2)
        );
    }
}
