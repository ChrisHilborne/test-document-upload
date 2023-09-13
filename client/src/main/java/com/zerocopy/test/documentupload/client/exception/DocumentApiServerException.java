package com.zerocopy.test.documentupload.client.exception;

import com.zerocopy.test.api.shared.dto.ErrorDto;
import lombok.Getter;

@Getter
public class DocumentApiServerException extends RuntimeException {
    private final ErrorDto errorDto;
    public DocumentApiServerException(ErrorDto errorDto) {
        super(errorDto.exceptionMessage());
        this.errorDto = errorDto;
    }


}

