package com.zerocopy.test.documentupload.api.v1;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.io.File;
import java.io.IOException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class DocApiControllerIT {

    @LocalServerPort
    private int port;

    private static final String BASE_PATH = "/v1/doc/";

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void uploadReturns201WhenDocumentIsValidPdf() throws IOException {
        // given
        File toUpload = new File(getClass().getClassLoader().getResource("dummy.pdf").getFile());

        // then
        RestAssured.given()
                .multiPart("file", toUpload, "multipart/form-data")
                .when()
                .post( BASE_PATH + "upload")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void docListGet() {
    }

}