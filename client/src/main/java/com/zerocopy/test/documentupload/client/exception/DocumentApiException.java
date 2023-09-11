package com.zerocopy.test.documentupload.client.exception;

import com.zerocopy.test.api.shared.dto.ErrorDto;
import lombok.Getter;

@Getter
public class DocumentApiException extends RuntimeException {
    private final ErrorDto errorDto;
    public DocumentApiException(ErrorDto errorDto) {
        super(errorDto.exceptionMessage());
        this.errorDto = errorDto;
    }
}
