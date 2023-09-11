package com.zerocopy.test.documentupload.server.domain;

import com.lowagie.text.pdf.PdfReader;
import com.zerocopy.test.documentupload.server.exception.FileReadException;
import com.zerocopy.test.documentupload.server.persistance.entity.DocumentEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
@Slf4j
public class PdfProcessor implements DocumentProcessor {

    @Override
    public DocumentEntity processDocument(MultipartFile file)  {
        log.debug("Processing Pdf: {}", file.getOriginalFilename());
        DocumentEntity entity = new DocumentEntity();
        entity.setName(file.getOriginalFilename());
        try {
            PdfReader reader = new PdfReader(file.getInputStream());
            log.trace("Num pages: {}", reader.getNumberOfPages());
            entity.setPages(reader.getNumberOfPages());
        } catch (IOException exception) {
            throw new FileReadException("Could not read file:" + file.getName(), exception);
        }
        return entity;
    }
}
