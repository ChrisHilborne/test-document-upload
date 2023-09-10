package com.zerocopy.test.documentupload.domain;

import com.zerocopy.test.documentupload.persistance.entity.DocumentEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface DocumentProcessor {

    public DocumentEntity processDocument(MultipartFile file);

}
