package com.zerocopy.test.documentupload.service;

import com.zerocopy.test.documentupload.api.v1.dto.DocumentDto;
import com.zerocopy.test.documentupload.exception.FileFormatException;
import com.zerocopy.test.documentupload.persistance.entity.DocumentEntity;
import com.zerocopy.test.documentupload.domain.DocumentProcessor;
import com.zerocopy.test.documentupload.persistance.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
        if (!isPdf(pdf)) {
            throw new FileFormatException("pdf");
        }
        DocumentEntity toSave = processor.processDocument(pdf);
        DocumentEntity saved = repository.save(toSave);
        log.debug("File saved: {}", saved);
    }

    @Override
    public List<DocumentDto> getAll() {
        return repository.findAll(Sort.by("name"))
                .stream()
                .map(doc -> new DocumentDto(doc.getName(), doc.getPages()))
                .toList();
    }

    protected static boolean isPdf(MultipartFile pdf) {
        String extension = StringUtils.getFilenameExtension(pdf.getOriginalFilename());
        return "pdf".equalsIgnoreCase(extension);
    }
}

