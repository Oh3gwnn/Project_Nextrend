package com.oh3g.nextrend.controller;

import com.oh3g.nextrend.dto.jsoup.ProductDto;
import com.oh3g.nextrend.service.HtmlParserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ViewController {
    private final HtmlParserService htmlParserService;

    @GetMapping("/result")
    public String getProduct(Model model,
                             @RequestParam("q") String query) {
        log.info(query);
        // musinsa URL (임시)
        String url = "https://www.musinsa.com/search/musinsa/goods?q=" + query + "&list_kind=small&sortCode=pop&sub_sort=&page=1&display_cnt=0&saleGoods=&includeSoldOut=&setupGoods=&popular=&category1DepthCode=&category2DepthCodes=&category3DepthCodes=&selectedFilters=&category1DepthName=&category2DepthName=&brandIds=&price=&colorCodes=&contentType=&styleTypes=&includeKeywords=&excludeKeywords=&originalYn=N&tags=&campaignId=&serviceType=&eventType=&type=&season=&measure=&openFilterLayout=N&selectedOrderMeasure=&shoeSizeOption=&d_cat_cd=&attribute=&plusDeliveryYn=";

        List<ProductDto> productList = htmlParserService.getAdProductItems(url);

        model.addAttribute("query", query);
        model.addAttribute("productList", productList);

        return "result";
    }

    @GetMapping
    public String getSearch() {
        return "index";
    }
}
