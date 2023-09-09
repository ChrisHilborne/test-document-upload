package com.zerocopy.test.documentupload.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class PdfServiceImplTest {

    PdfServiceImpl service = new PdfServiceImpl(null, null);

    @Test
    void isPdfReturnsTrueForPdf() throws IOException {
        // given
        File toTest = new File(getClass().getClassLoader().getResource("valid_test.pdf").getFile());
        MultipartFile multipartFile = new MockMultipartFile("toTest", toTest.getName(), "multipart/form-data", Files.readAllBytes(toTest.toPath()));

        // when
        boolean result = service.isPdf(multipartFile);

        // then
        assertTrue(result);
    }

    @Test
    void isPdfReturnsFalseForNonPdf() throws IOException {
        // given
        File toTest = new File(getClass().getClassLoader().getResource("invalid_test.doc").getFile());
        MultipartFile multipartFile = new MockMultipartFile("toTest", toTest.getName(), "multipart/form-data", Files.readAllBytes(toTest.toPath()));

        // when
        boolean result = service.isPdf(multipartFile);

        // then
        assertFalse(result);
    }
}