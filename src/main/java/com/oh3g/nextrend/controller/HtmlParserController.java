package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.ProductDto;
import com.oh3g.nextrend.service.HtmlParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HtmlParserController {
    @Autowired
    private HtmlParserService htmlParserService;

    @GetMapping("/parse-html")
    public String parseHtml(@RequestParam String url) {
        return htmlParserService.getTitle(url);
    }

    @GetMapping("/man-clothes")
    public List<ProductDto> parseMans(@RequestParam String url) {
        return htmlParserService.getAdProductItems(url);
    }
}
