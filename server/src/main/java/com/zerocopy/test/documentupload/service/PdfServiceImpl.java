package com.zerocopy.test.documentupload.service;

import com.zerocopy.test.documentupload.persistance.entity.DocumentEntity;
import com.zerocopy.test.documentupload.domain.DocumentProcessor;
import com.zerocopy.test.documentupload.persistance.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
public class PdfServiceImpl implements PdfService {

    private final DocumentRepository repository;

    private final DocumentProcessor processor;

    public PdfServiceImpl(DocumentRepository repository, DocumentProcessor processor) {
        this.repository = repository;
        this.processor = processor;
    }

    @Override
    public void savePdf(MultipartFile pdf) {
        log.debug("Saving file: {}", pdf.getOriginalFilename());
        DocumentEntity toSave = processor.processDocument(pdf);
        DocumentEntity saved = repository.save(toSave);
        log.debug("File saved: {}", saved);
    }
}
