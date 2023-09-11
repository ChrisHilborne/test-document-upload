package com.zerocopy.test.documentupload.server.domain;

import com.zerocopy.test.documentupload.server.persistance.entity.DocumentEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentProcessor {

    public DocumentEntity processDocument(MultipartFile file);

}
