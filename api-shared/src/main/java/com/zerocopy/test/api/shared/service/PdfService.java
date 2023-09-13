package com.zerocopy.test.api.shared.service;

import com.zerocopy.test.api.shared.dto.DocumentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PdfService {

    public void savePdf(MultipartFile file);

    List<DocumentDto> getAll();
}
