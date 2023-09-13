package com.zerocopy.test.documentupload.client.service;

import com.zerocopy.test.api.shared.dto.DocumentDto;
import com.zerocopy.test.api.shared.dto.ErrorDto;
import com.zerocopy.test.api.shared.service.PdfService;
import com.zerocopy.test.documentupload.client.config.ApiClientConfig;
import com.zerocopy.test.documentupload.client.exception.DocumentApiServerException;
import com.zerocopy.test.documentupload.client.exception.DocumentApiClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class PdfServiceApiClientImpl implements PdfService {

    private final ApiClientConfig apiClientConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    public PdfServiceApiClientImpl(ApiClientConfig apiClientConfig) {
        this.apiClientConfig = apiClientConfig;
    }

    @Override
    public void savePdf(MultipartFile pdf) {
        log.debug("Sending file to server:{}", pdf.getOriginalFilename());
        HttpEntity<MultiValueMap<String, Object>> requestEntity = prepareRequestEntity(pdf);
        ResponseEntity responseEntity;
        try {
            responseEntity = restTemplate.exchange(apiClientConfig.getApiUri() + apiClientConfig.getUploadPath(), HttpMethod.POST, requestEntity, Object.class);
        } catch (HttpServerErrorException serverException) {
            throw new DocumentApiServerException(Objects.requireNonNull(serverException.getResponseBodyAs(ErrorDto.class)));
        } catch (Exception exception) {
            throw new DocumentApiClientException("Error uploading file to server", exception);
        }
        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            ErrorDto errorDto = (ErrorDto) Objects.requireNonNull(responseEntity.getBody());
            log.warn("Error received from server:{}", errorDto);
            throw new DocumentApiServerException(errorDto);
        }
        log.debug("File {} successful uploaded to server", pdf.getOriginalFilename());
    }

    private HttpEntity<MultiValueMap<String, Object>> prepareRequestEntity(MultipartFile pdf) {
        log.debug("Preparing HttpEntity for upload file request for File: {}", pdf.getOriginalFilename());
        Resource pdfFileResource = pdf.getResource();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", pdfFileResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        log.debug("HttpEntity ready to be send:{}", requestEntity);
        return requestEntity;
    }

    @Override
    public List<DocumentDto> getAll() {
        log.trace("Fetching all uploaded documents");
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(apiClientConfig.getApiUri() + apiClientConfig.getListPath(), Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return (List<DocumentDto>) Objects.requireNonNull(response.getBody());
            } else {
                ErrorDto errorDto = (ErrorDto) response.getBody();
                log.warn("Error received from server:{}", errorDto);
                throw new DocumentApiServerException(errorDto);
            }
        } catch (Exception e) {
            throw new DocumentApiClientException("Something went wrong while fetching the document list", e);
        }
    }
}
