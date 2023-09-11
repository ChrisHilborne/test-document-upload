package com.zerocopy.test.documentupload.server.api.v1;

import com.zerocopy.test.api.shared.dto.ErrorDto;
import com.zerocopy.test.documentupload.server.exception.FileFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    private static final Integer FILE_FORMAT_EXP_CODE = 400;
    private static final Integer GENERIC_EXP_CODE = 500;

    @ExceptionHandler(FileFormatException.class)
    public ResponseEntity<ErrorDto> handleFileFormatException(FileFormatException exception) {
       log.warn("FileFormatException thrown:{}", exception);
        return ResponseEntity
                .status(FILE_FORMAT_EXP_CODE)
                .body(new ErrorDto(LocalDateTime.now(), exception.getMessage(), FILE_FORMAT_EXP_CODE));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception exception) {
        log.warn("Exception thrown: {}", exception);
        return ResponseEntity
                .status(GENERIC_EXP_CODE)
                .body(new ErrorDto(LocalDateTime.now(), exception.getMessage(), GENERIC_EXP_CODE));
    }
}
