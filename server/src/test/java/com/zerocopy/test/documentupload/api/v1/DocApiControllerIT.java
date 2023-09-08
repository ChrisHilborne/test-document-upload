package com.zerocopy.test.documentupload.api.v1;

import com.zerocopy.test.documentupload.persistance.entity.DocumentEntity;
import com.zerocopy.test.documentupload.persistance.repository.DocumentRepository;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
class DocApiControllerIT {

    @LocalServerPort
    private int port;
    @Autowired
    private DocumentRepository repository;
    private static final String BASE_PATH = "/v1/doc/";

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void uploadReturns201WhenDocumentIsValidPdf() throws IOException {
        // given
        File toUpload = new File(getClass().getClassLoader().getResource("valid_test.pdf").getFile());

        // then
        RestAssured.given()
                .multiPart("file", toUpload, "multipart/form-data")
                .when()
                .post(BASE_PATH + "upload")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void uploadDocumentSavesDocumentInDbWhenDocumentIsValidPdf() throws IOException {
        // given
        File toUpload = new File(getClass().getClassLoader().getResource("valid_test.pdf").getFile());

        // when
        RestAssured.given()
                .multiPart("file", toUpload, "multipart/form-data")
                .when()
                .post(BASE_PATH + "upload");

        // then
        DocumentEntity saved = repository.findAll().get(0);
        assertEquals("valid_test.pdf", saved.getName());
        assertEquals(Integer.valueOf(1), saved.getPages());

    }

    @Test
    void uploadDocumentShouldReturn400WithErrorMessageWhenDocumentIsNotPdf() throws IOException {
        // given
        File toUpload = new File(getClass().getClassLoader().getResource("invalid_test.doc").getFile());

        // when
        RestAssured.given()
                .multiPart("file", toUpload, "multipart/form-data")
                .when()
                .post(BASE_PATH + "upload")
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("timestamp", Matchers.notNullValue())
                .and()
                .body("code", Matchers.equalTo(400))
                .and()
                .body("message", Matchers.containsString("wrong file format"));

    }
}