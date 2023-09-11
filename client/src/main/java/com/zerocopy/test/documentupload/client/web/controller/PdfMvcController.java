package com.zerocopy.test.documentupload.client.web.controller;

import com.zerocopy.test.api.shared.dto.DocumentDto;
import com.zerocopy.test.api.shared.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.zerocopy.test.documentupload.client.web.PathConstants.*;
import static com.zerocopy.test.documentupload.client.web.TemplateConstants.FRAGMENTS_FILES_FILES_LIST;
import static com.zerocopy.test.documentupload.client.web.TemplateConstants.FRAGMENTS_FILES_UPLOAD_FORM;

@Controller
@RequestMapping(PDF_PATH)
@Slf4j
public class PdfMvcController {

    private final PdfService service;

    public PdfMvcController(PdfService service) {
        this.service = service;
    }

    @PostMapping(UPLOAD_PATH)
    public String uploadFile(@RequestParam("file") MultipartFile pdf) {
        log.debug("Received request to upload file:{}", pdf.getOriginalFilename());
        service.savePdf(pdf);
        return FRAGMENTS_FILES_UPLOAD_FORM;
    }

    @GetMapping(LIST_PATH)
    public String getFileList(Model model) {
        log.trace("Received request for file list");
        List<DocumentDto> files = service.getAll();
        model.addAttribute("files", files);
        return FRAGMENTS_FILES_FILES_LIST;
    }
}
