package com.zerocopy.test.documentupload.service;

import com.zerocopy.test.documentupload.api.v1.dto.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PdfService {

    public void savePdf(MultipartFile file);

    List<DocumentDto> getAll();
}
