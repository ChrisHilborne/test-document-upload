package com.zerocopy.test.documentupload.server.exception;

public class FileFormatException extends RuntimeException {

    private static final String messageFormat = "Wrong file format, should be %s";

    public FileFormatException(String fileFormat) {
        super(String.format(messageFormat, fileFormat));
    }
}