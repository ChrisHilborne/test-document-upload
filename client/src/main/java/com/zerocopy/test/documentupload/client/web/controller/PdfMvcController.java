package com.zerocopy.test.documentupload.client.web.controller;

import com.zerocopy.test.api.shared.dto.DocumentDto;
import com.zerocopy.test.api.shared.service.PdfService;
import com.zerocopy.test.documentupload.client.exception.DocumentApiServerException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.zerocopy.test.documentupload.client.web.PathConstants.*;
import static com.zerocopy.test.documentupload.client.web.TemplateConstants.*;

@Controller
@RequestMapping(PDF_PATH)
@Slf4j
public class PdfMvcController {

    private final PdfService service;

    public PdfMvcController(PdfService service) {
        this.service = service;
    }

    @PostMapping(UPLOAD_PATH)
    public String uploadFile(@RequestParam("file") MultipartFile pdf, HttpServletResponse response, Model model) {
        log.debug("Received request to upload file:{}", pdf.getOriginalFilename());
        try {
            service.savePdf(pdf);
            response.addHeader("HX-Trigger", "done");
            model.addAttribute("success", true);
        } catch (Exception e) {
            log.error("Error uploading document", e);
            loadErrorInfo(model, e);
        }
        return FRAGMENTS_FILES_UPLOAD_FORM;
    }

    @GetMapping(LIST_PATH)
    public String getFileList(Model model) {
        log.trace("Received request for file list");
        try {
            List<DocumentDto> files = service.getAll();
            model.addAttribute("files", files);
        } catch (Exception e) {
            log.error("Error fetching document list", e);
            loadErrorInfo(model, e);
        }
        return FRAGMENTS_FILES_FILES_LIST;
    }

    private void loadErrorInfo(Model model, Exception e) {
        model.addAttribute("error", true);
        if (e instanceof DocumentApiServerException documentApiException) {
            model.addAttribute("errorMessage", documentApiException.getErrorDto().exceptionMessage());
        }
    }
}
