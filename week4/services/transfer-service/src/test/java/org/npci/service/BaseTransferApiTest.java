package org.npci.service;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

public class BaseTransferApiTest {

    protected static RequestSpecification transferRequestSpec;
    protected static ResponseSpecification transferResponseSpec;


    @BeforeAll
     static void setupSpecs() {
        System.out.println("Setting up specs...");
        transferRequestSpec = new RequestSpecBuilder()
                .setBasePath("/api/v1/transfer")
                .addHeader("Content-Type", "application/json")
                .log(LogDetail.ALL)
                .build();

        transferResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .log(LogDetail.ALL)
                .build();
    }

}