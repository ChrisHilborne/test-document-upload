package com.zerocopy.test.documentupload.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApiClientConfig {

    @Value("${api.url}")
    private String apiUri;

    @Value("${api.path.list}")
    private String listPath;

    @Value("${api.path.upload}")
    private String uploadPath;
}
