package com.zerocopy.test.documentupload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PdfService {

    public void savePdf(MultipartFile file);

}
