package com.zerocopy.test.documentupload.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record ErrorDto(
        @JsonProperty("timestamp") LocalDateTime timeStamp,
        @JsonProperty("message") String exceptionMessage,
        @JsonProperty("code") Integer statusCode) { }
