package com.zerocopy.test.documentupload.api.v1;

import com.zerocopy.test.documentupload.api.v1.dto.DocumentDto;
import com.zerocopy.test.documentupload.service.PdfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-08T22:23:18.985393663+02:00[Europe/Madrid]")
@RestController
@RequestMapping("v1/doc")
@Slf4j
public class DocApiController implements DocApi {

    private final NativeWebRequest request;
    private final PdfService service;

    @Autowired
    public DocApiController(NativeWebRequest request, PdfService service) {
        this.request = request;
        this.service = service;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity docUploadPost(MultipartFile file) {
        log.debug("Received request to upload file name:{}, format:{}, size:{}", file.getOriginalFilename(), file.getContentType(), file.getSize());
        service.savePdf(file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<List<DocumentDto>> docListGet() {
        log.trace("Received request for all documentos");
        return ResponseEntity.ok(service.getAll());
    }
}
