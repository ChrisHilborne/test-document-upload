package com.zerocopy.test.documentupload.api.v1;

import com.zerocopy.test.documentupload.api.v1.dto.ErrorDto;
import com.zerocopy.test.documentupload.exception.FileFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ExceptionController {

    private static final Integer FILE_FORMAT_EXP_CODE = 400;

    @ExceptionHandler(FileFormatException.class)
    public ResponseEntity<ErrorDto> handleFileFormatException(FileFormatException exception) {
       log.debug("FileFormatException thrown");
        return ResponseEntity
                .status(FILE_FORMAT_EXP_CODE)
                .body(new ErrorDto(LocalDateTime.now(), exception.getMessage(), FILE_FORMAT_EXP_CODE));
    }
}
