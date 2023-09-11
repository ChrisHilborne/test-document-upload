package com.zerocopy.test.api.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DocumentDto(
        @JsonProperty("name") String fileName,
        @JsonProperty("pages") Integer numPages) { }
