package com.zerocopy.test.documentupload.api.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;

public record DocumentDto(
        @JsonProperty("name") String fileName,
        @JsonProperty("pages") Integer numPages) { }
