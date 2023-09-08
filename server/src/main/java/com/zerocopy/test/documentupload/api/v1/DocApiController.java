package com.zerocopy.test.documentupload.api.v1;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-09-08T22:23:18.985393663+02:00[Europe/Madrid]")
@RestController
@RequestMapping("v1/doc")
public class DocApiController implements DocApi {

    private final NativeWebRequest request;

    @Autowired
    public DocApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
