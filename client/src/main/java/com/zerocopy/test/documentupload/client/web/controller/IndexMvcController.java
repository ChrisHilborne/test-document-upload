package com.zerocopy.test.documentupload.client.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.zerocopy.test.documentupload.client.web.PathConstants.INDEX_PATH;
import static com.zerocopy.test.documentupload.client.web.TemplateConstants.INDEX;

@Controller
@RequestMapping(INDEX_PATH)
@Slf4j
public class IndexMvcController {

    @GetMapping()
    public String getIndex(Model model) {
        log.trace("Received request for index page");
        model.addAttribute("index", true);
        return INDEX;
    }

}
