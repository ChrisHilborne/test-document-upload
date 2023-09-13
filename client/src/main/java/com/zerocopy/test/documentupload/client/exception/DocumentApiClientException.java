package com.zerocopy.test.documentupload.client.exception;

public class DocumentApiClientException extends RuntimeException {

    public DocumentApiClientException(String message) {
        super(message);
    }

    public DocumentApiClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
