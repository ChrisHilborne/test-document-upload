package com.zerocopy.test.api.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.time.LocalDateTime;

public record ErrorDto(
        @JsonProperty("timestamp") LocalDateTime timeStamp,
        @JsonProperty("message") String exceptionMessage,
        @JsonProperty("code") Integer statusCode) { }
